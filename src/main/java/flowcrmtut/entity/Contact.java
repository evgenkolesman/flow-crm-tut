package flowcrmtut.entity;

import flowcrmtut.model.Company;
import flowcrmtut.model.Status;

public class Contact {

    private Status status;

    private Company company;

    public Status getStatus() {
        return status;
    }

    public Contact setStatus(Status status) {
        this.status = status;
        return this;
    }

    public Company getCompany() {
        return company;
    }

    public Contact setCompany(Company company) {
        this.company = company;
        return this;
    }
}
