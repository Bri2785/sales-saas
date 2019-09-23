package com.bi.salessaas.web.screens.order;

import com.bi.salessaas.entity.Company;
import com.bi.salessaas.service.BiReportService;
import com.bi.salessaas.web.screens.estimateviewer.EstimateViewer;
import com.haulmont.addon.sdbmt.MultiTenancyTools;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.Screens;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.screen.*;
import com.bi.salessaas.entity.Order;

import javax.inject.Inject;
import java.sql.SQLException;

@UiController("salessaas_Order.browse")
@UiDescriptor("order-browse.xml")
@LookupComponent("ordersTable")
@LoadDataBeforeShow
public class OrderBrowse extends StandardLookup<Order> {

    private byte[] reportBytes;

    @Inject
    private Notifications notifications;
    @Inject
    private BiReportService biReportService;
    @Inject
    private Screens screens;
    @Inject
    private MultiTenancyTools multiTenancyTools;
    @Inject
    private DataManager dataManager;
    @Inject
    private GroupTable<Order> ordersTable;

    @Subscribe("loadEstimateButton")
    protected void loadEstimateReport(Button.ClickEvent event){
        try {
            Order selectedOrder = null;
            if((selectedOrder = ordersTable.getSingleSelected()) != null) {
//TODO: add company tenant id to get logo file

                String tenant = multiTenancyTools.getCurrentUserTenantId();
                if (tenant != null){
                    Company currentTenant = loadCompanyFromTenantId(tenant);
                    //get logo if one
                    //if (currentTenant.getLogoFile() != null){
                        reportBytes = biReportService.generateEstimateReport(selectedOrder.getId().toString(), currentTenant.getId().toString(),currentTenant.getLogoFile());
                    //}


                }
                else{
                    //no company info
                    reportBytes = biReportService.generateEstimateReport(selectedOrder.getId().toString(), "",null);
                }



                EstimateViewer estimateViewer = screens.create(EstimateViewer.class, OpenMode.NEW_TAB);
                estimateViewer.setReportBytes(reportBytes);
                screens.show(estimateViewer);
            }
            else{
                notifications.create(Notifications.NotificationType.WARNING).withCaption("Select an estimate to print from the table").show();
            }
        }
        catch (SQLException e){
            notifications.create(Notifications.NotificationType.ERROR).withCaption(e.getMessage()).show();
        }
    }

    private Company loadCompanyFromTenantId(String tenantID){
        return dataManager.load(Company.class).query("select c from salessaas_Company c where c.tenantId = :tenantID")
                .parameter("tenantID", tenantID)
                .view("company-view-with-logo")
                .one();
    }
}