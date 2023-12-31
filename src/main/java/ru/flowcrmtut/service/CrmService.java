package ru.flowcrmtut.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.flowcrmtut.dao.CompanyMapper;
import ru.flowcrmtut.dao.ContactMapper;
import ru.flowcrmtut.dao.EmployeeMapper;
import ru.flowcrmtut.dao.StatusMapper;
import ru.flowcrmtut.model.Company;
import ru.flowcrmtut.model.Contact;
import ru.flowcrmtut.model.Status;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CrmService {

    private final CompanyMapper companyMapper;
    private final ContactMapper contactMapper;
    private final StatusMapper statusMapper;
    private final EmployeeMapper employeeMapper;

    public List<Contact> findAllContacts(String filterText) {
        if (filterText == null || filterText.isEmpty()) {
            return contactMapper.getContactsList();
        } else {

            return contactMapper.getContactBySearchData(filterText);
        }
    }

    public void deleteContact(Contact contact) {
        contactMapper.deleteContactById(contact.getId());
    }

    public void saveContact(Contact contact) {
        if(contact != null) {
            contactMapper.insertContact(contact);
        } else {
            log.error("Contact Cannot be NUll");
        }
    }

    public List<Status> getAllStatuses() {
        return statusMapper.getStatusesList();
    }
    public List<Company> getAllCompanies() {
        return companyMapper.getCompaniesList();
    }

    public int countContacts() {
        return contactMapper.countContacts();
    }


    public Integer getEmployeeCountByCompany(String id) {
        return employeeMapper.countEmployeeInCompany(id);
    }
}
