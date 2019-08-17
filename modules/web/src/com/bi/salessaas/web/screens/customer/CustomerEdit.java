package com.bi.salessaas.web.screens.customer;

import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.FileStorageException;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.bi.salessaas.entity.Customer;
import com.haulmont.cuba.gui.upload.FileUploadingAPI;

import javax.inject.Inject;

@UiController("salessaas_Customer.edit")
@UiDescriptor("customer-edit.xml")
@EditedEntityContainer("customerDc")
@LoadDataBeforeShow
public class CustomerEdit extends StandardEditor<Customer> {

    @Inject
    private TextField<String> billAddressField;
    @Inject
    private TextField<String> billZipField;
    @Inject
    private TextField<String> billStateField;
    @Inject
    private TextField<String> billCityField;
    @Inject
    private TextField<String> billAddress2Field;
    @Inject
    private TextField<String> jobZip;
    @Inject
    private TextField<String> jobState;
    @Inject
    private TextField<String> jobCity;
    @Inject
    private TextField<String> jobAddress2;
    @Inject
    private TextField<String> jobAddress;



    @Subscribe("copyBillingAddressInfo")
    private void onCopyBillingAddressInfo(Action.ActionPerformedEvent event) {
        jobAddress.setValue(billAddressField.getValue());
        jobAddress2.setValue(billAddress2Field.getValue());
        jobCity.setValue(billCityField.getValue());
        jobState.setValue(billStateField.getValue());
        jobZip.setValue(billZipField.getValue());
    }

}