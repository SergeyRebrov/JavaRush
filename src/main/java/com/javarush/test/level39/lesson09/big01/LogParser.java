package com.javarush.test.level39.lesson09.big01;

import com.javarush.test.level39.lesson09.big01.query.QLQuery;

import java.nio.file.Path;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class LogParser extends LogParserImplEventQuery implements QLQuery
{
    public LogParser(Path logDir)
    {
        super(logDir);
    }

    private void addStatusForParameters(Date after, Date before, Set<Status> set, String[] log)
    {
        Date currentLogDate = getCurrentLogDate(log[2]);

        if (isDateCorrect(after, before, currentLogDate))
        {
            set.add(Status.valueOf(log[4]));
        }
    }

    @Override
    public Set<Object> execute(String query)
    {
        Set<Object> set = new HashSet<>();
        String[] parameters = query.split(" ");
        String field1 = parameters[1];

        String field2 = "";
        String value1 = "";
        try
        {
            field2 = parameters[3];
            value1 = query.split("\"")[1];
        }
        catch (ArrayIndexOutOfBoundsException ignore)
        {
        }

        switch (field1)
        {
            case "ip":
                switch (field2)
                {
                    case "user":
                        set.addAll(getIPsForUser(value1, null, null));
                        break;
                    case "event":
                        set.addAll(getIPsForEvent(Event.valueOf(value1), null, null));
                        break;
                    case "date":
                        set.addAll(getUniqueIPs(getCurrentLogDate(value1), getCurrentLogDate(value1)));
                        break;
                    case "status":
                        set.addAll(getIPsForStatus(Status.valueOf(value1), null, null));
                        break;
                    default:
                        set.addAll(getUniqueIPs(null, null));
                        break;
                }
                return set;
            case "user":
                switch (field2)
                {
                    case "ip":
                        set.addAll(getUsersForIP(value1, null, null));
                        break;
                    case "event":
                        if (value1.equals(Event.LOGIN.toString()))
                            set.addAll(getLoggedUsers(null, null));
                        else if (value1.equals(Event.DOWNLOAD_PLUGIN.toString()))
                            set.addAll(getDownloadedPluginUsers(null, null));
                        else if (value1.equals(Event.WRITE_MESSAGE.toString()))
                            set.addAll(getWroteMessageUsers(null, null));
                        else if (value1.equals(Event.SOLVE_TASK.toString()))
                            set.addAll(getSolvedTaskUsers(null, null));
                        else if (value1.equals(Event.DONE_TASK.toString()))
                            set.addAll(getDoneTaskUsers(null, null));
                        break;
                    case "date":
                        set.addAll(getUsers(getCurrentLogDate(value1), getCurrentLogDate(value1)));
                        break;
                    case "status":
                        set.addAll(getUsersForStatus(Status.valueOf(value1), null, null));
                        break;
                    default:
                        set.addAll(getAllUsers());
                        break;
                }
                return set;
            case "date":
                Set<Date> dates = new HashSet<>();
                switch (field2)
                {
                    case "user":
                        for (String[] log : logs)
                        {
                            if (log[1].equals(value1))
                                if (isDateCorrect(null, null, getCurrentLogDate(log[2])))
                                    dates.add(getCurrentLogDate(log[2]));
                        }
                        set.addAll(dates);
                        break;
                    case "event":
                        for (String[] log : logs)
                        {
                            if (log[3].split(" ")[0].equals(value1))
                                if (isDateCorrect(null, null, getCurrentLogDate(log[2])))
                                    dates.add(getCurrentLogDate(log[2]));
                        }
                        set.addAll(dates);
                        break;
                    case "ip":
                        for (String[] log : logs)
                        {
                            if (log[0].equals(value1))
                                if (isDateCorrect(null, null, getCurrentLogDate(log[2])))
                                    dates.add(getCurrentLogDate(log[2]));
                        }
                        set.addAll(dates);
                        break;
                    case "status":
                        for (String[] log : logs)
                        {
                            if (log[4].equals(value1))
                                if (isDateCorrect(null, null, getCurrentLogDate(log[2])))
                                    dates.add(getCurrentLogDate(log[2]));
                        }
                        set.addAll(dates);
                        break;
                    default:
                        for (String[] log : logs)
                        {
                            dates.add(getCurrentLogDate(log[2]));
                        }
                        set.addAll(dates);
                        break;
                }
                return set;
            case "event":
                switch (field2)
                {
                    case "ip":
                        set.addAll(getEventsForIP(value1, null, null));
                        break;
                    case "user":
                        set.addAll(getEventsForUser(value1, null, null));
                        break;
                    case "date":
                        set.addAll(getAllEvents(getCurrentLogDate(value1), getCurrentLogDate(value1)));
                        break;
                    case "status":
                        if (value1.equals(Status.FAILED.toString()))
                            set.addAll(getFailedEvents(null, null));
                        else if (value1.equals(Status.ERROR.toString()))
                            set.addAll(getErrorEvents(null, null));
                        else if (value1.equals(Status.OK.toString()))
                            set.addAll(getOkEvents(null, null));
                        break;
                    default:
                        set.addAll(getAllEvents(null, null));
                        break;
                }
                return set;
            case "status":
                Set<Status> statuses = new HashSet<>();
                switch (field2)
                {
                    case "ip":
                        for (String[] log : logs)
                        {
                            if (log[0].equals(value1))
                                addStatusForParameters(null, null, statuses, log);
                        }
                        set.addAll(statuses);
                        break;
                    case "user":
                        for (String[] log : logs)
                        {
                            if (log[1].equals(value1))
                                addStatusForParameters(null, null, statuses, log);
                        }
                        set.addAll(statuses);
                        break;
                    case "date":
                        for (String[] log : logs)
                        {
                            addStatusForParameters(getCurrentLogDate(value1), getCurrentLogDate(value1), statuses, log);
                        }
                        set.addAll(statuses);
                        break;
                    case "event":
                        for (String[] log : logs)
                        {
                            if (log[3].split(" ")[0].equals(value1))
                                addStatusForParameters(null, null, statuses, log);
                        }
                        set.addAll(statuses);
                        break;
                    default:
                        for (String[] log : logs)
                        {
                            addStatusForParameters(null, null, statuses, log);
                        }
                        set.addAll(statuses);
                        break;
                }

                return set;
        }
        return null;
    }

}
