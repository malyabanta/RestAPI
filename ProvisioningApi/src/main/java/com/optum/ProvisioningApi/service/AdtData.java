package com.optum.ProvisioningApi.service;

import java.util.List;

public interface AdtData {
    public List<String> getIpcaData(String ingestDate);
    public List<String> getPatientInfo(String MedicareId);
}
