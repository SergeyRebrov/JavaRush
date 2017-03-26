package com.javarush.test.level39.lesson09.big01;

import com.javarush.test.level39.lesson09.big01.query.EventQuery;

import java.nio.file.Path;
import java.util.*;

/**
 * Created by Sergey on 25.03.2017.
 */
public abstract class LogParserImplEventQuery extends LogParserImplDateQuery implements EventQuery
{
    protected LogParserImplEventQuery(Path logDir)
    {
        super(logDir);
    }

    private void addEventForParameters(Date after, Date before, Set<Event> set, String[] log)
    {
        Date currentLogDate = getCurrentLogDate(log[2]);

        if (isDateCorrect(after, before, currentLogDate))
        {
            set.add(Event.valueOf(log[3].split(" ")[0]));
        }
    }

    protected Set<Event> getOkEvents(Date after, Date before)
    {
        Set<Event> events = new HashSet<>();
        for (String[] log : logs)
        {
            if (log[4].equals(Status.OK.toString()))
                addEventForParameters(after, before, events, log);
        }

        return events;
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before)
    {
        return getAllEvents(after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before)
    {
        Set<Event> events = new HashSet<>();
        for (String[] log : logs)
        {
            addEventForParameters(after, before, events, log);
        }

        return events;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before)
    {
        Set<Event> events = new HashSet<>();
        for (String[] log : logs)
        {
            if (ip.equals(log[0]))
                addEventForParameters(after, before, events, log);
        }

        return events;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before)
    {
        Set<Event> events = new HashSet<>();
        for (String[] log : logs)
        {
            if (user.equals(log[1]))
                addEventForParameters(after, before, events, log);
        }

        return events;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before)
    {
        Set<Event> events = new HashSet<>();
        for (String[] log : logs)
        {
            if (log[4].equals(Status.FAILED.toString()))
                addEventForParameters(after, before, events, log);
        }

        return events;
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before)
    {
        Set<Event> events = new HashSet<>();
        for (String[] log : logs)
        {
            if (log[4].equals(Status.ERROR.toString()))
                addEventForParameters(after, before, events, log);
        }

        return events;
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before)
    {
        int count = 0;
        for (String[] log : logs)
        {
            if (log[3].split(" ")[0].equals(Event.SOLVE_TASK.toString()) && Integer.parseInt(log[3].split(" ")[1]) == task)
                if (isDateCorrect(after, before, getCurrentLogDate(log[2])))
                    count++;
        }

        return count;
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before)
    {
        int count = 0;
        for (String[] log : logs)
        {
            if (log[3].split(" ")[0].equals(Event.SOLVE_TASK.toString()) && Integer.parseInt(log[3].split(" ")[1]) == task && log[4].equals(Status.OK.toString()))
                if (isDateCorrect(after, before, getCurrentLogDate(log[2])))
                    count++;
        }

        return count;
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before)
    {
        Map<Integer, Integer> solvedTasksAndTheirNumber = new HashMap<>();

        for (String[] log : logs)
        {
            if (log[3].split(" ")[0].equals(Event.SOLVE_TASK.toString()) && isDateCorrect(after, before, getCurrentLogDate(log[2])))
            {
                int count = getNumberOfAttemptToSolveTask(Integer.parseInt(log[3].split(" ")[1]), after, before);
                solvedTasksAndTheirNumber.put(Integer.parseInt(log[3].split(" ")[1]), count);
            }
        }

        return solvedTasksAndTheirNumber;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before)
    {
        Map<Integer, Integer> doneTasksAndTheirNumber = new HashMap<>();

        for (String[] log : logs)
        {
            if (log[3].split(" ")[0].equals(Event.DONE_TASK.toString()) && isDateCorrect(after, before, getCurrentLogDate(log[2])))
            {
                int count = getNumberOfAttemptToDoneTask(Integer.parseInt(log[3].split(" ")[1]), after, before);
                doneTasksAndTheirNumber.put(Integer.parseInt(log[3].split(" ")[1]), count);
            }
        }

        return doneTasksAndTheirNumber;
    }

    private int getNumberOfAttemptToDoneTask(int task, Date after, Date before)
    {
        int count = 0;
        for (String[] log : logs)
        {
            if (log[3].split(" ")[0].equals(Event.DONE_TASK.toString()) && Integer.parseInt(log[3].split(" ")[1]) == task)
                if (isDateCorrect(after, before, getCurrentLogDate(log[2])))
                    count++;
        }

        return count;
    }
}
