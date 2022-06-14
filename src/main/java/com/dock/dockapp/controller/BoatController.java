package com.dock.dockapp.controller;

import com.dock.dockapp.exception.BoatNotFoundException;
import com.dock.dockapp.model.Boat;
import com.dock.dockapp.model.Dock;
import com.dock.dockapp.service.BoatService;
import com.dock.dockapp.service.DockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController()
@RequestMapping("api/boats")
public class BoatController {

    private final BoatService boats;
    private final DockService docks;

    @Autowired
    public BoatController(BoatService boatService, DockService docks) {
        this.boats = boatService;
        this.docks = docks;
    }

    @GetMapping("/all")
    public Iterable<Boat> getAll() {
        return boats.findAll();
    }

    @GetMapping
    public Boat getById(@RequestParam Long id) {
        return Optional.of(boats.findById(id)).orElseThrow(() -> new BoatNotFoundException(id));
    }

    @PostMapping
    public Boat addBoat(@RequestBody Boat boat) {
        return boats.save(boat);
    }

    @PutMapping
    public Boat updateBoat(@RequestBody Boat boat) {
        return boats.save(boat);
    }

    @DeleteMapping
    public void deleteBoat(@RequestParam Long id) {
        boats.deleteById(id);
    }

    @PutMapping("/assign")
    public void assignBoatToDock(@RequestParam Long boatId, @RequestParam Long dockId) {
        processDockReservation(boatId, dockId);

    }

    private void processDockReservation(Long boatId, Long dockId) {
        Boat boat = boats.findById(boatId);
        Dock dock = docks.findDockById(dockId);
        dock.getBoats().add(boat);
        boats.save(boat);
    }
}
