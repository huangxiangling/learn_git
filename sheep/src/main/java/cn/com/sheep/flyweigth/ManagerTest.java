package cn.com.sheep.flyweigth;

public class ManagerTest {


    public static void main(String[] args) {
        /**
         * 享元模式，ReportManagerFactory作为享元工厂，以tenantId为唯一索引，维护了一个享元集合
         * 它确保相同的tenantId返回唯一的实例，确保对象的有效复用
         */
        ReportManagerFactory managerFactory = new ReportManagerFactory();
        IReportManager financialReportManager = managerFactory.getFinancialReportManager("A");
        String report = financialReportManager.createReport();
        System.out.println(report);
        System.out.println("提交git");
    }
}
