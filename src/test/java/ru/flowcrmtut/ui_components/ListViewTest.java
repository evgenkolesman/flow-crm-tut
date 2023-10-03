package ru.flowcrmtut.ui_components;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.ListDataProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.flowcrmtut.model.Contact;
import ru.flowcrmtut.model.ContactForm;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ListViewTest {

    @Autowired
    private ListView listView;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    public void formShownWhenContactSelected() {
        Grid<Contact> grid = listView.grid;
        Contact firstContact = getFirstItem(grid);

        ContactForm form = listView.contactForm;

        assertFalse(form.isVisible());
        grid.asSingleSelect().setValue(firstContact);
        assertTrue(form.isVisible());
        assertEquals(firstContact.getFirstName(), form.firstName.getValue());
    }

    private Contact getFirstItem(Grid<Contact> grid) {
        return( (ListDataProvider<Contact>) grid.getDataProvider()).getItems().iterator().next();
    }
}