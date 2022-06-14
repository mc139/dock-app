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
import lombok.SneakyThrows;

import java.util.Optional;

@UIScope
public class AddMyBoatForm extends FormLayout {

    private TextField name = new TextField("Boat Name");
    private TextField volume = new TextField("Volume in m3");
    private TextField regNo = new TextField("Registration number");
    private ComboBox<BoatSize> size = new ComboBox<>("Size");
    private Button save = new Button("SAVE");
    private Button cancel = new Button("CANCEL");
    private Button delete = new Button("DELETE");
    private Binder<Boat> boatBinder = new Binder<>(Boat.class);

    private BoatService boatService;
    private Boat boat = new Boat();

    private MyBoatsViewGui myBoatsViewGui;

    public AddMyBoatForm(MyBoatsViewGui myBoatsViewGui, BoatService boatService) {
        this.myBoatsViewGui = myBoatsViewGui;

        this.boatService = BoatServiceBean.getBoatService();
        setSizeUndefined();
        HorizontalLayout buttons = new HorizontalLayout(save, delete, cancel);
        add(name, volume, regNo, size, buttons);
        size.setItems(BoatSize.values());
        configureBoatBinder();
        boatBinder.addValueChangeListener(e -> boatBinder.setBean(boat));

        save.addClickListener(e -> {
            try {
                save();
            } catch (ValidationException ex) {
                ex.printStackTrace();
            }
        });
        delete.addClickListener(e -> delete());
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

    }

    public void setBoat(Boat boat) {
        this.boat = boat;
        boatBinder.setBean(this.boat);

    }

    public void delete() {
        System.out.println("Clicked delete");
    }

    public void save() throws ValidationException {

        Optional.ofNullable(boat).ifPresent(action -> myBoatsViewGui.getBoatService().save(boat));


    }

    @SneakyThrows
    private void validateAndSave() {
//        setBoat(boatBinder.getBean());
        System.out.println(boat);
        boatBinder.writeBean(boat);
        boatService.save(boat);

    }
}
