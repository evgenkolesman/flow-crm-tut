package ru.flowcrmtut.ui_components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;
import ru.flowcrmtut.model.Contact;
import ru.flowcrmtut.model.ContactForm;
import ru.flowcrmtut.service.CrmService;

import java.util.Collections;
import java.util.List;

@Route(value = "")
@PageTitle("Contacts | Vaadin CRM")
@Slf4j
public class ListView extends VerticalLayout {
    Grid<Contact> grid = new Grid<>(Contact.class);
    TextField filterText = new TextField();

    ContactForm contactForm;

    private final CrmService crmService;


    public ListView(CrmService crmService) {
        this.crmService = crmService;

        addClassName("list-view");
        setSizeFull();

        configureGrid();
        configureForm();

        add(getToolbar(),
                getContent());

        updateList();
    }

    private void updateList() {
//        List<Contact> allContacts = crmService.findAllContacts(filterText.getValue());
//
//        DataProvider<Contact, Void> dataProvider =
//                DataProvider.fromCallbacks(
//                        // First callback fetches items based on a query
//                        query -> {
//                            // The index of the first item to load
//                            int offset = query.getOffset();
//
//                            // The number of items to load
//                            int limit = query.getLimit();
//
//                            List<Contact> contacts = allContacts;
//                            return contacts.stream();
//                        },
//                        // Second callback fetches the total number of items currently in the Grid.
//                        // The grid can then use it to properly adjust the scrollbars.
//                        contactVoidQuery -> {
//                           return allContacts.size();
//                        });
//
//
//        grid.setDataProvider(dataProvider);


        grid.setItems(crmService.findAllContacts(filterText.getValue()));
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("firstName", "lastName", "email");
        grid.addColumn(contact -> {
            log.error(contact.toString());

            return contact.getStatus().getName();
        }).setHeader("Status");
        grid.addColumn(contact -> contact.getCompany().getName()).setHeader("Company");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addContactButton = new Button("Add contact");

        var toolbar = new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private HorizontalLayout getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, contactForm);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, contactForm);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {
        contactForm = new ContactForm(crmService.getAllCompanies(),crmService.getAllStatuses());
        contactForm.setWidth("25em");
    }
}
