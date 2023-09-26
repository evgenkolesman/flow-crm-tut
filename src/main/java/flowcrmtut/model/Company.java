package flowcrmtut.model;

import java.util.List;
import java.util.UUID;

public class Company extends CommonModel{

    UUID id;
    List<Employee> employees;

    public UUID getId() {
        return id;
    }

    public Company setId(UUID id) {
        this.id = id;
        return this;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Company setEmployees(List<Employee> employees) {
        this.employees = employees;
        return this;
    }
}
