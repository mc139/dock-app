package com.dock.dockapp.gui.boat;

import com.dock.dockapp.service.BoatService;
import org.springframework.stereotype.Component;

@Component
public class BoatServiceBean {

    private static BoatService boatService;

    public static BoatService getBoatService() {
        return boatService;
    }
}
