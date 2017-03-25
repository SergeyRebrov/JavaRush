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

    }
}
