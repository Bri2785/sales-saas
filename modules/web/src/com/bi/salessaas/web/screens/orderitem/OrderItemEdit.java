package com.bi.salessaas.web.screens.orderitem;

import com.haulmont.cuba.gui.screen.*;
import com.bi.salessaas.entity.OrderItem;

@UiController("salessaas_OrderItem.edit")
@UiDescriptor("order-item-edit.xml")
@EditedEntityContainer("orderItemDc")
@LoadDataBeforeShow
public class OrderItemEdit extends StandardEditor<OrderItem> {
}