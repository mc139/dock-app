package com.dock.dockapp.gui.reservation;

import com.dock.dockapp.gui.boat.TopBarGui;
import com.dock.dockapp.model.Dock;
import com.dock.dockapp.model.DockReservation;
import com.dock.dockapp.service.DockReservationService;
import com.vaadin.flow.component.grid.Grid;
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
        Grid<DockReservation> dockReservationGrid = getGrid(dockReservationService);
        setSizeFull();
        add(topBarGui, addReservationForm,dockReservationGrid);
    }

    private Grid<DockReservation> getGrid(DockReservationService dockReservationService) {
        Grid<DockReservation> dockReservationGrid = new Grid<DockReservation>();
        dockReservationGrid.addClassNames("boats-grid");
        dockReservationGrid.setSizeFull();
        dockReservationGrid.removeAllColumns();
        dockReservationGrid.addColumn(dockReservation -> dockReservation.getDock().getId()).setHeader("DOCK ID");
        dockReservationGrid.addColumn(dockReservation -> dockReservation.getBoat().getId()).setHeader("BOAT ID");
        dockReservationGrid.addColumn(DockReservation::getDateFrom).setHeader("DATE FROM");
        dockReservationGrid.addColumn(DockReservation::getDateTo).setHeader("DATE TO");
        dockReservationGrid.getColumns().forEach(col -> col.setAutoWidth(true));
        dockReservationGrid.setItems(dockReservationService.findAll());
        return dockReservationGrid;
    }
}
