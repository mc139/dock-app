package com.dock.dockapp.gui.reservation;

import com.dock.dockapp.gui.boat.TopBarGui;
import com.dock.dockapp.service.DockReservationService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

//@Component
@SpringComponent
@UIScope
@Route("gui/reservations")
public class ReservationViewGui extends VerticalLayout {

    private final DockReservationService dockReservationService;
    private final AddReservationForm addReservationForm;

    @Autowired
    public ReservationViewGui(DockReservationService dockReservationService,
                              AddReservationForm addReservationForm,
                              TopBarGui topBarGui) {
        this.dockReservationService = dockReservationService;
        this.addReservationForm = addReservationForm;
        setSizeFull();
        add(topBarGui, addReservationForm);
    }
}
