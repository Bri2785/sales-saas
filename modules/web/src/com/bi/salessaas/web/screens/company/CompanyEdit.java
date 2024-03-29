package com.bi.salessaas.web.screens.company;

import com.bi.salessaas.entity.TenantFileDescriptor;
import com.haulmont.addon.sdbmt.MultiTenancyTools;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.EntityAccessException;
import com.haulmont.cuba.core.global.FileStorageException;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.FileDescriptorResource;
import com.haulmont.cuba.gui.components.FileUploadField;
import com.haulmont.cuba.gui.components.Image;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.model.InstanceLoader;
import com.haulmont.cuba.gui.navigation.UrlParamsChangedEvent;
import com.haulmont.cuba.gui.screen.*;
import com.bi.salessaas.entity.Company;
import com.haulmont.cuba.gui.upload.FileUploadingAPI;
import sun.security.ssl.Debug;

import javax.inject.Inject;

@UiController("salessaas_Company.edit")
@UiDescriptor("company-edit.xml")
@EditedEntityContainer("companyDc")
//@LoadDataBeforeShow
public class CompanyEdit extends StandardEditor<Company> {

    @Inject
    private MultiTenancyTools multiTenancyTools;

    @Inject
    private InstanceContainer<Company> companyDc;
    @Inject
    private InstanceLoader<Company> companyDl;

    @Inject
    private Image image;
    @Inject
    private FileUploadField uploadField;
    @Inject
    private FileUploadingAPI fileUploadingAPI;
    @Inject
    private DataManager dataManager;
    @Inject
    private Notifications notifications;
    @Inject
    private Button clearImageBtn;

    @Subscribe
    private void onAfterInit(AfterInitEvent event) {
        String d = "d";
    }


    @Subscribe
    private void onInitEntity(InitEntityEvent<Company> event) {
        String d = "test";

    }

    @Subscribe
    private void onUrlParamsChanged(UrlParamsChangedEvent event) {
        String d = "test";
    }






    @Subscribe
    private void onBeforeShow(BeforeShowEvent event) {
        String tenantId = multiTenancyTools.getCurrentUserTenantId();

        Company newCompany = getEditedEntity();

        companyDl.setParameter("tenantId", tenantId);

        try {

            companyDl.load();

            getScreenData().getDataContext().evict(newCompany);

            setEntityToEdit(companyDc.getItem());

        } catch (EntityAccessException e) {

            //entity is null, no company info record yet

            //defaults to creating a new instance so we can just continue

        }

    }


//    select e from salessaas_company e
//    where e.tenantId = :tenantId



    public void onClearImageBtnClick() {
        //TODO: remove from file storage
        getEditedEntity().setLogoFile(null);
        displayImage();
    }

    @Subscribe
    private void onInit(InitEvent event) {
                uploadField.addFileUploadSucceedListener(uploadSucceedEvent -> {
            TenantFileDescriptor fd = (TenantFileDescriptor) uploadField.getFileDescriptor();
            try {
                fileUploadingAPI.putFileIntoStorage(uploadField.getFileId(), fd);
            } catch (FileStorageException e) {
                throw new RuntimeException("Error saving file to FileStorage", e);
            }

            if (fd != null) {
                fd.setTenantId(multiTenancyTools.getCurrentUserTenantId());
            }

            getEditedEntity().setLogoFile(dataManager.commit(fd));
            displayImage();

            companyDc.addItemPropertyChangeListener(customerItemPropertyChangeEvent -> {
                if ("logoFile".equals(customerItemPropertyChangeEvent.getProperty()))
                    updateImageButtons(customerItemPropertyChangeEvent.getValue() != null);
            });
        });

        uploadField.addFileUploadErrorListener(uploadErrorEvent ->
                notifications.create()
                        .withCaption("File upload error")
                        .show());
    }
    @Subscribe
    protected void onAfterShow(AfterShowEvent event) {
        displayImage();
        updateImageButtons(getEditedEntity().getLogoFile() != null);
    }

    private void updateImageButtons(boolean enable) {
        clearImageBtn.setEnabled(enable);
    }
    private void displayImage() {
        if (getEditedEntity().getLogoFile() != null) {
            image.setSource(FileDescriptorResource.class).setFileDescriptor(getEditedEntity().getLogoFile());
            image.setVisible(true);
        } else {
            image.setVisible(false);
        }
    }
}