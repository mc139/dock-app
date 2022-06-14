package com.dock.dockapp.gui;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route("")
public class HomePageGui extends VerticalLayout {

    public HomePageGui() {
        Button loginButton = new Button("LOGIN", buttonClickEvent -> UI.getCurrent().navigate("login"));
        Button registerButton = new Button("REGISTER", buttonClickEvent -> UI.getCurrent().navigate("register"));

        setSizeFull();

        add(loginButton);
        add(registerButton);
        setHorizontalComponentAlignment(Alignment.CENTER, loginButton);
        setHorizontalComponentAlignment(Alignment.CENTER, registerButton);

    }
}
