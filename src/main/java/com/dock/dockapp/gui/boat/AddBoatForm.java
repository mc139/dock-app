package com.dock.dockapp.gui.boat;

import com.dock.dockapp.model.Boat;
import com.dock.dockapp.model.BoatSize;
import com.dock.dockapp.service.BoatService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToDoubleConverter;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.spring.annotation.UIScope;

import java.util.Optional;

@UIScope
public class AddBoatForm extends FormLayout {

    private final TextField name = new TextField("Boat Name");
    private final TextField volume = new TextField("Volume in m3");
    private final TextField regNo = new TextField("Registration number");
    private final ComboBox<BoatSize> size = new ComboBox<>("Size");
    private final Button save = new Button("SAVE");
    private final Button cancel = new Button("CANCEL");
    private final Button delete = new Button("DELETE");
    private final Binder<Boat> boatBinder = new Binder<>(Boat.class);
    private final BoatsViewGui boatsViewGui;
    private BoatService boatService;
    private Boat boat = new Boat();

    public AddBoatForm(BoatsViewGui boatsViewGui, BoatService boatService) {
        this.boatsViewGui = boatsViewGui;
        this.boatService = BoatServiceBean.getBoatService();
        setSizeUndefined();
        configureButtons();
        configureBoatBinder();
        cofigureSave();
        configureDelete();
        this.boatService = boatService;
    }

    private void configureButtons() {
        HorizontalLayout buttons = new HorizontalLayout(save, delete, cancel);
        size.setItems(BoatSize.values());
        add(name, volume, regNo, size, buttons);
    }

    private void configureDelete() {
        delete.addClickListener(e -> delete());
    }

    private void cofigureSave() {
        save.addClickListener(e -> {
            try {
                save();
            } catch (ValidationException ex) {
                ex.printStackTrace();
            }
        });
    }


    private void configureBoatBinder() {
        boatBinder.forField(volume)
                .withConverter(new StringToDoubleConverter("It must be a number!"))
                .bind(Boat::getVolume, Boat::setVolume);
        boatBinder.forField(regNo)
                .withConverter(new StringToIntegerConverter("It must be a number!"))
                .bind(Boat::getRegNo, Boat::setRegNo);
        boatBinder.bindInstanceFields(this);

        boatBinder.setBean(this.boat);
        boatBinder.addValueChangeListener(e -> boatBinder.setBean(boat));
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
        boatBinder.setBean(this.boat);

    }

    public void delete() {
        System.out.println("Clicked delete");
    }

    public void save() throws ValidationException {
        Optional.ofNullable(boat).ifPresent(action -> boatsViewGui.getBoatService().save(boat));
    }

}
