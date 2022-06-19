package com.dock.dockapp.model;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid = UUID.randomUUID().toString();

    @Override
    public boolean equals(Object o) {

        return this == o || o instanceof BaseEntity
                && Objects.equals(uuid, ((BaseEntity) o).uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
