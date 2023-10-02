package ru.flowcrmtut.model;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Status extends CommonModel{

    UUID id;

    public UUID getId() {
        return id;
    }

    public Status setId(UUID id) {
        this.id = id;
        return this;
    }

    public Status setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "Status{" +
               "id=" + id +
               ", name='" + name + '\'' +
               '}';
    }
}
