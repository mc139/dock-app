package com.dock.dockapp.repository;

import com.dock.dockapp.model.DockUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DockUserRepo extends JpaRepository<DockUser, Long> {

    DockUser findByUserName(String username);
}
