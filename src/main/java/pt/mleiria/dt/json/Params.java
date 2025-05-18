package pt.mleiria.dt.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Params {

    @JsonProperty("altitude")
    private int altitude;

    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }
}
