package com.bi.salessaas.service;

import com.bi.salessaas.entity.TenantFileDescriptor;
import com.haulmont.cuba.core.global.FileLoader;
import com.haulmont.cuba.core.global.FileStorageException;
import com.haulmont.cuba.core.global.Resources;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

@Service(BiReportService.NAME)
public class BiReportServiceBean implements BiReportService {

    private Connection connection = null;
    private HashMap<String, Object> parameters = new HashMap();
    private byte[] bytes;

    private InputStream inputStream = null;

    @Inject
    private Resources resources;

    @Inject
    private FileLoader fileLoader;

    @Override
    public byte[] generateEstimateReport(String orderId, String companyId, TenantFileDescriptor logoFile) throws SQLException {
        String estimateReportPath = "reports/Order_Service_Test.jasper";

        parameters.put("OrderId", orderId);
        parameters.put("CompanyId", companyId); //for company information

        try {
            inputStream = resources.getResourceAsStream(estimateReportPath);

            if (logoFile != null) {
                InputStream logoInput = null;
                try {
                    logoInput = fileLoader.openStream(logoFile);
                    parameters.put("logoBytes", ImageIO.read(new ByteArrayInputStream(JRLoader.loadBytes(logoInput))));
                } catch (FileStorageException | IOException e) {
                    e.printStackTrace(); //TODO: throw to caller?
                } finally {
                    IOUtils.closeQuietly(logoInput);
                }
            }

            if(inputStream != null) {
                JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters, getDBConnection());
                bytes = JasperExportManager.exportReportToPdf(jasperPrint);
                connection.close();
            }

        } catch (JRException jre) {
            jre.printStackTrace();

//        } catch (SQLException e) {

        } finally {
            IOUtils.closeQuietly(inputStream);
        }

        return bytes;
    }

    private Connection getDBConnection() throws SQLException {
        //DriverManager.getConnection(url, user, pass)

        DataSource ds;
        try {
            Context context = new InitialContext();
            ds = (DataSource) context.lookup("java:/comp/env/jdbc/CubaDS");
            connection = ds.getConnection();
        }
        catch (NamingException e) {
            throw new RuntimeException("Error getting database connection", e);
        }

        return connection;
    }
}