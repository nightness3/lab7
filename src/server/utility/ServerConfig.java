package server.utility;

public class ServerConfig {

    private static boolean isRunning = true;

    public static void setIsRunning (boolean newIsRunning) {
        isRunning = newIsRunning;
    }

    public static boolean isIsRunning() {
        return isRunning;
    }
}
