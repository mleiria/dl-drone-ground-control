package pt.mleiria.dt;

public enum Action {

    NORTH("N"),
    SOUTH("S"),
    EAST("E"),
    WEST("W"),
    UP("U"),
    DOWN("D"),
    FORWARD("F"),
    BACKWARD("B"),
    MOVE("M"),
    HOME("H");

    private final String value;

    Action(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
