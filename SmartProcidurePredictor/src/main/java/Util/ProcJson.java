package Util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"data"})
public class ProcJson {

    @JsonProperty("data")
    private ProcData data;

    public ProcJson() {
    }

    /**
     * @param data
     */
    public ProcJson(ProcData data) {
        super();
        this.data = data;
    }

    @JsonProperty("data")
    public ProcData getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(ProcData data) {
        this.data = data;
    }
}
