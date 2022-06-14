package com.dock.dockapp.service;

import com.dock.dockapp.exception.BoatNotFoundException;
import com.dock.dockapp.model.Boat;
import com.dock.dockapp.model.Dock;
import com.dock.dockapp.repository.BoatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BoatService {

    private final BoatRepo boatRepo;
    private final DockUserService dockUserService;

    @Autowired
    public BoatService(BoatRepo boatRepo, DockUserService dockUserService) {
        this.boatRepo = boatRepo;
        this.dockUserService = dockUserService;
    }


    public List<Boat> findAllByUserName(String userName) {
        return Optional.of(boatRepo.findAll()).orElse(Collections.emptyList()).stream()
                .filter(Objects::nonNull)
                .filter(c -> c.getOwner() != null)
                .filter(c -> c.getOwner().getUsername() != null)
                .filter(c -> c.getOwner().getUsername().equals(userName))
                .toList();
    }

    public Boat findById(Long id) {
        return boatRepo.findById(id).orElseThrow(() -> new BoatNotFoundException(id));
    }

    public List<Boat> findAll() {
        return (List<Boat>) boatRepo.findAll();
    }

    public Boat save(Boat boat) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            boat.setOwner(dockUserService.findUserByUserName(authentication.getName()));

        }
        return boatRepo.save(boat);
    }

    public void deleteById(Long id) {
        boatRepo.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB() {

        Boat statek_testow_zadokowany = new Boat("statek testow zadokowany", 2000, 22121);
        statek_testow_zadokowany.setDock(new Dock(10000, "dockTestowy"));
        statek_testow_zadokowany.setOwner(dockUserService.findUserByUserName("admin"));
        save(statek_testow_zadokowany);
        save(new Boat("statek testow9", 20000.0, 22121));
        save(new Boat("Statek testowy1", 31223.0, 21));
        save(new Boat("Statek testowy2", 31223, 22));
        save(new Boat("Statek testowy3", 31223, 23));
        save(new Boat("Statek testowy4", 31223, 24));
        save(new Boat("Statek testowy5", 31223, 25));

    }


}
