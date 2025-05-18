package pt.mleiria.dt;

/**
 * Mapear do lado do python mavlink para o spring boot
 */
public enum RequestParams {

    MISSION_NAME("mission_name"),
    CMD_RESP("cmd_resp");

    private final String value;

    RequestParams(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
