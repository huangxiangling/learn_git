package cn.com.sheep.flyweigth;

import java.util.HashMap;
import java.util.Map;

public class ReportManagerFactory {

    private Map<String,IReportManager> financialManager = new HashMap();
    private Map<String,IReportManager> employeeManager = new HashMap();

    public IReportManager getFinancialReportManager(String tenantId){
        IReportManager iReportManager = financialManager.get(tenantId);
        if(iReportManager == null){
            iReportManager = new FinancialReportManager(tenantId);
            financialManager.put(tenantId,iReportManager);
        }
        return iReportManager;
    }

    public IReportManager getEmployeeReportManager(String tenantId){
        IReportManager iReportManager = employeeManager.get(tenantId);
        if(iReportManager == null){
            iReportManager = new EmployeeReportManager(tenantId);
            employeeManager.put(tenantId,iReportManager);
        }
        return iReportManager;
    }
}
