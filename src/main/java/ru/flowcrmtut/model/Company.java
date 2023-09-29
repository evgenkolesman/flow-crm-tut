package ru.flowcrmtut.model;

import java.util.UUID;

public class Company {

    UUID id;
    String name;
    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
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

}
