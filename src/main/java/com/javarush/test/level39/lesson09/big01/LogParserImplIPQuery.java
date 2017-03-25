package com.javarush.test.level39.lesson09.big01;

import com.javarush.test.level39.lesson09.big01.query.IPQuery;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Sergey on 25.03.2017.
 */
public abstract class LogParserImplIPQuery implements IPQuery
{
    protected Path logDir;
    protected List<String[]> logs = new ArrayList<>();
    protected DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    protected LogParserImplIPQuery(Path logDir)
    {
        this.logDir = logDir;
        loadLogs();
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

    protected void addStringForParameters(Date after, Date before, Set<String> set, String[] log, int parameter)
    {
        Date currentLogDate = getCurrentLogDate(log[2]);

        if (isDateCorrect(after, before, currentLogDate))
        {
            set.add(log[parameter]);
        }
    }

    protected Date getCurrentLogDate(String logDate)
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

    protected boolean isDateCorrect(Date after, Date before, Date currentLogDate)
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

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before)
    {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before)
    {
        Set<String> IPs = new HashSet<>();
        for (String[] log : logs)
        {
            addStringForParameters(after, before, IPs, log, 0);
        }

        return IPs;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before)
    {
        Set<String> IPs = new HashSet<>();
        for (String[] log : logs)
        {
            if (user == null || user.equals(log[1]))
                addStringForParameters(after, before, IPs, log, 0);
        }

        return IPs;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before)
    {
        Set<String> IPs = new HashSet<>();
        for (String[] log : logs)
        {
            String currentEvent = log[3].split(" ")[0];
            if (event == null || event.toString().equals(currentEvent))
                addStringForParameters(after, before, IPs, log, 0);
        }

        return IPs;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before)
    {
        Set<String> IPs = new HashSet<>();
        for (String[] log : logs)
        {
            if (status == null || status.toString().equals(log[4]))
                addStringForParameters(after, before, IPs, log, 0);
        }

        return IPs;
    }
}
