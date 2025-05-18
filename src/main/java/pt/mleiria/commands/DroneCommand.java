package pt.mleiria.commands;

import java.util.Arrays;

public enum DroneCommand {

    TAKEOFF("takeoff"),
    LAND("land"),
    UP("up"),
    DOWN("down"),
    LEFT("left"),
    RIGHT("right"),
    FORWARD("forward"),
    BACK("back"),
    ERROR("error");

    private final String command;

    DroneCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
