package com.bi.salessaas.web.screens.salesperson;

import com.haulmont.cuba.gui.screen.*;
import com.bi.salessaas.entity.SalesPerson;

@UiController("salessaas_SalesPerson.browse")
@UiDescriptor("sales-person-browse.xml")
@LookupComponent("table")
@LoadDataBeforeShow
public class SalesPersonBrowse extends MasterDetailScreen<SalesPerson> {
}