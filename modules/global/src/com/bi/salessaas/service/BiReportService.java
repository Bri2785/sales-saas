package com.bi.salessaas.service;

import com.bi.salessaas.entity.TenantFileDescriptor;

import java.sql.SQLException;

public interface BiReportService {
    String NAME = "salessaas_BiReportService";

    byte[] generateEstimateReport(String orderId, String companyId, TenantFileDescriptor logoFile) throws SQLException;

}