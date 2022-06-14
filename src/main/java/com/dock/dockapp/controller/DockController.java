package com.dock.dockapp.controller;

import com.dock.dockapp.model.Dock;
import com.dock.dockapp.service.DockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/docks")
public class DockController {

    private final DockService docks;

    @Autowired
    public DockController(DockService docks) {
        this.docks = docks;
    }

    @GetMapping("/all")
    public Iterable<Dock> getAll() {
        return docks.findAll();
    }

    @GetMapping
    public Dock getDockById(@RequestParam Long id) {
        return docks.findDockById(id);
    }

    @PostMapping
    public Dock addDock(@RequestBody Dock dock) {
        return docks.save(dock);
    }

    @DeleteMapping
    public void deleteDock(@RequestParam Long id) {
        docks.deleteById(id);
    }

    @PutMapping
    public Dock updateDock(@RequestBody Dock dock) {
        return docks.save(dock);
    }

}
