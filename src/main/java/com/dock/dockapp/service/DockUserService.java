package com.dock.dockapp.service;

import com.dock.dockapp.model.DockUser;
import com.dock.dockapp.repository.DockUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DockUserService {

    private final DockUserRepo dockUserRepo;

    @Autowired
    public DockUserService(DockUserRepo dockUserRepo) {
        this.dockUserRepo = dockUserRepo;
    }

    public DockUser registerUser(DockUser user) {
        return dockUserRepo.save(user);
    }

    public boolean isUsernameAvailable(String username) {
        return dockUserRepo.findByUserName(username) == null;
    }


    public DockUser findUserByUserName(String username) {
        return dockUserRepo.findByUserName(username);
    }

}
