package com.bi.salessaas.web.screens.orderitem;

import com.bi.salessaas.entity.*;
import com.bi.salessaas.web.screens.order.OrderJobTypeOptions;
import com.haulmont.cuba.core.global.PersistenceHelper;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.text.NumberFormat;

@UiController("salessaas_OrderItem.edit")
@UiDescriptor("order-item-edit.xml")
@EditedEntityContainer("orderItemDc")
@LoadDataBeforeShow
public class OrderItemEdit extends StandardEditor<OrderItem> {

    private NumberFormat numberFormat = NumberFormat.getCurrencyInstance();

    @Inject
    private InstanceContainer<OrderItem> orderItemDc;
    @Inject
    private LookupField<ItemStatus> statusField;
    @Inject
    private LookupField<ItemInvoiceStatus> invoiceStatusField;

    @Inject
    private Label<String> lblTotalInstallCharge;
    @Inject
    private Label<String> lblTotalStorageCharge;
    @Inject
    private Label<String> lblTotalRemovalCharge;
    @Inject
    private Label<String> jobTypeLabel;
    @Inject
    private CheckBox useMHForCalcField;
    @Inject
    private TextField<BigDecimal> removalMHField;
    @Inject
    private TextField<BigDecimal> installMHField;
    @Inject
    private TextField<BigDecimal> removalChargeField;
    @Inject
    private TextField<BigDecimal> installChargeField;



    private JobType currentOrderJobType;

    @Subscribe
    private void onInit(InitEvent event) {
        ScreenOptions options = event.getOptions();
        if (options instanceof OrderJobTypeOptions){
            currentOrderJobType = ((OrderJobTypeOptions)event.getOptions()).getOrderJobType();
            jobTypeLabel.setValue(String.format("Job Type Wage: %s", numberFormat.format(currentOrderJobType.getHourlyWage())));
        }
    }





    @Subscribe
    private void onBeforeShow(BeforeShowEvent event) {
        //check if this is a new record first before changing values
        if (PersistenceHelper.isNew(getEditedEntity())) {
            statusField.setValue(ItemStatus.NEW);
            invoiceStatusField.setValue(ItemInvoiceStatus.NOT_INVOICED);
        }
        toggleMHFields(getEditedEntity().getUseMHForCalc()); //calculates all of the labels on screen init
    }

    @Subscribe("useMHForCalcField")
    private void onUseMHForCalcFieldValueChange(HasValue.ValueChangeEvent<Boolean> event) {
        //disable to install and removal fields
        toggleMHFields(event.getValue());
    }

    private void toggleMHFields(Boolean enabled) {
        installMHField.setEditable(enabled);
        removalMHField.setEditable(enabled);
        installChargeField.setEditable(!enabled);
        removalChargeField.setEditable(!enabled);
        calculateTotals();
    }


    @Subscribe("productField")
    private void onProductFieldValueChange(HasValue.ValueChangeEvent<Product> event) {
        //set the cost, retail, install and removal charges and mh from the product
        if (event.isUserOriginated()) { // new product selected , not loaded on screen change
            Product product = event.getValue();
            if (product != null) {
                //update fields
                OrderItem item = orderItemDc.getItem();
                item.setCost(product.getCost());
                item.setRetail(product.getBaseRetail());
                item.setInstallCharge(product.getInstallCharge());
                item.setRemovalCharge(product.getRemovalCharge());
                item.setInstallMH(product.getInstallMH());
                item.setRemovalMH(product.getRemovalMH());
                item.setStorage(product.getStoragePrice());

                calculateTotals();
            }
        }
    }

    @Subscribe("quantityField")
    private void onQuantityFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        if (event.isUserOriginated()) {
            calculateTotals();
        }
    }

    @Subscribe("retailField")
    private void onRetailFieldValueChange(HasValue.ValueChangeEvent<BigDecimal> event) {
        if (event.isUserOriginated()) {
            calculateTotals();
        }
    }

    @Subscribe("installMHField")
    private void onInstallMHFieldValueChange(HasValue.ValueChangeEvent<BigDecimal> event) {
        if (event.isUserOriginated()) {
            calculateTotals();
        }
    }

    @Subscribe("removalMHField")
    private void onRemovalMHFieldValueChange(HasValue.ValueChangeEvent<BigDecimal> event) {
        if (event.isUserOriginated()) {
            calculateTotals();
        }
    }

    @Subscribe("installChargeField")
    private void onInstallChargeFieldValueChange(HasValue.ValueChangeEvent<BigDecimal> event) {
        if (event.isUserOriginated()) {
            calculateTotals();
        }
    }

    @Subscribe("removalChargeField")
    private void onRemovalChargeFieldValueChange(HasValue.ValueChangeEvent<BigDecimal> event) {
        if (event.isUserOriginated()) {
            calculateTotals();
        }
    }

    @Subscribe("storageField")
    private void onStorageFieldValueChange(HasValue.ValueChangeEvent<BigDecimal> event) {
        if (event.isUserOriginated()){
            calculateTotals();
        }
    }




    private void calculateTotals() {
        //product price
        getEditedEntity().setTotalPrice(getEditedEntity().getRetail().multiply(new BigDecimal(getEditedEntity().getQuantity())));

        if (getEditedEntity().getUseMHForCalc()){
            //set install and removal charges to the calculated each value and then the label to the total value
            if (currentOrderJobType != null) {
                //MH * jobType hourly wage * quantity
                BigDecimal itemIndividualInstallCharge = getEditedEntity().getInstallMH().multiply(
                        currentOrderJobType.getHourlyWage());
                getEditedEntity().setInstallCharge(itemIndividualInstallCharge);
                getEditedEntity().setTotalInstallCharge(itemIndividualInstallCharge.multiply( new BigDecimal(getEditedEntity().getQuantity())));
                lblTotalInstallCharge.setValue(numberFormat.format(itemIndividualInstallCharge.multiply( new BigDecimal(getEditedEntity().getQuantity()))));

                BigDecimal itemIndividualRemovalCharge = getEditedEntity().getRemovalMH().multiply(
                        currentOrderJobType.getHourlyWage());
                getEditedEntity().setRemovalCharge(itemIndividualRemovalCharge);

                getEditedEntity().setTotalRemovalCharge(itemIndividualRemovalCharge.multiply(new BigDecimal(getEditedEntity().getQuantity())));
                lblTotalRemovalCharge.setValue(numberFormat.format(itemIndividualRemovalCharge.multiply(new BigDecimal(getEditedEntity().getQuantity()))));
            }
        }
        else {
            getEditedEntity().setTotalInstallCharge(getEditedEntity().getInstallCharge().multiply( new BigDecimal(getEditedEntity().getQuantity())));
            lblTotalInstallCharge.setValue(numberFormat.format(getEditedEntity().getInstallCharge().multiply(new BigDecimal(getEditedEntity().getQuantity()))));
            getEditedEntity().setTotalRemovalCharge(getEditedEntity().getRemovalCharge().multiply(new BigDecimal(getEditedEntity().getQuantity())));
            lblTotalRemovalCharge.setValue(numberFormat.format(getEditedEntity().getRemovalCharge().multiply(new BigDecimal(getEditedEntity().getQuantity()))));

        }
        lblTotalStorageCharge.setValue(numberFormat.format(getEditedEntity().getStorage().multiply(new BigDecimal(getEditedEntity().getQuantity()))));
    }


}