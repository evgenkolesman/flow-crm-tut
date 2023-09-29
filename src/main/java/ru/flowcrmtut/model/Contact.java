package ru.flowcrmtut.model;

import java.util.UUID;

public class Contact {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private Status status;
    private Company company;

    public UUID getId() {
        return id;
    }

    public Contact setId(UUID id) {
        this.id = id;
        return this;
    }

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

    public UUID getStatus_id() {
        if(this.status != null) {
            return status.getId();
        } else {
            return null;
        }
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
