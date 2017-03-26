package com.javarush.test.level39.lesson09.big01;

import java.nio.file.Paths;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("D:/test/"));
        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
        System.out.println(logParser.getUniqueIPs(null, new Date()));
        System.out.println(logParser.getIPsForUser("Eduard Petrovich Morozko", null, new Date()));
        System.out.println(logParser.getIPsForEvent(Event.SOLVE_TASK, new Date(), null));
        System.out.println(logParser.getIPsForStatus(Status.FAILED, new Date(), null));
        System.out.println();
        System.out.println(logParser.getAllUsers());
        System.out.println(logParser.getNumberOfUsers(new Date(), null));
        System.out.println(logParser.getNumberOfUserEvents("Amigo", null, null));
        System.out.println(logParser.getUsersForIP("12.12.12.12", new Date(), null));
        System.out.println(logParser.getLoggedUsers(new Date(), null));
        System.out.println(logParser.getDownloadedPluginUsers(null, null));
        System.out.println(logParser.getWroteMessageUsers(null, null));
        System.out.println(logParser.getSolvedTaskUsers(null, null));
        System.out.println(logParser.getSolvedTaskUsers(null, new Date(), 18));
        System.out.println(logParser.getDoneTaskUsers(null, null));
        System.out.println(logParser.getDoneTaskUsers(null, null, 15));
        System.out.println();
        System.out.println(logParser.getDatesForUserAndEvent("Amigo", Event.LOGIN, null, new Date()));
        System.out.println(logParser.getDatesWhenSomethingFailed( null, null));
        System.out.println(logParser.getDatesWhenErrorHappened( null, null));
        System.out.println(logParser.getDateWhenUserLoggedFirstTime("Eduard Petrovich Morozko", null, null));
        System.out.println(logParser.getDateWhenUserSolvedTask("Vasya Pupkin", 18, null, null));
        System.out.println(logParser.getDateWhenUserDoneTask("Vasya Pupkin", 15, null, null));
        System.out.println(logParser.getDatesWhenUserWroteMessage("Eduard Petrovich Morozko", new Date(), null));
        System.out.println(logParser.getDatesWhenUserDownloadedPlugin("Eduard Petrovich Morozko", null, null));
        System.out.println();
        System.out.println(logParser.getNumberOfAllEvents(null, new Date()));
        System.out.println(logParser.getAllEvents(new Date(), null));
        System.out.println(logParser.getEventsForIP("127.0.0.1", new Date(), null));
        System.out.println(logParser.getEventsForUser("Amigo", new Date(), null));
        System.out.println(logParser.getFailedEvents(null, new Date()));
        System.out.println(logParser.getErrorEvents(null, null));
        System.out.println(logParser.getNumberOfAttemptToSolveTask(18, null, null));
        System.out.println(logParser.getNumberOfSuccessfulAttemptToSolveTask(18, null, null));
        System.out.println(logParser.getAllSolvedTasksAndTheirNumber(null, new Date()));
        System.out.println(logParser.getAllDoneTasksAndTheirNumber(null, new Date()));
        System.out.println();
        System.out.println(logParser.execute("get ip for user = \"Amigo\""));
        System.out.println(logParser.execute("get ip for event = \"DOWNLOAD_PLUGIN\""));
        System.out.println(logParser.execute("get ip for date = \"30.08.2012 16:08:40\""));
        System.out.println(logParser.execute("get ip for status = \"OK\""));
        System.out.println(logParser.execute("get ip"));
        System.out.println();
        System.out.println(logParser.execute("get user for ip = \"192.168.100.2\""));
        System.out.println(logParser.execute("get user for event = \"WRITE_MESSAGE\""));
        System.out.println(logParser.execute("get user for date = \"30.08.2012 16:08:40\""));
        System.out.println(logParser.execute("get user for status = \"FAILED\""));
        System.out.println(logParser.execute("get user"));
        System.out.println();
        System.out.println(logParser.execute("get event for date = \"03.01.2014 03:45:23\""));
        System.out.println(logParser.execute("get event for ip = \"192.168.100.2\""));
        System.out.println(logParser.execute("get event for user = \"Amigo\""));
        System.out.println(logParser.execute("get event for status = \"ERROR\""));
        System.out.println(logParser.execute("get event"));
        System.out.println();
        System.out.println(logParser.execute("get date for user = \"Amigo\""));
        System.out.println(logParser.execute("get date for event = \"DONE_TASK\""));
        System.out.println(logParser.execute("get date for ip = \"192.168.100.2\""));
        System.out.println(logParser.execute("get date for status = \"ERROR\""));
        System.out.println(logParser.execute("get date"));
        System.out.println();
        System.out.println(logParser.execute("get status for user = \"Amigo\""));
        System.out.println(logParser.execute("get status for ip = \"192.168.100.2\""));
        System.out.println(logParser.execute("get status for date = \"30.08.2012 16:08:40\""));
        System.out.println(logParser.execute("get status for event = \"LOGIN\""));
        System.out.println(logParser.execute("get status"));
        System.out.println(logParser.execute("get ip for user = \"Eduard Petrovich Morozko\" and date between\n" +
                "\"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\""));
    }
}
