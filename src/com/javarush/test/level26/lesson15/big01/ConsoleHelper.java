package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Sergey on 02.01.2017.
 */
public class ConsoleHelper
{
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en", Locale.getDefault());

    public static void writeMessage(String message)
    {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException
    {

        String line = "";
        try
        {
            line = reader.readLine();
            if (line.toUpperCase().equals(res.getString("operation.EXIT")))
            {
                writeMessage(res.getString("the.end"));
                throw new InterruptOperationException();
            }
        }
        catch (IOException ignored)
        {
        }

        return line;
    }

    public static String askCurrencyCode() throws InterruptOperationException
    {
        writeMessage(res.getString("choose.currency.code"));
        String s = readString().trim();
        while (!s.matches("^[a-zA-Z]{3}$"))
        {
            writeMessage(res.getString("invalid.data"));
            s = readString();
        }
        return s.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException, NumberFormatException
    {
        writeMessage(res.getString("choose.denomination.and.count.format"));
        String[] denomination;
        while (true)
        {
            denomination = readString().trim().replaceAll("\\s+", " ").split(" ");
            if (denomination.length != 2)
                writeMessage(res.getString("invalid.data"));
            else
            {
                break;
            }
        }
        return denomination;
    }

    public static Operation askOperation() throws InterruptOperationException
    {

        do
        {
            writeMessage(res.getString("choose.operation"));
            writeMessage("1 - " + res.getString("operation.INFO"));
            writeMessage("2 - " + res.getString("operation.DEPOSIT"));
            writeMessage("3 - " + res.getString("operation.WITHDRAW"));
            writeMessage("4 - " + res.getString("operation.EXIT"));
            try
            {
                return Operation.getAllowableOperationByOrdinal(Integer.parseInt(readString()));
            }
            catch (IllegalArgumentException ignored)
            {
            }
        }
        while (true);
    }

    public static void printExitMessage()
    {
        writeMessage("Goodbye!");
    }
}
