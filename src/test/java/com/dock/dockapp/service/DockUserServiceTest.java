package com.dock.dockapp.service;

import com.dock.dockapp.model.DockUser;
import com.dock.dockapp.repository.DockUserRepo;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class DockUserServiceTest {

    @Mock
    private DockUserRepo dockUserRepository;

    @Spy
    @InjectMocks
    private DockUserService dockUserService;

    private DockUser dockUser;

    @BeforeEach
    void prepareMock() {
        prepareDockUserData();
    }

    @Test
    void shouldRegisterUserAndReturnDockUserObject() {

        Assertions.assertEquals(dockUser, dockUserService.registerUser(dockUser));
    }

    @Test
    void shouldFindUserByUserNameAndReturnNullWhenNameIsNo_USER() {
        Assertions.assertNull(dockUserService.findUserByUserName("NO_USER"));
    }

    @Test
    void shouldFindUserByUserNameAndReturnDockUserObjectWhenNameIsTEST() {
        Assertions.assertNotNull(dockUserService.findUserByUserName("TEST"));
    }

    @Test
    void shouldFindADockUserAndReturnTrue() {
        //EXPECTED
        Assertions.assertTrue(dockUserService.isUsernameAvailable("TEST2"));

    }

    @Test
    void shouldFindADockUserAndReturnFalse() {
        //EXPECTED
        Assertions.assertFalse(dockUserService.isUsernameAvailable("TEST5"));

    }

    private void prepareDockUserData() {
        List<DockUser> dockUsers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DockUser user = new DockUser("ssdas" + 1, "sdsad" + i, "USER_ADMIN");
            dockUsers.add(user);
        }
        when(dockUserRepository.findAll()).thenReturn(dockUsers);
        when(dockUserRepository.findByUserName("test"))
                .thenReturn(new DockUser("test", "testPass", "ROLE_ADMIN"));
        dockUser = new DockUser("TEST", "TestPass", "ROLE_ADMIN");
        when(dockUserRepository.findById(1L)).thenReturn(Optional.of(dockUser));
        when(dockUserRepository.save(any(DockUser.class))).thenReturn(dockUser);
        when(dockUserRepository.findByUserName("NO_USER")).thenReturn(null);
        when(dockUserRepository.findByUserName("TEST")).thenReturn(dockUser);
        when(dockUserRepository.findByUserName("TEST2")).thenReturn(null);
        when(dockUserRepository.findByUserName("TEST5")).thenReturn(dockUser);

    }

}