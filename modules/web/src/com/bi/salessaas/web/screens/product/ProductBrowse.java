package com.bi.salessaas.web.screens.product;

import com.haulmont.cuba.gui.screen.*;
import com.bi.salessaas.entity.Product;

@UiController("salessaas_Product.browse")
@UiDescriptor("product-browse.xml")
@LookupComponent("productsTable")
@LoadDataBeforeShow
public class ProductBrowse extends StandardLookup<Product> {



}