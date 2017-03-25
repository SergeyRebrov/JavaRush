package com.javarush.test.level39.lesson09.big01;

import com.javarush.test.level39.lesson09.big01.query.DateQuery;
import com.javarush.test.level39.lesson09.big01.query.IPQuery;
import com.javarush.test.level39.lesson09.big01.query.UserQuery;

import java.io.*;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery, UserQuery, DateQuery
{
    private Path logDir;
    private List<String[]> logs = new ArrayList<>();
    private DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public LogParser(Path logDir)
    {
        this.logDir = logDir;
    }

    private void loadLogs()
    {
        for (File file : logDir.toFile().listFiles())
        {
            if (file.getName().endsWith(".log"))
            {
                try (BufferedReader reader = new BufferedReader(new FileReader(file)))
                {
                    while (reader.ready())
                        logs.add(reader.readLine().split("\\t"));
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    private void addDataForParameters(Date after, Date before, Set<String> set, String[] log, int parameter)
    {
        Date currentLogDate = getCurrentLogDate(log[2]);

        if (isDateCorrect(after, before, currentLogDate))
        {
            set.add(log[parameter]);
        }
    }

    private Date getCurrentLogDate(String logDate)
    {
        Date currentLogDate = null;
        try
        {
            currentLogDate = dateFormat.parse(logDate);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return currentLogDate;
    }

    private boolean isDateCorrect(Date after, Date before, Date currentLogDate)
    {
        if (after == null && before == null)
            return true;
        else if (after == null)
        {
            if (currentLogDate.getTime() <= before.getTime())
                return true;
        } else if (before == null)
        {
            if (currentLogDate.getTime() >= after.getTime())
                return true;
        } else if (currentLogDate.getTime() >= after.getTime() && currentLogDate.getTime() <= before.getTime())
        {
            return true;
        }
        return false;
    }

    private Date getDate(Date after, Date before, Date date, String logDate)
    {
        if (isDateCorrect(after, before, getCurrentLogDate(logDate)))
            if (date == null)
                date = (getCurrentLogDate(logDate));
            else if (date.getTime() > getCurrentLogDate(logDate).getTime())
                date = (getCurrentLogDate(logDate));
        return date;
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before)
    {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before)
    {
        loadLogs();
        Set<String> IPs = new HashSet<>();
        for (String[] log : logs)
        {
            addDataForParameters(after, before, IPs, log, 0);
        }

        return IPs;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before)
    {
        loadLogs();
        Set<String> IPs = new HashSet<>();
        for (String[] log : logs)
        {
            if (user == null || user.equals(log[1]))
                addDataForParameters(after, before, IPs, log, 0);
        }

        return IPs;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before)
    {
        loadLogs();
        Set<String> IPs = new HashSet<>();
        for (String[] log : logs)
        {
            String currentEvent = log[3].split(" ")[0];
            if (event == null || event.toString().equals(currentEvent))
                addDataForParameters(after, before, IPs, log, 0);
        }

        return IPs;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before)
    {
        loadLogs();
        Set<String> IPs = new HashSet<>();
        for (String[] log : logs)
        {
            if (status == null || status.toString().equals(log[4]))
                addDataForParameters(after, before, IPs, log, 0);
        }

        return IPs;
    }

    @Override
    public Set<String> getAllUsers()
    {
        loadLogs();
        Set<String> users = new HashSet<>();
        for (String[] log : logs)
        {
            addDataForParameters(null, null, users, log, 1);
        }

        return users;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before)
    {
        loadLogs();
        Set<String> users = new HashSet<>();
        for (String[] log : logs)
        {
            addDataForParameters(after, before, users, log, 1);
        }

        return users.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before)
    {
        loadLogs();
        Set<String> events = new HashSet<>();
        for (String[] log : logs)
        {
            if (user == null || user.equals(log[1]))
                addDataForParameters(after, before, events, log, 3);
        }

        return events.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before)
    {
        loadLogs();
        Set<String> users = new HashSet<>();
        for (String[] log : logs)
        {
            if (ip == null || ip.equals(log[0]))
                addDataForParameters(after, before, users, log, 1);
        }

        return users;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before)
    {
        loadLogs();
        Set<String> users = new HashSet<>();
        for (String[] log : logs)
        {
            if (log[3].equals(Event.LOGIN.toString()))
                addDataForParameters(after, before, users, log, 1);
        }

        return users;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before)
    {
        loadLogs();
        Set<String> users = new HashSet<>();
        for (String[] log : logs)
        {
            if (log[3].equals(Event.DOWNLOAD_PLUGIN.toString()))
                addDataForParameters(after, before, users, log, 1);
        }

        return users;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before)
    {
        loadLogs();
        Set<String> users = new HashSet<>();
        for (String[] log : logs)
        {
            if (log[3].equals(Event.WRITE_MESSAGE.toString()))
                addDataForParameters(after, before, users, log, 1);
        }

        return users;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before)
    {
        loadLogs();
        Set<String> users = new HashSet<>();
        for (String[] log : logs)
        {
            if (log[3].split(" ")[0].equals(Event.SOLVE_TASK.toString()))
                addDataForParameters(after, before, users, log, 1);
        }

        return users;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task)
    {
        loadLogs();
        Set<String> users = new HashSet<>();
        for (String[] log : logs)
        {
            if (log[3].split(" ")[0].equals(Event.SOLVE_TASK.toString()) && Integer.parseInt(log[3].split(" ")[1]) == task)
                addDataForParameters(after, before, users, log, 1);
        }

        return users;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before)
    {
        loadLogs();
        Set<String> users = new HashSet<>();
        for (String[] log : logs)
        {
            if (log[3].split(" ")[0].equals(Event.DONE_TASK.toString()))
                addDataForParameters(after, before, users, log, 1);
        }

        return users;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task)
    {
        loadLogs();
        Set<String> users = new HashSet<>();
        for (String[] log : logs)
        {
            if (log[3].split(" ")[0].equals(Event.DONE_TASK.toString()) && Integer.parseInt(log[3].split(" ")[1]) == task)
                addDataForParameters(after, before, users, log, 1);
        }

        return users;
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before)
    {
        loadLogs();
        Set<Date> dates = new HashSet<>();
        for (String[] log : logs)
        {
            if (log[1].equals(user) && log[3].split(" ")[0].equals(event.toString()))
                if (isDateCorrect(after, before, getCurrentLogDate(log[2])))
                    dates.add(getCurrentLogDate(log[2]));
        }

        return dates;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before)
    {
        loadLogs();
        Set<Date> dates = new HashSet<>();
        for (String[] log : logs)
        {
            if (log[4].equals(Status.FAILED.toString()) && isDateCorrect(after, before, getCurrentLogDate(log[2])))
                dates.add(getCurrentLogDate(log[2]));
        }

        return dates;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before)
    {
        loadLogs();
        Set<Date> dates = new HashSet<>();
        for (String[] log : logs)
        {
            if (log[4].equals(Status.ERROR.toString()) && isDateCorrect(after, before, getCurrentLogDate(log[2])))
                dates.add(getCurrentLogDate(log[2]));
        }

        return dates;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before)
    {
        loadLogs();
        Date date = null;
        for (String[] log : logs)
        {
            if (log[1].equals(user) && log[3].split(" ")[0].equals(Event.LOGIN.toString()))
                date = getDate(after, before, date, log[2]);
        }

        return date;
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before)
    {
        loadLogs();
        Date date = null;
        for (String[] log : logs)
        {
            if (log[1].equals(user) && log[3].split(" ")[0].equals(Event.SOLVE_TASK.toString()) && Integer.parseInt(log[3].split(" ")[1]) == task)
                date = getDate(after, before, date, log[2]);
        }

        return date;
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before)
    {
        loadLogs();
        Date date = null;
        for (String[] log : logs)
        {
            if (log[1].equals(user) && log[3].split(" ")[0].equals(Event.DONE_TASK.toString()) && Integer.parseInt(log[3].split(" ")[1]) == task)
                date = getDate(after, before, date, log[2]);
        }

        return date;
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before)
    {
        loadLogs();
        Set<Date> dates = new HashSet<>();
        for (String[] log : logs)
        {
            if (log[1].equals(user) && log[3].split(" ")[0].equals(Event.WRITE_MESSAGE.toString()))
                if (isDateCorrect(after, before, getCurrentLogDate(log[2])))
                    dates.add(getCurrentLogDate(log[2]));
        }

        return dates;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before)
    {
        loadLogs();
        Set<Date> dates = new HashSet<>();
        for (String[] log : logs)
        {
            if (log[1].equals(user) && log[3].split(" ")[0].equals(Event.DOWNLOAD_PLUGIN.toString()))
                if (isDateCorrect(after, before, getCurrentLogDate(log[2])))
                    dates.add(getCurrentLogDate(log[2]));
        }

        return dates;
    }
}
