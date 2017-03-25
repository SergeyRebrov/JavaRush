package com.javarush.test.level39.lesson09.big01;

import com.javarush.test.level39.lesson09.big01.query.DateQuery;

import java.nio.file.Path;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sergey on 25.03.2017.
 */
public abstract class LogParserImplDateQuery extends LogParserImplUserQuery implements DateQuery
{

    protected LogParserImplDateQuery(Path logDir)
    {
        super(logDir);
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
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before)
    {
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
