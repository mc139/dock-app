package com.dock.dockapp.gui.dock;

import com.dock.dockapp.model.Dock;
import com.dock.dockapp.service.DockService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToDoubleConverter;

import java.util.Optional;

public class AddDockForm extends FormLayout {

    private final DockViewGui dockViewGui;
    private TextField name = new TextField("Dock name");
    private TextField capacity = new TextField("Capacity in M3");
    private Button save = new Button("SAVE");
    private Button cancel = new Button("CANCEL");
    private Button delete = new Button("DELETE");
    private Binder<Dock> dockBinder = new Binder<Dock>(Dock.class);
    private Dock dock = new Dock();
    private DockService dockService;

    public AddDockForm(DockService dockService, DockViewGui dockViewGui) {
        this.dockService = dockService;
        this.dockViewGui = dockViewGui;
        configureDockBinder();
        setSizeUndefined();
        configureButtons();
        configureSave();
        configureDelete();
    }

    private void configureButtons() {
        HorizontalLayout buttons = new HorizontalLayout(save, cancel);
        add(name, capacity, buttons);
    }

    private void configureDelete() {
        delete.addClickListener(e -> delete());
    }

    private void configureSave() {
        save.addClickListener(e -> {
            try {
                save();
            } catch (ValidationException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void delete() {
        System.out.println("Clicked delete");
    }

    private void save() throws ValidationException {
        Optional.ofNullable(dock).ifPresent(action -> dockViewGui.getDockService().save(dock));
    }

    public void setDock(Dock dock) {
        this.dock = dock;
        dockBinder.setBean(this.dock);

    }

    private void configureDockBinder() {
        dockBinder.forField(name).bind(Dock::getName, Dock::setName);
        dockBinder.forField(capacity)
                .withConverter(new StringToDoubleConverter("It must be a number!"))
                .bind(Dock::getCapacity, Dock::setCapacity);
        dockBinder.setBean(this.dock);
        dockBinder.addValueChangeListener(event -> dockBinder.setBean(dock));
    }


}
