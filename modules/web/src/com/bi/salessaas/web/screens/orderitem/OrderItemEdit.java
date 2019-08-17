package com.bi.salessaas.web.screens.orderitem;

import com.bi.salessaas.entity.ItemInvoiceStatus;
import com.bi.salessaas.entity.ItemStatus;
import com.bi.salessaas.entity.Product;
import com.haulmont.cuba.core.global.PersistenceHelper;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.bi.salessaas.entity.OrderItem;

import javax.inject.Inject;
import java.math.BigDecimal;

@UiController("salessaas_OrderItem.edit")
@UiDescriptor("order-item-edit.xml")
@EditedEntityContainer("orderItemDc")
@LoadDataBeforeShow
public class OrderItemEdit extends StandardEditor<OrderItem> {

    @Inject
    private InstanceContainer<OrderItem> orderItemDc;
    @Inject
    private LookupField<ItemStatus> statusField;
    @Inject
    private LookupField<ItemInvoiceStatus> invoiceStatusField;


    @Subscribe
    private void onBeforeShow(BeforeShowEvent event) {
        //check if this is a new record first before changing values
        if (PersistenceHelper.isNew(getEditedEntity())) {
            statusField.setValue(ItemStatus.NEW);
            invoiceStatusField.setValue(ItemInvoiceStatus.NOT_INVOICED);
        }
    }




    @Subscribe("productField")
    private void onProductFieldValueChange(HasValue.ValueChangeEvent<Product> event) {
        //set the cost, retail, install and removal charges and mh from the product
        if (event.isUserOriginated()) {
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

                calculateTotal();
            }
        }
    }

    @Subscribe("quantityField")
    private void onQuantityFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        calculateTotal();
    }

    private void calculateTotal() {
        getEditedEntity().setTotalPrice(getEditedEntity().getRetail().multiply(new BigDecimal(getEditedEntity().getQuantity())));
    }


}