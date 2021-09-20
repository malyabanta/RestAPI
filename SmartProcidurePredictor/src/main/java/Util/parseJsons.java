package Util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class parseJsons {
    public List<String> parse(String jsonInString){

        ObjectMapper mapper = new ObjectMapper();
        List<String> codes = new ArrayList<>();
        try {
            // JSON string to Java object
            ProcJson staff2 = mapper.readValue(jsonInString, ProcJson.class);

            // pretty print
            String prettyStaff1 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(staff2);
            System.out.println(prettyStaff1);

            List<MSBAVProcedureDiagcode> mc = staff2.getData().getMSBAVProcedureDiagcodes();
            for(MSBAVProcedureDiagcode item : mc){
                codes.add(item.getProcedureCode());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return codes;
    }
}
