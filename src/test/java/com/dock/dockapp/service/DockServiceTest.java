package com.dock.dockapp.service;

import com.dock.dockapp.model.Dock;
import com.dock.dockapp.repository.DockRepo;
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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class DockServiceTest {

    @Mock
    private DockRepo dockRepository;

    @Spy
    @InjectMocks
    private DockService dockService;

    @BeforeEach
    public void prepareMock() {
        prepareMockData();
    }

    @Test
    void shouldFindAllDocksAndReturn10() {
        //WHEN
        List<Dock> all = dockService.findAll();
        //THEN
        Assertions.assertEquals(10, all.size());
    }

    @Test
    void shouldFindADockWithId999() {
        Assertions.assertNotNull(dockService.findDockById(999L));

    }

    @Test
    void shouldSaveDockAndReturnNotNullObject() {
        //GIVEN
        Dock dockToBeSaved = new Dock(1222, "Dock TBS");
        dockToBeSaved.setId(789L);

        //WHEN
        Dock save = dockService.save(dockToBeSaved);

        //THEN
        Assertions.assertNotNull(save);
    }

    @Test
    void updateCapacityLeft() {
        //GIVEN
        Dock dockToUpdated = new Dock(1222, "Dock TBS");
        dockToUpdated.setId(789L);

        //THEN
        dockService.updateCapacityLeft(dockToUpdated);

        //THEN
        verify(dockRepository, atLeastOnce()).setCapacityLeft(anyDouble(), anyLong());
    }

    @Test
    void deleteById() {
        //GIVEN
        Dock dockToDeleted = new Dock(1222, "Dock TBS");
        dockToDeleted.setId(789L);
        dockToDeleted.setId(15687L);

        //WHEN
        dockService.deleteById(15687L);

        //THEN
        verify(dockRepository, atLeastOnce()).deleteById(15687L);

    }

    private void prepareMockData() {
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