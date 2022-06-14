package com.dock.dockapp.repository;

import com.dock.dockapp.model.Boat;
import com.dock.dockapp.model.DockReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DockReservationRepo extends JpaRepository<DockReservation, Long> {

    @Query(value = "SELECT * from dock_reservation where boat_id = ?1", nativeQuery = true)
    Boat findReservationWithBoatByBoatId(Long id);
}
