package com.nagarro.logger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.nagarro.enums.LogLevel;

//verification of only single instance is created is done in orderstatusserviceimpl class
public class Logger {
    private static Logger instance;
    private LogLevel logLevel;
    private List<String> logs;
    //private PrintWriter fileWriter;

    private Logger() {
        logLevel = LogLevel.INFO;
        logs = new ArrayList<>();
//        try {
//           fileWriter = new PrintWriter(new FileOutputStream(new File("server.log")));;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    // Singleton instance retrieval
    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void setLogLevel(LogLevel level) {
        logLevel = level;
    }

    public void log(LogLevel level, String message) {
        if (level.ordinal() >= logLevel.ordinal()) {
            String log = String.format("[%s] [%s] %s", level, LocalDateTime.now(), message);
            System.out.println(log);
//            fileWriter.println(log);
//            fileWriter.flush();
        }
        logs.add(String.format("[%s] [%s] %s", level, LocalDateTime.now(), message));
    }


    public void displayLogs() {
        for (String log : logs) {
            System.out.println(log);
        }
    }

    
}