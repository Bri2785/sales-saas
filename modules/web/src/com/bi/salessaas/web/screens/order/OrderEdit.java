package com.bi.salessaas.web.screens.order;

import com.bi.salessaas.entity.*;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.model.CollectionChangeType;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionPropertyContainer;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;

@UiController("salessaas_Order.edit")
@UiDescriptor("order-edit.xml")
@EditedEntityContainer("orderDc")
@LoadDataBeforeShow
public class OrderEdit extends StandardEditor<Order> {

    @Inject
    private LookupField<OrderStatus> statusField;
    @Inject
    private TextField<String> totalStorageField;
    @Inject
    private TextField<String> totalRemovalField;
    @Inject
    private LookupField<Year> seasonField;
    @Inject
    private TextField<String> totalInstallField;
    @Inject
    private TextField<BigDecimal> discountField;
    @Inject
    private DataManager dataManager;
    @Inject
    private InstanceContainer<Order> orderDc;
    @Inject
    private CollectionPropertyContainer<OrderItem> itemsDc;


    @Inject
    private Notifications notifications;

    @Inject
    private ScreenBuilders screenBuilders;
    @Inject
    private Table<OrderItem> itemsTable;




    @Subscribe(id = "itemsDc", target = Target.DATA_CONTAINER)
    private void onItemsDcCollectionChange(CollectionContainer.CollectionChangeEvent<OrderItem> event) {
        if (event.getChangeType() != CollectionChangeType.REFRESH) {
            recalculateTotals();
        }
    }

    @Subscribe(id = "itemsDc", target = Target.DATA_CONTAINER)
    private void onItemsDcItemPropertyChange(InstanceContainer.ItemPropertyChangeEvent<OrderItem> event) {
        if (event.getProperty().equals("quantity") || event.getProperty().equals("retail")) {
            event.getItem().setTotalPrice(event.getItem().getRetail().multiply(new BigDecimal(event.getItem().getQuantity())));
            recalculateTotals();
        }
    }


    @Subscribe(id = "orderDc", target = Target.DATA_CONTAINER)
    private void onOrderDcItemPropertyChange(InstanceContainer.ItemPropertyChangeEvent<Order> event) {
        if (event.getProperty().equals("jobType")){
            JobType jobType = getEditedEntity().getJobType();
            recalculateTotals(); //if the jobType is changed, the wages may be different so recalculate all item install and removal charges

        }
    }

    @Subscribe("discountPercentageField")
    private void onDiscountPercentageFieldValueChange(HasValue.ValueChangeEvent<BigDecimal> event) {
        if (event.isUserOriginated()) {

            toggleDiscountAmountField();

            calculateTotalDiscount();
            calculateGrandTotal();
        }
    }

    private void toggleDiscountAmountField() {
        if (orderDc.getItem().getDiscountPercent().equals(BigDecimal.ZERO)) {
            discountField.setVisible(false);

        } else {
            discountField.setVisible(true);
        }
    }

    @Subscribe("discountField")
    private void onDiscountFieldValueChange(HasValue.ValueChangeEvent<BigDecimal> event) {
        if (event.isUserOriginated()) {
            //manual override
            if (orderDc.getItem().getDiscount() == null || new BigDecimal(String.valueOf(orderDc.getItem().getDiscount())).equals(BigDecimal.ZERO)) { //0 doesn't automatically parse for some reason
                orderDc.getItem().setDiscount(BigDecimal.ZERO);
                orderDc.getItem().setDiscountPercent(BigDecimal.ZERO);
            } else {
                if (orderDc.getItem().getTotalProduct().compareTo(orderDc.getItem().getDiscount()) < 0) { //discount is greater than total product
                    notifications.create(Notifications.NotificationType.WARNING).withCaption("Discount amount is greater than total product. Adjustment made").show();
                    orderDc.getItem().setDiscount(orderDc.getItem().getTotalProduct());
                    orderDc.getItem().setDiscountPercent(BigDecimal.ONE); //100%
                }
                else {
                    orderDc.getItem().setDiscountPercent(orderDc.getItem().getDiscount().divide(orderDc.getItem().getTotalProduct(), 2, RoundingMode.CEILING));
                }
            }
            toggleDiscountAmountField();
            calculateGrandTotal();
        }
    }

    @Subscribe("shippingRateField")
    private void onShippingRateFieldValueChange(HasValue.ValueChangeEvent<BigDecimal> event) {
        if (event.isUserOriginated()) {
            calculateShippingAmount();
            calculateGrandTotal();
        }
    }







    private void recalculateTotals(){
        //recalculate product totals
        //install amount
        //removalAmount
        //storageAmount
        //tax amount - check the customer tax rate. iF not null, use that percent, otherwise get global tax rate from company info
        //discount amount - if discount percent field is not null then calc amount from total Product
        //grand total

        calculateOrderInstall();
        calculateOrderRemoval();
        calculateOrderStorage();
        calculateOrderTotalProduct();
        calculateTotalDiscount();
        calculateTaxAmount();
        calculateShippingAmount();
        calculateGrandTotal();
    }
    private void calculateOrderInstall(){
        //if the override Install flag is not set then calc based on the job type or just retrieve install charge
        if (!orderDc.getItem().getOverrideInstall()){
            //for each item
            //if the UseMH is selected the install is the hourly rate of the job type * the install man hours for the item * quantity.
            // todo:Rounded?
            if (orderDc.getItem().getJobType() != null && itemsDc.getItems().size() > 0) { //can't calculate if the job type is not selected, or there are no items
                BigDecimal installWageRate = orderDc.getItem().getJobType().getHourlyWage();
                BigDecimal installTotal = BigDecimal.ZERO;

                for (OrderItem item : itemsDc.getItems()) {
                    if (item.getUseMHForCalc()){
                        //we need to update the install charge, same if it was done on the orderItem screen
                        BigDecimal itemIndividualInstallCharge = item.getInstallMH().multiply(
                                installWageRate);
                        item.setInstallCharge(itemIndividualInstallCharge);

                    }
                    //now that the install charge is updates or already set:
                    installTotal = installTotal.add(item.getInstallCharge().multiply(new BigDecimal(item.getQuantity())));

                }
                getEditedEntity().setTotalInstall(installTotal);
            }
        }
    }
    private void calculateOrderRemoval(){
        //if the override Install flag is not set then calc based on the job type or just retrieve removal charge
        if (!orderDc.getItem().getOverrideRemoval()){
            //for each item the install is the hourly rate of the job type * the install man hours for the item. Rounded?
            if (orderDc.getItem().getJobType() != null && itemsDc.getItems().size() > 0) { //can't calculate if the job type is not selected
                BigDecimal removalWageRate = orderDc.getItem().getJobType().getHourlyWage();
                BigDecimal removalTotal = BigDecimal.ZERO;

                for (OrderItem item : itemsDc.getItems()) {
                    if (item.getUseMHForCalc()){
                        //we need to update the removal charge, same if it was done on the orderItem screen
                        BigDecimal itemIndividualRemovalCharge = item.getRemovalMH().multiply(
                                removalWageRate);
                        item.setRemovalCharge(itemIndividualRemovalCharge);

                    }
                    //now that the install charge is updates or already set:
                    removalTotal = removalTotal.add(item.getRemovalCharge().multiply(new BigDecimal(item.getQuantity())));

                }
                getEditedEntity().setTotalRemoval(removalTotal);
            }
        }
    }
    private void calculateOrderStorage(){
        if (!orderDc.getItem().getOverrideStorage()){

            if (itemsDc.getItems().size() > 0) { //can't calculate if the job type is not selected
                BigDecimal storageTotal = BigDecimal.ZERO;

                for (OrderItem item : itemsDc.getItems()) {

                    storageTotal = storageTotal.add(item.getStorage().multiply(new BigDecimal(item.getQuantity())));

                }
                getEditedEntity().setTotalStorage(storageTotal);
            }
        }
    }
    private void calculateOrderTotalProduct(){

        if (itemsDc.getItems().size() > 0) { //can't calculate if the job type is not selected
            BigDecimal productTotal = BigDecimal.ZERO;

            for (OrderItem item : itemsDc.getItems()) {

                productTotal = productTotal.add(item.getRetail().multiply(new BigDecimal(item.getQuantity())));

            }
            getEditedEntity().setTotalProduct(productTotal);
        }

    }
    private void calculateTaxAmount() {
        //check the customer tax rate. iF not null, use that percent, otherwise get global tax rate from company info
        //totalProduct + if(installTaxable) + if(removalTaxable) + if(storageTaxable) * taxrate
    }
    private void calculateTotalDiscount(){
        if (orderDc.getItem().getDiscountPercent() == null) {
            orderDc.getItem().setDiscountPercent(BigDecimal.ZERO);
        }
            //discount percent provided
            BigDecimal totalDiscount = BigDecimal.ZERO;
            totalDiscount = orderDc.getItem().getDiscountPercent().multiply(orderDc.getItem().getTotalProduct());

            orderDc.getItem().setDiscount(totalDiscount);

    }
    private void calculateShippingAmount(){
        if (orderDc.getItem().getShippingRate() == null || orderDc.getItem().getShippingRate().equals(BigDecimal.ZERO)){
            orderDc.getItem().setShippingAmount(BigDecimal.ZERO);
        }
        else {
            orderDc.getItem().setShippingAmount(orderDc.getItem().getShippingRate().multiply(orderDc.getItem().getTotalProduct()));
        }
    }
    private void calculateGrandTotal(){
        //add all together
        BigDecimal grandTotal = BigDecimal.ZERO;

        grandTotal = grandTotal.add(orderDc.getItem().getTotalProduct())
                .add(orderDc.getItem().getTotalInstall())
                .add(orderDc.getItem().getTotalRemoval())
                .add(orderDc.getItem().getTotalStorage())
                .add(orderDc.getItem().getTotalTax())
                .subtract(orderDc.getItem().getDiscount())
                .add(orderDc.getItem().getShippingAmount());



        orderDc.getItem().setGrandTotal(grandTotal);
    }

    @Subscribe("itemsTable.create")
    private void onItemsTableCreate(Action.ActionPerformedEvent event) {
        //verify the job type field has been set first so we can calc our items on the next screen
        if (getEditedEntity().getJobType() != null) {
            OrderJobTypeOptions orderJobTypeOptions = new OrderJobTypeOptions();
            orderJobTypeOptions.setOrderJobType(getEditedEntity().getJobType());

            Screen screen = screenBuilders.editor(itemsTable)
                    .newEntity()
                    .withOptions(orderJobTypeOptions)
                    .build()
                    .show();
        }
        else {
            notifications.create(Notifications.NotificationType.WARNING).withCaption("Job type needs to be set before adding items").show();
        }
    }

    @Subscribe("itemsTable.edit")
    private void onItemsTableEdit(Action.ActionPerformedEvent event) {
            OrderJobTypeOptions orderJobTypeOptions = new OrderJobTypeOptions();
            orderJobTypeOptions.setOrderJobType(getEditedEntity().getJobType());

            Screen screen = screenBuilders.editor(itemsTable)
                    .withOptions(orderJobTypeOptions)
                    .build()
                    .show();

    }





    @Subscribe
    private void onBeforeShow(BeforeShowEvent event) {
        statusField.setValue(OrderStatus.ESTIMATE);

        //set the season base on the current year
        //TODO: verify time zone is local user
        String yearInString = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        try{
            Year yearEntity = dataManager.load(Year.class).query("select y from salessaas_Year y where y.name = :yearName")
                    .parameter("yearName", yearInString)
                    .view("_minimal")
                    .one();
            seasonField.setValue(yearEntity);
        }
        catch (Exception e){
            //ignore, will just leave the field blank for the user to select
        }


    }

    @Subscribe
    private void onAfterShow(AfterShowEvent event) {
        //check the order record for the service override values and set the enabled property of the text field accordingly
        updateInstallField(orderDc.getItem().getOverrideInstall());
        updateRemovalField(orderDc.getItem().getOverrideRemoval());
        updateStorageField(orderDc.getItem().getOverrideStorage());

    }



    private void updateStorageField(Boolean overrideStorage) {
        totalStorageField.setEditable(overrideStorage);
    }

    private void updateRemovalField(Boolean overrideRemoval) {
        totalRemovalField.setEditable(overrideRemoval);
    }

    private void updateInstallField(Boolean overrideInstall) {
        totalInstallField.setEditable(overrideInstall);
    }


    public void ontoggleOverrideInstallClick() {
        Boolean currentOverride =  orderDc.getItem().getOverrideInstall();
        orderDc.getItem().setOverrideInstall(!currentOverride);
        updateInstallField(!currentOverride);
        if (currentOverride){
            recalculateTotals(); //went back to auto-calc mode
        }
    }

    public void ontoggleOverrideRemovalClick() {
        Boolean currentOverride =  orderDc.getItem().getOverrideRemoval();
        orderDc.getItem().setOverrideRemoval(!currentOverride);
        updateRemovalField(!currentOverride);
        if (currentOverride){
            recalculateTotals(); //went back to auto-calc mode
        }
    }

    public void ontoggleOverrideStorageClick() {
        Boolean currentOverride =  orderDc.getItem().getOverrideStorage();
        orderDc.getItem().setOverrideStorage(!currentOverride);
        updateStorageField(!currentOverride);
        if (currentOverride){
            recalculateTotals(); //went back to auto-calc mode
        }
    }
}