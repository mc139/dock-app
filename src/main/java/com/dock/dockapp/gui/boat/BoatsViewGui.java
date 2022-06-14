package com.dock.dockapp.gui.boat;

import com.dock.dockapp.model.Boat;
import com.dock.dockapp.service.BoatService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@UIScope
@SpringComponent
@Route("gui/boats")
public class BoatsViewGui extends VerticalLayout {

    private final Grid<Boat> grid = new Grid<>(Boat.class);
    private final TextField filterText = new TextField();
    private BoatService boatService;
    private final AddBoatForm form = new AddBoatForm(this, boatService);

    @Autowired
    public BoatsViewGui(BoatService boatService, TopBarGui topBarGui) {
        this.boatService = boatService;
        setSizeFull();
        configureGrid();

        grid.setItems(boatService.findAll());
        HorizontalLayout layout = new HorizontalLayout(grid, form);
        add(topBarGui, getContent());
    }

    public BoatService getBoatService() {
        return boatService;
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
        grid.addColumn(Boat::getName).setHeader("NAME");
        grid.addColumn(Boat::getRegNo).setHeader("REGISTRATION NUMBER");
        grid.addColumn(Boat::getVolume).setHeader("VOLUME");
        grid.addColumn(Boat::getDockName).setHeader("DOCK NAME");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }


    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        Button addContactButton = new Button("add boat");
        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }


}
