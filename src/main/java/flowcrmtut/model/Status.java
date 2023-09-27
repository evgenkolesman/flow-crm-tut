package flowcrmtut.model;

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
}
