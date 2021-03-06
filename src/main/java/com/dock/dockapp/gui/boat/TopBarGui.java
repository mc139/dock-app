package com.dock.dockapp.gui.boat;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@UIScope
@SpringComponent
public class TopBarGui extends Div {

    public TopBarGui() {
        MenuBar menuBar = new MenuBar();
        MenuItem manage_docks = menuBar.addItem("Manage Docks");
        MenuItem manage_boats = menuBar.addItem("Manage Boats");
        MenuItem manage_reservations = menuBar.addItem("Manage Reservations");
        MenuItem logout = menuBar.addItem("Logout");
        configureLogout(logout);
        configureDocks(manage_docks);
        SubMenu manage_boatsSubMenu = manage_boats.getSubMenu();
        configureBoats(manage_boatsSubMenu);
        configureReservations(manage_reservations);
        add(menuBar);
    }

    private void configureDocks(MenuItem manage_docks) {
        manage_docks.getSubMenu().addItem("All Docks", menuItemClickEvent -> UI.getCurrent().navigate("/gui/docks"));
    }

    private void configureBoats(SubMenu manage_boatsSubMenu) {
        manage_boatsSubMenu.addItem("All boats", menuItemClickEvent -> UI.getCurrent().navigate("gui/boats"));
        manage_boatsSubMenu.addItem("Your boats", menuItemClickEvent -> UI.getCurrent().navigate("dashboard/boats"));
    }

    private void configureReservations(MenuItem manage_reservations) {
        SubMenu manage_reservations_submenu = manage_reservations.getSubMenu();
        manage_reservations_submenu.addItem("Your Reservation", menuItemClickEvent -> UI.getCurrent().navigate("gui/reservations"));
        manage_reservations_submenu.addItem("All Reservation", menuItemClickEvent -> UI.getCurrent().navigate("gui/reservations"));
    }

    private void configureLogout(MenuItem logout) {
        logout.addClickListener(menuItemClickEvent -> {
            UI.getCurrent().getPage().executeJs("location.assign('logout')");

        });
    }

}
