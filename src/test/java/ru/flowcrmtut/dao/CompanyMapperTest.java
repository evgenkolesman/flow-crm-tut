package ru.flowcrmtut.dao;

import ru.flowcrmtut.FlowCrmTutApplication;
import ru.flowcrmtut.model.Company;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest(classes = {FlowCrmTutApplication.class})
class CompanyMapperTest {

    @Autowired
    private CompanyMapper companyMapper;
    private String newCompany;

    private UUID uuid;

    @BeforeEach
    void setUp() {

        newCompany = "NewCompany";
        uuid = UUID.fromString(companyMapper.insertCompany(new Company().setName(newCompany)));
    }

    @AfterEach
    void tearDown() {
        companyMapper.deleteCompanyById(String.valueOf(uuid));
    }

    @Test
    void getCompaniesList() {
        List<Company> companiesList = companyMapper.getCompaniesList();
        assertThat(companiesList.size()).isEqualTo(3);
        assertThat(companiesList.get(0).getName()).isEqualTo(newCompany);
    }

    @Test
    void getCompanyByName() {
        List<Company> companiesList = companyMapper.getCompanyByName(newCompany);
        assertThat(companiesList.size()).isEqualTo(2);
        assertThat(companiesList.get(0).getName()).isEqualTo(newCompany);
    }
}