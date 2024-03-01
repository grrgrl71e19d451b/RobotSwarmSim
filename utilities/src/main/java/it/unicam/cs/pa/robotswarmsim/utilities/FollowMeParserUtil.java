package it.unicam.cs.pa.robotswarmsim.utilities;

public class FollowMeParserUtil {
    public static String unknownCommandMessage(int line) {
        return String.format("Unknown command at line %d", line);
    }
}
