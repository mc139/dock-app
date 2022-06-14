package com.dock.dockapp.controller;

import com.dock.dockapp.model.DockReservation;
import com.dock.dockapp.service.BoatService;
import com.dock.dockapp.service.DockReservationService;
import com.dock.dockapp.service.DockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;

@RestController
@RequestMapping("api/reservations")
public class DockReservationController {
    private final DockReservationService reservations;
    private final BoatService boatService;
    private final DockService dockService;

    @Autowired
    public DockReservationController(DockReservationService dockReservationService, BoatService boatService, DockService dockService) {
        this.reservations = dockReservationService;
        this.boatService = boatService;
        this.dockService = dockService;
    }

    @GetMapping("/all")
    public Iterable<DockReservation> getAll() {
        return reservations.findAll();
    }

    @GetMapping
    public DockReservation getReservationById(@RequestParam Long id) {
        return reservations.findDockReservationByID(id);
    }

    @PostMapping
    public DockReservation addReservation(@RequestBody DockReservation dockReservation) {
        return reservations.save(dockReservation);
    }

    @DeleteMapping
    public void deleteDock(@RequestParam Long id) {
        reservations.deleteById(id);
    }

    @PutMapping
    public DockReservation updateReservation(@RequestBody DockReservation dockReservation) {
        return reservations.save(dockReservation);
    }

    @PutMapping("/reservation")
    public DockReservation addReservation(@RequestParam Long dockId, Long boatId) {
        return createDockReservation(dockId, boatId);
    }

    private DockReservation createDockReservation(Long dockId, Long boatId) {
        DockReservation dockReservation = new DockReservation(dockService.findDockById(dockId)
                , boatService.findById(boatId), Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()));
        return reservations.save(dockReservation);
    }

}
