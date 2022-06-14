package com.dock.dockapp.service;

import com.dock.dockapp.model.Boat;
import com.dock.dockapp.model.DockUser;
import com.dock.dockapp.repository.BoatRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class BoatServiceTest {

    @Mock
    private BoatRepo boatRepository;

    @Spy
    @InjectMocks
    private BoatService boatService;

    @BeforeEach
    public void prepareMock() {
        prepareMockData();
    }

    @Test
    void shouldFindAllByUserName() {

        //WHEN
        List<Boat> test = boatService.findAllByUserName("test");

        //THEN
        assertEquals(1, test.size());

    }

    @Test
    void shouldFindBoatWithId999() {
        //EXPECTED
        Assertions.assertNotNull(boatService.findById(999L));
    }

    @Test
    void shouldFindAllAndReturn11() {
        //WHEN
        List<Boat> all = boatService.findAll();
        //THEN
        assertEquals(11, all.size());
    }

    @Test
    void shouldSaveAndReturnSavedObject() {
        //GIVEN
        Boat boatToBeSaved = new Boat("TEST BOAT", 1201, 112223);
        boatToBeSaved.setId(95599L);
        //EXPECTED
        assertNotNull(boatService.save(boatToBeSaved));
    }

    @Test
    void shouldDeleteBoat() {

        //GIVEN
        Boat boatToBeDeleted = new Boat("TEST BOAT", 133201, 11823);
        boatToBeDeleted.setId(9123123L);

        //WHEN
        boatService.deleteById(9123123L);

        //THEN
        verify(boatRepository).deleteById(9123123L);

    }

    private void prepareMockData() {
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
}