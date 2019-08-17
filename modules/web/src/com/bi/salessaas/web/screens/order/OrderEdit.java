package com.bi.salessaas.web.screens.order;

import com.haulmont.cuba.gui.screen.*;
import com.bi.salessaas.entity.Order;

@UiController("salessaas_Order.edit")
@UiDescriptor("order-edit.xml")
@EditedEntityContainer("orderDc")
@LoadDataBeforeShow
public class OrderEdit extends StandardEditor<Order> {
}