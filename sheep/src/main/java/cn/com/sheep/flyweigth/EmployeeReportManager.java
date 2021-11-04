package cn.com.sheep.flyweigth;

public class EmployeeReportManager implements IReportManager{

    protected String tenantId = null;
    @Override
    public String createReport() {
        return "employee report";
    }

    public EmployeeReportManager(String tenantId){
        this.tenantId = tenantId;
    }
}
