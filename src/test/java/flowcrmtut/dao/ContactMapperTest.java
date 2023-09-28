package flowcrmtut.dao;

import flowcrmtut.FlowCrmTutApplication;
import flowcrmtut.model.Company;
import flowcrmtut.model.Contact;
import flowcrmtut.model.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

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

    @BeforeEach
    void setUp() {

        newCompany = "NewCompany";
        String s = companyMapper.insertCompany(new Company().setName(newCompany));

        newStatus = "NEW";
        UUID statusUUID = UUID.fromString(statusMapper.insertStatus(new Status().setName(newStatus)));

        Contact contact = new Contact().setFirstName("FIRST_NAME")
                .setLastName("LAST_NAME")
                .setFirstName("FIRST_NAME")
                .setEmail("EMAIL@EMAIL.VU")
                .setCompany(companyMapper.getCompanyByName(newCompany).get(0))
                .setStatus(statusMapper.getStatusByName(newStatus).get(0));
        contactId = UUID.fromString(contactMapper.insertContact(
                contact));
    }

    @AfterEach
    void tearDown() {
        contactMapper.deleteContactById(contactId);
        companyMapper.deleteCompanyByName(newCompany);
        statusMapper.deleteStatusByName(newStatus);

    }

    @Test
    void getContactFullCase() {
        var contact = contactMapper.getContactByName(contactId);
        assertThat(contact).isNotNull();
        assertThat(contact.getId()).isEqualTo(contactId);
        assertThat(contact.getLastName()).isEqualTo("LAST_NAME");
        assertThat(contact.getFirstName()).isEqualTo("FIRST_NAME");
        assertThat(contact.getEmail()).isEqualTo("EMAIL@EMAIL.VU");
        assertThat(contact.getStatus().getName()).isEqualTo(newStatus);
        assertThat(contact.getCompany().getName()).isEqualTo(newCompany);
    }

}