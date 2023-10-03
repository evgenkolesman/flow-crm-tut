package ru.flowcrmtut.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.flowcrmtut.FlowCrmTutApplication;
import ru.flowcrmtut.model.Company;
import ru.flowcrmtut.model.Contact;
import ru.flowcrmtut.model.Status;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest(classes = {FlowCrmTutApplication.class})
class ContactMapperTest {

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private StatusMapper statusMapper;

    @Autowired
    private ContactMapper contactMapper;


    private String newCompany;
    private String newStatus;

    private UUID contactId;
    private UUID statusUUID;
    private String companyId;

    @BeforeEach
    void setUp() {

        newCompany = "NewCompany " + ThreadLocalRandom.current().nextInt(100);
        companyId = companyMapper.insertCompany(new Company().setName(newCompany));

        newStatus = "NEW " + ThreadLocalRandom.current().nextInt(100);
        statusUUID = UUID.fromString(statusMapper.insertStatus(new Status().setName(newStatus)));

        Contact contact = new Contact()
                .setFirstNameB("FIRST_NAME")
                .setLastNameB("LAST_NAME")
                .setEmailB("EMAIL@EMAIL.VU")
                .setCompanyB(companyMapper.getCompanyByName(newCompany).get(0))
                .setStatusB(statusMapper.getStatusByName(newStatus).get(0));
        contactId = UUID.fromString(contactMapper.insertContact(
                contact));
    }

    @AfterEach
    void tearDown() {
        contactMapper.deleteContactById(contactId);
        companyMapper.deleteCompanyById(companyId);
        statusMapper.deleteStatusById(statusUUID);

    }

    @Test
    void getContactFullCase() {
        var contact = contactMapper.getContactById(contactId);
        assertThat(contact).isNotNull();
        assertThat(contact.getId()).isEqualTo(contactId);
        assertThat(contact.getLastName()).isEqualTo("LAST_NAME");
        assertThat(contact.getFirstName()).isEqualTo("FIRST_NAME");
        assertThat(contact.getEmail()).isEqualTo("EMAIL@EMAIL.VU");
        assertThat(contact.getStatus().getName()).isEqualTo(newStatus);
        assertThat(contact.getCompany().getName()).isEqualTo(newCompany);
    }

    @Test
    void getContactSearchRequest() {
        var contactList = contactMapper.getContactBySearchData("SEC");
        var contact = contactList.get(0);
        assertThat(contact).isNotNull();
//        assertThat(contact.getId()).isEqualTo(contactId);
        assertThat(contact.getLastName()).isEqualTo("LAST_NAME");
        assertThat(contact.getFirstName()).isEqualTo("SECOND_NAME");
        assertThat(contact.getEmail()).isEqualTo("EMAIL@EMAIL.VU");
        assertThat(contact.getStatus().getName()).isEqualTo("NEW");
        assertThat(contact.getCompany().getName()).isEqualTo("NewCompany");
    }


    @Test
    void countContactsWithPatternProcedureTest() {
        var numberContact = contactMapper.countContactsWithPattern("SEC");
        assertThat(numberContact).isEqualTo(1);
    }

}