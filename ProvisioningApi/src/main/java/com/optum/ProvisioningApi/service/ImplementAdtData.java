package com.optum.ProvisioningApi.service;

import com.optum.ProvisioningApi.Util.Connection;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AdtData")
public class ImplementAdtData implements  AdtData {
    @Override
    public List<String> getIpcaData(String IngestDate) {
        Connection cons = new Connection();
        String query = cons.constructGreminQuery(IngestDate);
       List<String> data =  cons.connectToDB(query);
        return data;
    }

    @Override
    public List<String> getPatientInfo(String MedicareId) {
        Connection cons = new Connection();
        String query = cons.constructGreminQueryWithMedicareId(MedicareId);
        List<String> data =  cons.connectToDB(query);
        return data;
    }
}
