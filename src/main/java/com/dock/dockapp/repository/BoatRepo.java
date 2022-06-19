package com.dock.dockapp.repository;

import com.dock.dockapp.model.Boat;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;

@Repository
public interface BoatRepo extends JpaRepository<Boat, Long> {

    @Transactional
    @Modifying
    @Query(value = "update boat set dock_id = ?1 where id = ?2", nativeQuery = true)
    void updateDock(Long dockId, Long boatId);

    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    @Override
    Boat save(Boat boat);


}
