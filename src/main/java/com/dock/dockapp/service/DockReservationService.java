package com.dock.dockapp.service;

import com.dock.dockapp.exception.DockNotFoundException;
import com.dock.dockapp.model.Boat;
import com.dock.dockapp.model.Dock;
import com.dock.dockapp.model.DockReservation;
import com.dock.dockapp.repository.BoatRepo;
import com.dock.dockapp.repository.DockRepo;
import com.dock.dockapp.repository.DockReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


}
