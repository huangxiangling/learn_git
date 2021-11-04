package cn.com.sheep.flyweigth;

public class FinancialReportManager implements IReportManager{

    protected String tenantId = null;
    @Override
    public String createReport() {
        return "financial report";
    }

    public FinancialReportManager(String tenantId){
        this.tenantId = tenantId;
    }
}
