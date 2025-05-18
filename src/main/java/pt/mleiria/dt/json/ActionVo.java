package pt.mleiria.dt.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ActionVo {

    @JsonProperty("action")
    private String action;

    @JsonProperty("params")
    private Params params;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }
}
