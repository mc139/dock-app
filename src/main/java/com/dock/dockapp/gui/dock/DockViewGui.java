package com.dock.dockapp.gui.dock;

import com.dock.dockapp.gui.boat.TopBarGui;
import com.dock.dockapp.model.Dock;
import com.dock.dockapp.service.DockService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
@Route("gui/docks")
public class DockViewGui extends VerticalLayout {

    private final Grid<Dock> grid = new Grid<>(Dock.class);
    private DockService dockService;
    private AddDockForm form = new AddDockForm(dockService, this);

    @Autowired
    public DockViewGui(DockService dockService, TopBarGui topBarGui) {
        this.dockService = dockService;
        setSizeFull();
        configureGrid();
        getContent();
        add(topBarGui, getContent());
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }

    private void configureGrid() {
        grid.addClassNames("boats-grid");
        grid.setSizeFull();
        grid.removeAllColumns();
        grid.addColumn(Dock::getName).setHeader("DOCK NAME");
        grid.addColumn(Dock::getNumberOfDockBoats).setHeader("Number of boats");
        grid.addColumn(Dock::getCapacityAsString).setHeader("Total capacity");
        grid.addColumn(Dock::getCapacityLeftAsString).setHeader("Capacity left");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.setItems(dockService.findAll());
    }

    public DockService getDockService() {
        return dockService;
    }
}
