package com.dock.dockapp.service;

import com.dock.dockapp.exception.DockNotFoundException;
import com.dock.dockapp.model.Boat;
import com.dock.dockapp.model.Dock;
import com.dock.dockapp.model.DockReservation;
import com.dock.dockapp.repository.BoatRepo;
import com.dock.dockapp.repository.DockRepo;
import com.dock.dockapp.repository.DockReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


@Service
public class DockReservationService {

    private final DockService dockService;
    private final DockRepo dockRepo;
    private final BoatRepo boatRepo;
    private final DockReservationRepo reservationRepo;

    @Autowired
    public DockReservationService(DockReservationRepo reservationRepo, DockService dockService, DockRepo dockRepo, BoatRepo boatRepo) {
        this.reservationRepo = reservationRepo;
        this.dockService = dockService;
        this.dockRepo = dockRepo;
        this.boatRepo = boatRepo;
    }

    public List<DockReservation> findAll() {
        return reservationRepo.findAll();
    }

    public DockReservation findDockReservationByID(Long id) {
        return reservationRepo.findById(id).orElseThrow(() -> new DockNotFoundException(id));
    }

    @Transactional(rollbackFor = Throwable.class)
    public DockReservation save(DockReservation dockReservation) {
        dockReservation.getBoat().setReservation(dockReservation);
        Dock dock = dockReservation.getDock();
        Boat boat1 = dockReservation.getBoat();
        boat1.setDock(dock);
        boatRepo.updateDock(dock.getId(), boat1.getId());
        dockService.updateCapacityLeft(dock);
        return reservationRepo.save(dockReservation);

    }

    public void deleteById(Long id) {
        reservationRepo.deleteById(id);
    }


    @EventListener(ApplicationReadyEvent.class)
    public void fillDB() throws InterruptedException {
        Thread.sleep(1000);
        Dock dock_with_reservation = new Dock(15000.0, "$$$$$$$");
        Boat reserved_boat = new Boat("reserved boat", 1000.0, 999);
        Dock dock_with_reservations = new Dock(888888, "Dock with multiple reservations");
        dockRepo.save(dock_with_reservations);
        boatRepo.save(reserved_boat);
        dockRepo.save(dock_with_reservation);
        save(new DockReservation(dock_with_reservation, reserved_boat
                , Date.valueOf(LocalDate.of(2020, 1, 1))
                , Date.valueOf(LocalDate.of(2023, 11, 11))));

    }

}
