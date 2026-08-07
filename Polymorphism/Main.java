package Polymorphism;

interface Logger {

    void log(String level, String message);

    String getDestination();
}

class ConsoleLogger implements Logger {

    public void log(String level, String message) {
        System.out.println("[" + level + "]" + message);
    }

    public String getDestination() {
        return new String("Console");
    }

}

class FilePath implements Logger {
    public void log(String level, String message) {
        System.out.println("Writing to filePath: [" + level + "] " + message);
    }

    public String getDestination() {
        return new String("File: filePath");
    }

}

import java.util.List;

interface Logger {
    void log(String level, String message);

    String getDestination();
}

class ConsoleLogger implements Logger {
    public void log(String level, String message) {
        // TODO: print "[level] message" to console
        System.out.println("[" + level + "]" + message);
    }

    public String getDestination() {
        // TODO: return "Console"
        return new String("Console");
    }
}

class FileLogger implements Logger {
    private String filePath;

    public FileLogger(String filePath) {
        // TODO: initialize this.filePath
        fiel

    }

    public void log(String level, String message) {
        // TODO: print "Writing to filePath: [level] message"
        System.out.println("Writing to filePath: [" + level + "] " + message);

    }

    public String getDestination() {
        // TODO: return "File: filePath"
        return new String("File: filePath");
    }
}

class DatabaseLogger implements Logger {
    private String tableName;

    public DatabaseLogger(String tableName) {
        // TODO: initialize this.tableName
    }

    public void log(String level, String message) {
        // TODO: print "INSERT INTO tableName: [level] message"
    }

    public String getDestination() {
        // TODO: return "Database: tableName"
        return "";
    }
}

class Application {
    private Logger logger;

    public Application(Logger logger) {
        // TODO: initialize this.logger
    }

    public void run() {
        // TODO: log three messages with level "INFO":
        // "Application starting..."
        // "Processing data..."
        // "Application shutting down."
    }
}

public class Main {
    public static void main(String[] args) {
        List<Logger> loggers = List.of(
                new ConsoleLogger(),
                new FileLogger("/var/log/app.log"),
                new DatabaseLogger("app_logs"));

        for (Logger logger : loggers) {
            System.out.println("--- Using " + logger.getDestination() + " ---");
            Application app = new Application(logger);
            app.run();
            System.out.println();
        }
    }
}