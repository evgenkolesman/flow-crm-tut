//package ru.flowcrmtut.ui_components;
//
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.router.Route;
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.JFreeChart;
//import org.jfree.data.general.DefaultPieDataset;
//import org.springframework.stereotype.Component;
//import ru.flowcrmtut.service.CrmService;
//
//@Route(value = "", layout = MainLayout.class)
//@Component
//public class CustomComponentCharts {
//
//    private final CrmService crmService;
//
//    public CustomComponentCharts(CrmService crmService) {
//        this.crmService = crmService;
//        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
//
//        this.crmService.getAllCompanies().forEach(
//                company -> dataset.setValue(company.getName(), this.crmService.getEmployeeCountByCompany(String.valueOf(company.getId()))));
//
//        JFreeChart companyStat = ChartFactory
//                .createPieChart("COMPANY STAT",
//                        dataset
//                );
//        JFreeChartWrapper jFreeChartWrapper = new JFreeChartWrapper();
//        add(companyStat);
//
//    }
//}
