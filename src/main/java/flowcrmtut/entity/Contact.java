package entity;

public class Contact {

    private String status, company;

    public String getStatus() {
        return status;
    }

    public Contact setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public Contact setCompany(String company) {
        this.company = company;
        return this;
    }
}
