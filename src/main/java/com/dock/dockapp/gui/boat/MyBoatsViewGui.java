package com.dock.dockapp.gui.boat;

import com.dock.dockapp.model.Boat;
import com.dock.dockapp.service.BoatService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@UIScope
@SpringComponent
@Route("dashboard/boats")
public class MyBoatsViewGui extends VerticalLayout {

    private final Grid<Boat> grid = new Grid<>(Boat.class);
    private BoatService boatService;
    private final AddMyBoatForm form = new AddMyBoatForm(this, boatService);

    @Autowired
    public MyBoatsViewGui(BoatService boatService, TopBarGui topBar) {
        this.boatService = boatService;
        setSizeFull();
        configureGrid();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        grid.setItems(boatService.findAllByUserName(currentPrincipalName));
        HorizontalLayout layout = new HorizontalLayout(grid, this.form);
        add(topBar, getContent());
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }

    public BoatService getBoatService() {
        return boatService;
    }

    private void configureGrid() {
        grid.addClassNames("my-boats-grid");
        grid.setSizeFull();
        grid.removeAllColumns();
        grid.addColumn(Boat::getName).setHeader("NAME");
        grid.addColumn(Boat::getRegNo).setHeader("REGISTRATION NUMBER");
        grid.addColumn(Boat::getVolume).setHeader("VOLUME");
        grid.addColumn(Boat::getDockName).setHeader("DOCK NAME");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }
}
