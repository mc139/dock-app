package com.dock.dockapp.service;

import com.dock.dockapp.exception.DockNotFoundException;
import com.dock.dockapp.model.Dock;
import com.dock.dockapp.repository.BoatRepo;
import com.dock.dockapp.repository.DockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DockService {

    private final DockRepo dockRepo;
    private final BoatRepo boatRepo;

    @Autowired
    public DockService(DockRepo dockRepo, BoatRepo boatRepo) {
        this.dockRepo = dockRepo;
        this.boatRepo = boatRepo;
    }

    public List<Dock> findAll() {
        return dockRepo.findAll();
    }

    public Dock findDockById(Long id) {
        return dockRepo.findById(id).orElseThrow(() -> new DockNotFoundException(id));
    }

    public Dock save(Dock dock) {
        updateCapacityLeft(dock);
        return dockRepo.save(dock);
    }

    public void updateCapacityLeft(Dock dock) {
        dockRepo.setCapacityLeft(dock.getCapacityLeft(), dock.getId());
    }

    public void deleteById(Long id) {
        dockRepo.deleteById(id);
    }


}
