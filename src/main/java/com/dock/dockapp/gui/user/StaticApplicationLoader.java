package com.dock.dockapp.gui.user;

import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.ConcurrentHashMap;

@WebListener
public class StaticApplicationLoader implements ServletContextListener {
    public static ConcurrentHashMap<String, UserSessionObject> userSessions = new ConcurrentHashMap<String, UserSessionObject>();


}
