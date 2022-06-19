package com.dock.dockapp.service;

import com.dock.dockapp.exception.BoatNotFoundException;
import com.dock.dockapp.model.Boat;
import com.dock.dockapp.repository.BoatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

//    @Transactional
    @Transactional(propagation= Propagation.REQUIRES_NEW, readOnly=false)
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

}
