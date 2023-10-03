package ru.flowcrmtut.ui_components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import ru.flowcrmtut.model.Contact;
import ru.flowcrmtut.model.ContactForm;
import ru.flowcrmtut.service.CrmService;

import javax.annotation.security.PermitAll;

@Scope("prototype")
@org.springframework.stereotype.Component
@Route(value = "", layout = MainLayout.class)
@PageTitle("Contacts | Vaadin CRM")
@PermitAll
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
        //this selects all results from db and visualize it in grid
        updateList();
        // this close editor when nothing is selected
        closeEditor();
    }

    private void closeEditor() {
        contactForm.setContact(null);
        contactForm.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(crmService.findAllContacts(filterText.getValue()));
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("firstName", "lastName", "email");
        grid.addColumn(contact -> {
            log.warn(contact.toString());
            return contact.getStatus().getName();
        }).setHeader("Status");
        grid.addColumn(contact -> contact.getCompany().getName()).setHeader("Company");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        // we use grid ass single select
        grid.asSingleSelect().addValueChangeListener(event -> editContact(event.getValue()));
    }
    //take data from edited contact
    private void editContact(Contact value) {
        if( value == null) {
            closeEditor();
        } else {
            // Vizualization of editing
            contactForm.setContact(value);
            contactForm.setVisible(true);
            addClassName("editing");
        }
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addContactButton = new Button("Add contact");
        addContactButton.addClickListener( event -> addContact());

        var toolbar = new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addContact() {
        grid.asSingleSelect().clear();
        editContact(new Contact());
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
        // events

        contactForm.addSaveListener(this::saveContact);
        contactForm.addCloseListener(event -> closeEditor());
        contactForm.addDeleteListener(this::deleteContact);

    }



    private void deleteContact(ContactForm.DeleteEvent deleteEvent) {
        crmService.deleteContact(deleteEvent.getContact());
        updateList();
        closeEditor();
    }

    private void saveContact(ContactForm.SaveEvent saveEvent) {
        crmService.saveContact(saveEvent.getContact());
        updateList();
        closeEditor();
    }
}
