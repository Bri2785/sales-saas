package com.bi.salessaas.web.screens.estimateviewer;

import com.haulmont.cuba.gui.components.BrowserFrame;
import com.haulmont.cuba.gui.components.StreamResource;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;
import java.io.ByteArrayInputStream;

@UiController("salessaas_EstimateViewer")
@UiDescriptor("estimate-viewer.xml")
public class EstimateViewer extends Screen {

    private byte[] reportBytes;

    public void setReportBytes(byte[] reportBytes) {
        this.reportBytes = reportBytes;
    }

    @Inject
    private BrowserFrame browserFrame;

    @Subscribe
    private void onBeforeShow(BeforeShowEvent event) {
        if (reportBytes != null){
            browserFrame.setSource(StreamResource.class)
                    .setStreamSupplier(() -> new ByteArrayInputStream(reportBytes))
                    .setMimeType("application/pdf");

        }
    }


}