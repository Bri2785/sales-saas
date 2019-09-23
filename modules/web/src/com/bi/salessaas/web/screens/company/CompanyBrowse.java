package com.bi.salessaas.web.screens.company;

import com.haulmont.cuba.gui.screen.*;
import com.bi.salessaas.entity.Company;

@UiController("salessaas_Company.browse")
@UiDescriptor("company-browse.xml")
@LookupComponent("companiesTable")
@LoadDataBeforeShow
public class CompanyBrowse extends StandardLookup<Company> {
}