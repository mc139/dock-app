package com.dock.dockapp.model;

import com.dock.dockapp.repository.DockUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class DockUserDetailServiceImpl implements UserDetailsService {

    private final DockUserRepo dockUserRepo;

    @Autowired
    public DockUserDetailServiceImpl(DockUserRepo dockUserRepo) {
        this.dockUserRepo = dockUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return dockUserRepo.findByUserName(username);
    }


}
