package com.dock.dockapp.util;

import com.dock.dockapp.model.Boat;
import com.dock.dockapp.model.Dock;
import com.dock.dockapp.model.DockReservation;
import com.dock.dockapp.service.BoatService;
import com.dock.dockapp.service.DockReservationService;
import com.dock.dockapp.service.DockService;
import com.dock.dockapp.service.DockUserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
@AllArgsConstructor
public class ApplicationInitializationUtil {

    private BoatService boatService;
    private DockReservationService dockReservationService;
    private DockService dockService;
    private DockUserService dockUserService;


    @EventListener(ApplicationReadyEvent.class)
    public void fillDB() {

        Boat statek_testow_zadokowany = new Boat("TEST BOAT DOCKED", 2000, 22121);
        statek_testow_zadokowany.setDock(new Dock(10000, "TEST DOCK"));
        statek_testow_zadokowany.setOwner(dockUserService.findUserByUserName("admin"));
        boatService.save(statek_testow_zadokowany);
        boatService.save(new Boat("TEST BOAT#1", 20000.0, 22121));
        boatService.save(new Boat("TEST BOAT#2", 31223.0, 21));
        boatService.save(new Boat("TEST BOAT#3", 31223, 22));
        boatService.save(new Boat("TEST BOAT#4", 31223, 23));
        boatService. save(new Boat("TEST BOAT#5", 31223, 24));
        boatService. save(new Boat("TEST BOAT#6", 31223, 25));

        Dock dock_with_reservation = new Dock(15000.0, "dock with boat reservation");
        Boat reserved_boat = new Boat("reserved boat", 1000.0, 999);
        Dock dock_with_reservations = new Dock(888888, "Dock with multiple reservations");
        dockService.save(dock_with_reservations);
        boatService.save(reserved_boat);
        dockService.save(dock_with_reservation);
        dockReservationService.save(new DockReservation(dock_with_reservation, reserved_boat
                , Date.valueOf(LocalDate.of(2020, 1, 1))
                , Date.valueOf(LocalDate.of(2023, 11, 11))));

        dockService.save(new Dock(1000000, "TEST DOCK #1"));
        dockService.save(new Dock(1004320, "TEST DOCK #2"));
        dockService.save(new Dock(104300000, "TEST DOCK #3"));
        dockService.save(new Dock(100430000, "TEST DOCK #4"));

    }
}
