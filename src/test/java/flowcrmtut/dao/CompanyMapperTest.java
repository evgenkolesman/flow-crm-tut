package flowcrmtut.dao;

import flowcrmtut.FlowCrmTutApplication;
import flowcrmtut.model.Company;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest(classes = {FlowCrmTutApplication.class})
class CompanyMapperTest {

    @Autowired
    private CompanyMapper companyMapper;
    private String newCompany;

    @BeforeEach
    void setUp() {

        newCompany = "NewCompany";
        companyMapper.insertCompany((Company) new Company().setName(newCompany));
    }

    @AfterEach
    void tearDown() {
        companyMapper.deleteCompanyByName(newCompany);
    }

    @Test
    void getCompaniesList() {
        List<Company> companiesList = companyMapper.getCompaniesList();
        assertThat(companiesList.size()).isEqualTo(1);
        assertThat(companiesList.get(0).getName()).isEqualTo(newCompany);
    }

    @Test
    void getCompanyByName() {
        List<Company> companiesList = companyMapper.getCompanyByName(newCompany);
        assertThat(companiesList.size()).isEqualTo(1);
        assertThat(companiesList.get(0).getName()).isEqualTo(newCompany);
    }
}