package com.dock.dockapp.gui.user;

import com.dock.dockapp.model.DockUser;
import com.dock.dockapp.service.DockUserService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringComponent
@Route("register")
public class RegisterView extends Composite {

    private final DockUserService dockUserService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterView(DockUserService dockUserService) {
        this.dockUserService = dockUserService;
        this.passwordEncoder = new BCryptPasswordEncoder();

    }

    @Override
    protected Component initContent() {

        TextField username = new TextField("Username");
        PasswordField password = new PasswordField("Password");
        PasswordField confirm_password = new PasswordField("Confirm password");
        VerticalLayout verticalLayout = new VerticalLayout(
                new H2("Register"),
                username,
                password,
                confirm_password,
                new Button("Register", event -> register(
                        username.getValue()
                        , password.getValue()
                        , confirm_password.getValue()))
        );
        verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        return verticalLayout;

    }

    private void register(String userName, String password, String confirmPassword) {
        if (userName.trim().isEmpty()) {
            Notification.show("Your username cannot be empty!");
        } else if (!dockUserService.isUsernameAvailable(userName)) {
            Notification.show("This username is already in use!");
        } else if (password.isEmpty()) {
            Notification.show("Your password cannot be empty!");
        } else if (!password.equals(confirmPassword)) {
            Notification.show("Your passwords cannot be different!");
        } else {
            dockUserService.registerUser(new DockUser(userName, passwordEncoder.encode(password), "ROLE_ADMIN"));
            Notification.show("REGISTRATION SUCCEEDED!");

        }
    }
}
