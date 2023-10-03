package ru.flowcrmtut;

import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@PWA(name = "CRM",
        shortName = "CRM",
        offlinePath="offline.html",
        offlineResources = { "./images/offline.png"} )
@NpmPackage(value = "line-awesome", version = "1.3.0")
public class FlowCrmTutApplication extends SpringBootServletInitializer implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(FlowCrmTutApplication.class, args);
    }

}
