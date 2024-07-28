class Logger {
    private static Logger instance;

    private Logger() {}

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("Logged: " + message);
    }
}

public class Singleton_Pattern{
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.log("Hello");
    }
}