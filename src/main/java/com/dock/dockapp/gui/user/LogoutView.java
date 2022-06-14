package com.dock.dockapp.gui.user;

import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route(value = "logout")
public class LogoutView implements BeforeEnterObserver {
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        VaadinSession.getCurrent().getSession().invalidate();
        event.forwardTo("/login");
    }
}
