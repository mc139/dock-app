package com.dock.dockapp.gui.user;

import com.dock.dockapp.gui.boat.TopBarGui;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Route("dashboard")
public class UserDashBoardGui extends VerticalLayout {

    private Button myBoats = new Button("My Boats");
    private Button allBoats = new Button("All Boats");
    private Button myReservation = new Button("My Reservations");
    private Button allReservations = new Button("All reservations");

    public UserDashBoardGui(TopBarGui topBarGui) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        add(topBarGui, myBoats, allBoats, myReservation, allReservations);
        myBoats.addClickListener(buttonClickEvent -> UI.getCurrent().navigate("dashboard/boats"));
        allBoats.addClickListener(buttonClickEvent -> UI.getCurrent().navigate("gui/boats"));
        myReservation.addClickListener(buttonClickEvent -> UI.getCurrent().navigate("gui/reservations"));
        allReservations.addClickListener(buttonClickEvent -> UI.getCurrent().navigate("gui/reservations"));

    }
}
