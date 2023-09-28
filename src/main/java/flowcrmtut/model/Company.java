package flowcrmtut.model;

import java.util.UUID;

public class Company extends CommonModel{

    UUID id;
//    List<Employee> employees;

    public UUID getId() {
        return id;
    }

    public Company setId(UUID id) {
        this.id = id;
        return this;
    }
  public Company setId(String id) {
        this.id = UUID.fromString(id);
        return this;
    }

    public Company setName(String name) {
        this.name = name;
        return this;
    }

//    public List<Employee> getEmployees() {
//        return employees;
//    }
//
//    public Company setEmployees(List<Employee> employees) {
//        this.employees = employees;
//        return this;
//    }
}
