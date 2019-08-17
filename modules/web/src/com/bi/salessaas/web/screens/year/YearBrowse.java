package com.bi.salessaas.web.screens.year;

import com.haulmont.cuba.gui.screen.*;
import com.bi.salessaas.entity.Year;

@UiController("salessaas_Year.browse")
@UiDescriptor("year-browse.xml")
@LookupComponent("table")
@LoadDataBeforeShow
public class YearBrowse extends MasterDetailScreen<Year> {
}