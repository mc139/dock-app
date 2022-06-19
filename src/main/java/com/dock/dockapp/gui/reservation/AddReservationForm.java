package com.dock.dockapp.gui.reservation;

import com.dock.dockapp.model.Boat;
import com.dock.dockapp.model.Dock;
import com.dock.dockapp.model.DockReservation;
import com.dock.dockapp.service.BoatService;
import com.dock.dockapp.service.DockReservationService;
import com.dock.dockapp.service.DockService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringComponent
@UIScope
public class AddReservationForm extends FormLayout {

    private final DockReservationService dockReservationService;
//    private final ReservationViewGui reservationViewGui;

    private DatePicker datePickerFrom = new DatePicker("Select a date:");
    private DatePicker datePickerTo = new DatePicker("Select a date:");
    private Button save = new Button("SAVE");
    private DockService dockService;
    private BoatService boatService;
    private ComboBox<Dock> docks = new ComboBox<>("Dock");
    private ComboBox<Boat> boats = new ComboBox<>("Boat");

    private Dock dock = new Dock();
    private Boat boat = new Boat();
    private DockReservation dockReservation = new DockReservation();

    private Binder<DockReservation> dockReservationBinder = new Binder<>(DockReservation.class);

    @Autowired
    public AddReservationForm(DockReservationService dockReservationService, DockService dockService, BoatService boatService) {
        this.dockReservationService = dockReservationService;
        this.dockService = dockService;
        this.boatService = boatService;
        setSizeUndefined();
        HorizontalLayout buttons = new HorizontalLayout(save);
        datePickerFrom.setMin(LocalDate.now());
        add(docks, boats, datePickerFrom, datePickerTo, buttons);
        configureDocks(dockService);
        configureBoats(boatService);
        configureSave();

    }

    private void configureDocks(DockService dockService) {
        List<Dock> all = dockService.findAll();
        docks.setItems(all);
        docks.getElement().setAttribute("required", true);
    }

    private void configureBoats(BoatService boatService) {
        List<Boat> allBoats = boatService.findAll();
        boats.setItems(allBoats);
    }

    private void configureSave() {
        save.addClickListener(e -> save());
    }

    public void save() {
        if (!Arrays.asList(docks.getValue(), boats.getValue(), datePickerFrom.getValue(), datePickerTo.getValue()).contains(null)) {
            DockReservation dockReservation = new DockReservation(docks.getValue()
                    , boats.getValue()
                    , Date.valueOf(datePickerFrom.getValue())
                    , Date.valueOf(datePickerTo.getValue()));
            dockReservationService.save(dockReservation);
//            reservationViewGui.getDockReservationGrid().getDataProvider().refreshAll();
        } else {
            Notification.show("Please provide all the details!").setPosition(Notification.Position.MIDDLE);
        }

    }

}
