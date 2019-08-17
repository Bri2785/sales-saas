package com.bi.salessaas.web.screens.order;

import com.haulmont.cuba.gui.screen.*;
import com.bi.salessaas.entity.Order;

@UiController("salessaas_Order.browse")
@UiDescriptor("order-browse.xml")
@LookupComponent("ordersTable")
@LoadDataBeforeShow
public class OrderBrowse extends StandardLookup<Order> {
}