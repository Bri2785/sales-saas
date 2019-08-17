package com.bi.salessaas.web.screens.category;

import com.haulmont.cuba.gui.screen.*;
import com.bi.salessaas.entity.Category;

@UiController("salessaas_Category.browse")
@UiDescriptor("category-browse.xml")
@LookupComponent("table")
@LoadDataBeforeShow
public class CategoryBrowse extends MasterDetailScreen<Category> {
}