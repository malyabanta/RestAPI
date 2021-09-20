package Util;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"MSBAVProcedureDiagcodes"})
public class ProcData {
    private List<MSBAVProcedureDiagcode> mSBAVProcedureDiagcodes;

    public ProcData() {
    }

    /**
     *
     * @param mSBAVProcedureDiagcodes
     */
    public ProcData(List<MSBAVProcedureDiagcode> mSBAVProcedureDiagcodes) {
        super();
        this.mSBAVProcedureDiagcodes = mSBAVProcedureDiagcodes;
    }

    @JsonProperty("MSBAVProcedureDiagcodes")
    public List<MSBAVProcedureDiagcode> getMSBAVProcedureDiagcodes() {
        return mSBAVProcedureDiagcodes;
    }

    @JsonProperty("MSBAVProcedureDiagcodes")
    public void setMSBAVProcedureDiagcodes(List<MSBAVProcedureDiagcode> mSBAVProcedureDiagcodes) {
        this.mSBAVProcedureDiagcodes = mSBAVProcedureDiagcodes;
    }
}
