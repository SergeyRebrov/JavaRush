package com.javarush.test.level39.lesson09.big01;

import com.javarush.test.level39.lesson09.big01.query.UserQuery;

import java.nio.file.Path;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sergey on 25.03.2017.
 */
public abstract class LogParserImplUserQuery extends LogParserImplIPQuery implements UserQuery
{

    protected LogParserImplUserQuery(Path logDir)
    {
        super(logDir);
    }

    @Override
    public Set<String> getAllUsers()
    {
        Set<String> users = new HashSet<>();
        for (String[] log : logs)
        {
            addStringForParameters(null, null, users, log, 1);
        }

        return users;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before)
    {
        Set<String> users = new HashSet<>();
        for (String[] log : logs)
        {
            addStringForParameters(after, before, users, log, 1);
        }

        return users.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before)
    {
        Set<String> events = new HashSet<>();
        for (String[] log : logs)
        {
            if (user == null || user.equals(log[1]))
                addStringForParameters(after, before, events, log, 3);
        }

        return events.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before)
    {
        Set<String> users = new HashSet<>();
        for (String[] log : logs)
        {
            if (ip == null || ip.equals(log[0]))
                addStringForParameters(after, before, users, log, 1);
        }

        return users;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before)
    {
        Set<String> users = new HashSet<>();
        for (String[] log : logs)
        {
            if (log[3].equals(Event.LOGIN.toString()))
                addStringForParameters(after, before, users, log, 1);
        }

        return users;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before)
    {
        Set<String> users = new HashSet<>();
        for (String[] log : logs)
        {
            if (log[3].equals(Event.DOWNLOAD_PLUGIN.toString()))
                addStringForParameters(after, before, users, log, 1);
        }

        return users;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before)
    {
        Set<String> users = new HashSet<>();
        for (String[] log : logs)
        {
            if (log[3].equals(Event.WRITE_MESSAGE.toString()))
                addStringForParameters(after, before, users, log, 1);
        }

        return users;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before)
    {
        Set<String> users = new HashSet<>();
        for (String[] log : logs)
        {
            if (log[3].split(" ")[0].equals(Event.SOLVE_TASK.toString()))
                addStringForParameters(after, before, users, log, 1);
        }

        return users;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task)
    {
        Set<String> users = new HashSet<>();
        for (String[] log : logs)
        {
            if (log[3].split(" ")[0].equals(Event.SOLVE_TASK.toString()) && Integer.parseInt(log[3].split(" ")[1]) == task)
                addStringForParameters(after, before, users, log, 1);
        }

        return users;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before)
    {
        Set<String> users = new HashSet<>();
        for (String[] log : logs)
        {
            if (log[3].split(" ")[0].equals(Event.DONE_TASK.toString()))
                addStringForParameters(after, before, users, log, 1);
        }

        return users;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task)
    {
        Set<String> users = new HashSet<>();
        for (String[] log : logs)
        {
            if (log[3].split(" ")[0].equals(Event.DONE_TASK.toString()) && Integer.parseInt(log[3].split(" ")[1]) == task)
                addStringForParameters(after, before, users, log, 1);
        }

        return users;
    }
}
