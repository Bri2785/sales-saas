package com.bi.salessaas.web.screens.order;

import com.bi.salessaas.entity.*;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.model.CollectionChangeType;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionPropertyContainer;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.inject.Inject;
import java.math.BigDecimal;
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
    private DataManager dataManager;
    @Inject
    private InstanceContainer<Order> orderDc;
    @Inject
    private CollectionContainer<JobType> jobTypeDc;
    @Inject
    private CollectionPropertyContainer<OrderItem> itemsDc;

    @Subscribe(id = "itemsDc", target = Target.DATA_CONTAINER)
    private void onItemsDcCollectionChange(CollectionContainer.CollectionChangeEvent<OrderItem> event) {
        if (event.getChangeType() != CollectionChangeType.REFRESH) {
            recalculateTotals();
        }
    }

    @Subscribe(id = "itemsDc", target = Target.DATA_CONTAINER)
    private void onItemsDcItemPropertyChange(InstanceContainer.ItemPropertyChangeEvent<OrderItem> event) {

    }

    @Subscribe(id = "orderDc", target = Target.DATA_CONTAINER)
    private void onOrderDcItemPropertyChange(InstanceContainer.ItemPropertyChangeEvent<Order> event) {
        if (event.getProperty().equals("jobType")){
            recalculateTotals(); //if the jobType is changed, the wages may be different so recalculate
        }
    }



    private void recalculateTotals(){
        //recalculate product totals
        //install amount
        //removalAmount
        //storageAmount
        //tax amount
        //discount amount
        //grand total

    }
    private void calculateInstall(){
        //if the override Install flag is not set then calc based on the job type
        if (!orderDc.getItem().getOverrideInstall()){
            //for each item the install is the hourly rate of the job type * the install man hours for the item. Rounded?
            if (orderDc.getItem().getJobType() != null) { //can't calculate if the job type is not selected
                BigDecimal installWageRate = orderDc.getItem().getJobType().getHourlyWage();
                BigDecimal installTotal = BigDecimal.ZERO;

                for (OrderItem item :
                        itemsDc.getItems()) {
                    installTotal = installTotal.add(installWageRate.multiply(item.getInstallMH()));
                }
                getEditedEntity().setTotalInstall(installTotal);
            }
        }
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
    }

    public void ontoggleOverrideRemovalClick() {
        Boolean currentOverride =  orderDc.getItem().getOverrideRemoval();
        orderDc.getItem().setOverrideRemoval(!currentOverride);
        updateRemovalField(!currentOverride);
    }

    public void ontoggleOverrideStorageClick() {
        Boolean currentOverride =  orderDc.getItem().getOverrideStorage();
        orderDc.getItem().setOverrideStorage(!currentOverride);
        updateStorageField(!currentOverride);
    }
}