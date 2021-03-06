package com.dock.dockapp.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Optional;

@Getter
@Setter
//@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@SelectBeforeUpdate
//@DynamicUpdate
public class Boat extends BaseEntity {

    //    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Id
//    private Long id;
    @NonNull
    private String name;
    @NonNull
    private double volume;
    @NonNull
    private int regNo;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dock_id")
    @JsonIgnore
    private Dock dock;
    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "boat", orphanRemoval = true)
    @JsonIgnore
    private DockReservation reservation;
    @OneToOne
    private DockUser owner;

    @Version()
    @JsonIgnore
    private long version = 0L;
    @Id
    private Long id;

    public String getDockName() {
        return Optional.ofNullable(dock)
                .stream()
                .map(Dock::getName)
                .filter(Objects::nonNull)
                .findFirst().orElse("");

    }

    @Override
    public String toString() {
        return "Boat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", volume=" + volume +
                ", regNo=" + regNo +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
