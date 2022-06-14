package com.dock.dockapp.gui.user;

import java.io.Serializable;

public class UserSessionObject implements Serializable {

//    private static final long serialVersionUID = 1L;
//
//    private Date sessionDate = new Date();
//    private VaadinSession session;
//    private String 			username = "";
//
//    public UserSessionObject(){}
//
//    public String getUsername() {
//        return username;
//    }
//    public void setUsername(String username) {
//        this.username = username;
//    }
//    public Date getSessionDate() {
//        return sessionDate;
//    }
//    public void setSessionDate(Date sessionDate) {
//        this.sessionDate = sessionDate;
//    }
//    public VaadinSession getSession() {
//        return session;
//    }
//    public void setSession(VaadinSession session) {
//        this.session = session;
//    }
////    private void addSessionForUserWithUsername(String username){
////        if(StaticApplicationLoader.userSessions.containsKey(username)){
////            removeSessionForUserWithUsername(username); //Clear it first
////        }
////
////        UserSessionObject userSession = new UserSessionObject();
////        userSession.setSessionDate(java.sql.Date.valueOf(LocalDate.now()));
////        userSession.setUsername(username);
////        userSession.setSession(new VaadinSession().getSession());
////
////        StaticApplicationLoader.userSessions.put(username, userSession);
////    }
////
////    private void removeSessionForUserWithUsername(String username){
////        //Remove from UserSession handler
////        if(StaticApplicationLoader.userSessions.containsKey(username)){
////            VaadinService.getCurrent().closeSession(StaticApplicationLoader.userSessions.get(username).getSession());
////            StaticApplicationLoader.userSessions.remove(username);
////        }
////    }
}
