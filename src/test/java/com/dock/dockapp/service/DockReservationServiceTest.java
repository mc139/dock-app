package com.dock.dockapp.service;

import com.dock.dockapp.model.Boat;
import com.dock.dockapp.model.Dock;
import com.dock.dockapp.model.DockReservation;
import com.dock.dockapp.model.DockUser;
import com.dock.dockapp.repository.BoatRepo;
import com.dock.dockapp.repository.DockRepo;
import com.dock.dockapp.repository.DockReservationRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class DockReservationServiceTest {

    @Mock
    private DockService dockService;
    @Mock
    private DockReservationRepo reservationRepository;
    @Mock
    private DockRepo dockRepository;
    @Mock
    private BoatRepo boatRepository;

    private DockReservation dockReservation;

    @Spy
    @InjectMocks
    private DockReservationService dockReservationService;

    @BeforeEach
    public void prepareMock() {
        prepareMockData();

    }

    @Test
    void shouldFindAllAndReturn10() {

        //WHEN
        List<DockReservation> all = dockReservationService.findAll();

        //THEN
        assertEquals(10, all.size());
    }

    @Test
    void shouldFindDockReservationWithId999() {
        //EXPECTED
        assertNotNull(dockReservationService.findDockReservationByID(999L));
    }

    @Test
    void shouldSaveAndReturnSavedObject() {
        //EXPECTED
        assertNotNull(dockReservationService.save(dockReservation));

    }

    @Test
    void shouldDeleteDockReservation() {

        //WHEN

        dockReservationService.deleteById(999L);

        verify(reservationRepository, atLeastOnce()).deleteById(999L);
    }

    private void prepareMockData() {
        prepareBoatData();
        prepareDockData();
        prepareReservationData();
        prepareSingleDockReservation();
    }

    private void prepareSingleDockReservation() {

        Dock dock_with_reservation = new Dock(15000.0, "$$$$$$$");
        dock_with_reservation.setId(99L);
        Boat reserved_boat = new Boat("reserved boat", 1000.0, 999);
        reserved_boat.setId(99L);
        dockReservation = new DockReservation(dock_with_reservation, reserved_boat
                , Date.valueOf(LocalDate.now())
                , Date.valueOf(LocalDate.now()));
        dockReservation.setId(99L);
    }

    private void prepareReservationData() {
        List<DockReservation> listOfDockReservations = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Dock dock_with_reservation = new Dock(15000.0, "$$$$$$$");
            dock_with_reservation.setId((long) i);
            Boat reserved_boat = new Boat("reserved boat", 1000.0, 999);
            reserved_boat.setId((long) i);
            DockReservation dockReservation = new DockReservation(dock_with_reservation, reserved_boat
                    , Date.valueOf(LocalDate.now())
                    , Date.valueOf(LocalDate.now()));
            dockReservation.setId((long) i);
            listOfDockReservations.add(dockReservation);

        }
        when(reservationRepository.findAll()).thenReturn(listOfDockReservations);
        when(reservationRepository.findById(anyLong())).thenReturn(Optional.of(new DockReservation()));
        when(reservationRepository.save(any(DockReservation.class))).thenReturn(new DockReservation());
    }

    private void prepareBoatData() {
        List<Boat> boatsMocks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Boat boat = new Boat("TEST" + i, 100 + i, 100 + i);
            boat.setId((long) i + 1000);
            boatsMocks.add(boat);
        }
        Boat boat = new Boat("TEST with username", 1001, 112);
        boat.setOwner(new DockUser("test", "test", "ROLE_ADMIN"));
        boatsMocks.add(boat);
        when(boatRepository.findAll()).thenReturn(boatsMocks);
        when(boatRepository.findById(anyLong())).thenReturn(Optional.of(new Boat("TEST", 1231412, 123131)));
        when(boatRepository.save(any(Boat.class))).thenReturn(new Boat());
    }

    private void prepareDockData() {
        List<Dock> dockMocks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Dock dock = new Dock(100000 + i, "TEST DOCK " + i);
            dockMocks.add(dock);

        }
        when(dockRepository.findAll()).thenReturn(dockMocks);
        when(dockRepository.findById(anyLong())).thenReturn(Optional.of(new Dock(11334, "TEST DOCK with ID")));
        when(dockRepository.save(any(Dock.class))).thenReturn(new Dock());

    }


}
