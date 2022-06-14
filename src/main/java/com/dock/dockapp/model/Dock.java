package com.dock.dockapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Dock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;
    private double capacity;
    private String name;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "dock", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Boat> boats = new HashSet<>();
    @Column(name = "capacity_left")
    private double capacityLeft = getCapacityLeft();

    public Dock(double capacity, String name) {
        this.capacity = capacity;
        this.name = name;
    }

    public double getCapacityLeft() {
        return capacity - Optional.ofNullable(boats).orElse(Collections.emptySet())
                .stream()
                .filter(Objects::nonNull)
                .filter(boat -> boat.getVolume() != 0)
                .mapToDouble(Boat::getVolume).sum();
    }

    public String getCapacityAsString() {
        return ((long) capacity + " m2");
    }

    public String getCapacityLeftAsString() {
        return ((long) getCapacityLeft() + " m2");
    }

    public String getNumberOfDockBoats() {
        return boats.size() + " boats.";
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", space left : " + getCapacityLeftAsString();
    }
}


