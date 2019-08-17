package com.bi.salessaas.web.screens.product;

import com.haulmont.cuba.gui.screen.*;
import com.bi.salessaas.entity.Product;

@UiController("salessaas_Product.edit")
@UiDescriptor("product-edit.xml")
@EditedEntityContainer("productDc")
@LoadDataBeforeShow
public class ProductEdit extends StandardEditor<Product> {
}