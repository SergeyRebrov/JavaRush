package com.javarush.test.level30.lesson15.big01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Sergey on 15.02.2017.
 */
public class Server
{
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args)
    {
        ConsoleHelper.writeMessage("Введите порт сервера.");
        try (ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt()))
        {
            ConsoleHelper.writeMessage("Сервер запущен.");
            while (true)
            {
                new Handler(serverSocket.accept()).start();
                continue;
            }
        }
        catch (IOException e)
        {
            ConsoleHelper.writeMessage("Ошибка сервера!");
        }
    }

    public static void sendBroadcastMessage(Message message)
    {
        for (Map.Entry<String, Connection> entry : connectionMap.entrySet())
        {
            try
            {
                entry.getValue().send(message);
            }
            catch (IOException e)
            {
                ConsoleHelper.writeMessage("Не удалось отправить сообщение " + entry.getKey());
            }
        }
    }

    private static class Handler extends Thread
    {
        private Socket socket;

        private Handler(Socket socket)
        {
            this.socket = socket;
        }


        public void run()
        {
            String name = null;
            try (Connection connection = new Connection(socket))
            {
                ConsoleHelper.writeMessage("Установлено новое соединение с удаленным адресом" + connection.getRemoteSocketAddress());
                name = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, name));
                sendListOfUsers(connection, name);
                serverMainLoop(connection, name);
            }
            catch (Exception e)
            {
                ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом!");
            }

            if (name != null)
            {
                connectionMap.remove(name);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, name));
                ConsoleHelper.writeMessage("Соединение с удаленным адресом закрыто.");
            }
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException
        {
            while (true)
            {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message answer = connection.receive();
                if (answer.getType() == MessageType.USER_NAME)
                {
                    if (answer.getData() != null && !answer.getData().isEmpty() && !connectionMap.containsKey(answer.getData()))
                    {
                        connectionMap.put(answer.getData(), connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        return answer.getData();
                    }
                }
            }
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException
        {
            for (Map.Entry<String, Connection> entry : connectionMap.entrySet())
            {
                if (!entry.getKey().equals(userName))
                    connection.send(new Message(MessageType.USER_ADDED, entry.getKey()));
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException
        {
            while (true)
            {
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT)
                {
                    sendBroadcastMessage(new Message(MessageType.TEXT, String.format("%s: %s", userName, message.getData())));
                } else
                {
                    ConsoleHelper.writeMessage("Неверный формат сообщения!");
                }
            }
        }
    }
}
