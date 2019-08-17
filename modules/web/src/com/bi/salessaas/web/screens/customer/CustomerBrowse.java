package com.bi.salessaas.web.screens.customer;

import com.haulmont.cuba.gui.screen.*;
import com.bi.salessaas.entity.Customer;

@UiController("salessaas_Customer.browse")
@UiDescriptor("customer-browse.xml")
@LookupComponent("customersTable")
@LoadDataBeforeShow
public class CustomerBrowse extends StandardLookup<Customer> {
}