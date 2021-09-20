package com.optum.SmartProcidurePredictor.service;

import java.util.List;

public interface procedures {

    List<String> findBydiagnosisCode(String code);
    void setProcedureCode(String code, String propCode);
}
