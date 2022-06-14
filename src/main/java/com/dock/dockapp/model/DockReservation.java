package com.dock.dockapp.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Entity
public class DockReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "dock_id")
    private Dock dock;

    @OneToOne
    @JoinColumn(name = "boat_id")
    private Boat boat;
    @DateTimeFormat
    private Date dateFrom;
    @DateTimeFormat
    private Date dateTo;

    public DockReservation(Dock dock, Boat boat, Date dateFrom, Date dateTo) {
        this.dock = dock;
        this.boat = boat;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        boat.setDock(dock);
        dock.getBoats().add(boat);
    }
}
