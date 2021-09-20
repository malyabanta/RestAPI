package Util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"ProcedureCode"})
public class MSBAVProcedureDiagcode {

    @JsonProperty("ProcedureCode")
    private String procedureCode;


    public MSBAVProcedureDiagcode() {
    }

    /**
     * @param procedureCode
     */
    public MSBAVProcedureDiagcode(String procedureCode) {
        super();
        this.procedureCode = procedureCode;
    }

    @JsonProperty("ProcedureCode")
    public String getProcedureCode() {
        return procedureCode;
    }

    @JsonProperty("ProcedureCode")
    public void setProcedureCode(String procedureCode) {
        this.procedureCode = procedureCode;
    }

}