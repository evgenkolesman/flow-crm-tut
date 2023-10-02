package ru.flowcrmtut.ui_components;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import ru.flowcrmtut.service.SecurityService;

public class MainLayout extends AppLayout {

    private final SecurityService securityService;

    public MainLayout(SecurityService securityService) {
        this.securityService = securityService;
        createHeader();

        createDrawerBox();
    }

    private void createDrawerBox() {
        RouterLink list = new RouterLink("List", ListView.class);
        RouterLink dashboard = new RouterLink("Dashboard", DashboardView.class);

        list.setHighlightCondition(HighlightConditions.sameLocation());
        addToDrawer(new VerticalLayout(
                list,
                dashboard
        ));
    }

    private void createHeader() {
        H1 logo = new H1("CRM");
        logo.addClassNames("text-logo", "m-m");

        String u = securityService.getAuthenticatedUser().getUsername();
        Button logout = new Button("Log out " + u, e -> securityService.logout());

        var header = new HorizontalLayout(new DrawerToggle(), logo, logout);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.addClassNames("py-0", "px-m");

        addToNavbar(header);

    }
}
