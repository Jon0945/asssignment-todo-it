package se.lexicon.exception;
public class CustomSQLException extends RuntimeException{
        public CustomSQLException(String message) {
            super(message);
        }
        public CustomSQLException(String message, Throwable cause) {
            super(message, cause);
        }
    }

