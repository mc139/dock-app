package com.dock.dockapp.gui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;

public class AppLayoutBasic extends AppLayout {

    public AppLayoutBasic() {
        Button logoutButton = new Button("LOGOUT", buttonClickEvent -> UI.getCurrent().navigate("/logout"));
        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("MyApp");
        title.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");

        Tabs tabs = getTabs();

        addToDrawer(tabs);
        addToNavbar(toggle, title);
        addToNavbar(logoutButton);
    }

    public Tabs getTabs() {
        Tabs tabs = new Tabs();
        tabs.add(new Tab("test label"));
        return tabs;
    }
}