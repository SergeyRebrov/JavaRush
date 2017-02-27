package com.javarush.test.level23.lesson06.task02;

/* Рефакторинг
Отрефакторите класс Solution: вынесите все константы в public вложенный(nested) класс Constants.
Запретите наследоваться от Constants.
*/
public class Solution
{

    public static final class Constants
    {
        public static final String SERVER_NOT_ACCESSIBLE_EXCEPTION = "Server is not accessible for now.";
        public static final String UN_U_E = "User is not authorized.";
        public static final String BA_U_E = "User is banned.";
        public static final String RE_E = "Access is denied.";

    }

    public class ServerNotAccessibleException extends Exception
    {
        public ServerNotAccessibleException()
        {
            super(Constants.SERVER_NOT_ACCESSIBLE_EXCEPTION);
        }

        public ServerNotAccessibleException(Throwable cause)
        {
            super(Constants.SERVER_NOT_ACCESSIBLE_EXCEPTION, cause);
        }
    }

    public class UnauthorizedUserException extends Exception
    {
        public UnauthorizedUserException()
        {
            super(Constants.UN_U_E);
        }

        public UnauthorizedUserException(Throwable cause)
        {
            super(Constants.UN_U_E, cause);
        }
    }

    public class BannedUserException extends Exception
    {
        public BannedUserException()
        {
            super(Constants.BA_U_E);
        }

        public BannedUserException(Throwable cause)
        {
            super(Constants.BA_U_E, cause);
        }
    }

    public class RestrictionException extends Exception
    {
        public RestrictionException()
        {
            super(Constants.RE_E);
        }

        public RestrictionException(Throwable cause)
        {
            super(Constants.RE_E, cause);
        }
    }
}
