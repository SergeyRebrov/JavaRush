package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Sergey on 03.01.2017.
 */
class ExitCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "exit_en", Locale.getDefault());

    @Override
    public void execute() throws InterruptOperationException
    {
        do
        {
            ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
            String answer = ConsoleHelper.readString();
            if (answer.equals(res.getString("yes")))
            {
                ConsoleHelper.writeMessage(res.getString("thank.message"));
                break;
            } else
                break;
        }
        while (true);

    }
}
