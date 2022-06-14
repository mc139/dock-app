package com.dock.dockapp.repository;

import com.dock.dockapp.model.Dock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DockRepo extends JpaRepository<Dock, Long> {

    @Transactional
    @Modifying
    @Query(value = "update dock set dock.capacity_left = ?1 where dock.id = ?2", nativeQuery = true)
    void setCapacityLeft(double capacityLeft, Long dockId);
}
