package flowcrmtut.model;

public class Contact {
    private String firstName;
    private String lastName;
    private String email;
    private Status status;
    private Company company;
    public String getFirstName() {
        return firstName;
    }

    public Contact setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Contact setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Contact setEmail(String email) {
        this.email = email;
        return this;
    }

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
