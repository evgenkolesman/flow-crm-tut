package ru.flowcrmtut.ui_components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.ChartVariant;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.charts.Chart;

import ru.flowcrmtut.service.CrmService;

@Route(value = "dashboard", layout = MainLayout.class)
@PageTitle(" Dashboard | CRM")
public class DashboardView extends VerticalLayout {

    private final CrmService crmService;

    public DashboardView(CrmService crmService) {
        this.crmService = crmService;
        addClassName("dashboard-view");
        setHorizontalComponentAlignment(Alignment.CENTER);

        add(getContactStats(), getCompanyChart());

    }

    private Component getContactStats() {
        Span span = new Span(crmService.countContacts() + " contact");
        span.addClassNames("text-xl", "mt-m");
        return span;
    }

    private Component getCompanyChart() {
        Chart chart = new Chart(ChartType.PIE);

        DataSeries dataSeries = new DataSeries();

        crmService.getAllCompanies()
                .forEach( company ->
                        dataSeries.add(new DataSeriesItem(
                               company.getName(),
                               crmService.getEmployeeCountByCompany(String.valueOf(company.getId()))
                        ))
                );
        return chart;
    }
}
