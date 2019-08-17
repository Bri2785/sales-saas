package com.bi.salessaas.web.screens.company;

import com.bi.salessaas.entity.TenantFileDescriptor;
import com.haulmont.addon.sdbmt.MultiTenancyTools;
import com.haulmont.addon.sdbmt.entity.Tenant;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.EntityAccessException;
import com.haulmont.cuba.core.global.FileStorageException;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.model.InstanceLoader;
import com.haulmont.cuba.gui.screen.*;
import com.bi.salessaas.entity.Company;
import com.haulmont.cuba.gui.upload.FileUploadingAPI;
import com.haulmont.cuba.security.app.UserSessionService;
import com.haulmont.cuba.security.global.UserSession;

import javax.inject.Inject;


@UiController("salessaas_Company.edit")
@UiDescriptor("company-edit.xml")
@EditedEntityContainer("companyDc")
//@LoadDataBeforeShow
public class CompanyEdit extends StandardEditor<Company> {

    @Inject
    private InstanceContainer<Company> companyDc;
    @Inject
    private InstanceLoader<Company> companyDl;
//    @Inject
//    private Image image;
//    @Inject
//    private FileUploadField uploadField;
//    @Inject
//    private FileUploadingAPI fileUploadingAPI;
//    @Inject
//    private DataManager dataManager;
//    @Inject
//    private Notifications notifications;
//    @Inject
//    private Button clearImageBtn;
//
//    @Inject
//    private GridLayout gridInfo;
//    @Inject
//    private Button windowCommitAndCloseButton;
//
    @Inject
    private MultiTenancyTools multiTenancyTools;

    public void onClearImageBtnClick() {
//        getEditedEntity().setLogoFile(null);
//        displayImage();
    }

    @Subscribe
    private void onBeforeShow(BeforeShowEvent event) {
        String tenantId = multiTenancyTools.getCurrentUserTenantId();

//        if (tenantId == null || tenantId.isEmpty()){
//            notifications.create(Notifications.NotificationType.ERROR)
//                    .withCaption("Can't set company info for a non-tenant user")
//                    .show();
//            gridInfo.setEnabled(false);
//            windowCommitAndCloseButton.setEnabled(false);
//        }
//        else {
            companyDl.setParameter("tenantId", tenantId);
            try {
                companyDl.load();

            }
            catch (EntityAccessException e){
                //entity is null, no company info record yet
                //defaults to creating a new instance so we can just continue
            }
//        }
    }

//    @Subscribe
//    private void onInit(InitEvent event) {
//                uploadField.addFileUploadSucceedListener(uploadSucceedEvent -> {
//            TenantFileDescriptor fd = (TenantFileDescriptor) uploadField.getFileDescriptor();
//            try {
//                fileUploadingAPI.putFileIntoStorage(uploadField.getFileId(), fd);
//            } catch (FileStorageException e) {
//                throw new RuntimeException("Error saving file to FileStorage", e);
//            }
//
//            if (fd != null) {
//                fd.setTenantId(multiTenancyTools.getCurrentUserTenantId());
//            }
//
//            getEditedEntity().setLogoFile(dataManager.commit(fd));
//            displayImage();
//
//            companyDc.addItemPropertyChangeListener(customerItemPropertyChangeEvent -> {
//                if ("logoFile".equals(customerItemPropertyChangeEvent.getProperty()))
//                    updateImageButtons(customerItemPropertyChangeEvent.getValue() != null);
//            });
//        });
//
//        uploadField.addFileUploadErrorListener(uploadErrorEvent ->
//                notifications.create()
//                        .withCaption("File upload error")
//                        .show());
//    }
//    @Subscribe
//    protected void onAfterShow(AfterShowEvent event) {
//        displayImage();
//        updateImageButtons(getEditedEntity().getLogoFile() != null);
//    }
//
//    private void updateImageButtons(boolean enable) {
//        clearImageBtn.setEnabled(enable);
//    }
//    private void displayImage() {
//        if (getEditedEntity().getLogoFile() != null) {
//            image.setSource(FileDescriptorResource.class).setFileDescriptor(getEditedEntity().getLogoFile());
//            image.setVisible(true);
//        } else {
//            image.setVisible(false);
//        }
//    }
}