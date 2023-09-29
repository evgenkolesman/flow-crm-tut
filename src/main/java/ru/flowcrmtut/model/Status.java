package ru.flowcrmtut.model;

import java.util.UUID;

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
}
