package com.optum.ProvisioningApi.controller;

import com.optum.ProvisioningApi.Util.CustomErrorType;
import com.optum.ProvisioningApi.service.AdtData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestApiController {

    @Autowired
    AdtData adtData;

   /* @RequestMapping(value = "/IPCA/IngestDate/{IngestDate}", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getAdtData(@PathVariable("IngestDate") String ingestDate)
    {
       List<String> data =  adtData.getIpcaData(ingestDate);
        if (data.isEmpty()) {

            return new ResponseEntity(new CustomErrorType("IPCA Data for ingest date =  " + ingestDate
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<String>>(data, HttpStatus.OK);

    }*/

    @RequestMapping(value = "/IPCA/IngestDate/{IngestDate}", method = RequestMethod.GET)
    public ResponseEntity<String> getAdtData(@PathVariable("IngestDate") String ingestDate)
    {
        List<String> data =  adtData.getIpcaData(ingestDate);
        if (data.isEmpty()) {

            return new ResponseEntity(new CustomErrorType("IPCA Data for ingest date =  " + ingestDate
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(data.toString(), HttpStatus.OK);

    }

    @RequestMapping(value = "/IPCA/medicareId/{MedicareID}", method = RequestMethod.GET)
    public ResponseEntity<String> getPatientInfo(@PathVariable("MedicareID") String MedicareID)
    {
        List<String> data =  adtData.getPatientInfo(MedicareID);

        if (data.isEmpty()) {

            return new ResponseEntity(new CustomErrorType("MedicareID =  " + MedicareID
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(data.toString(), HttpStatus.OK);

    }

   /* @RequestMapping(value = "/IPCA/medicareId/{MedicareID}", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getPatientInfo(@PathVariable("MedicareID") String MedicareID)
    {
        List<String> data =  adtData.getPatientInfo(MedicareID);

        if (data.isEmpty()) {

            return new ResponseEntity(new CustomErrorType("MedicareID =  " + MedicareID
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<String>>(data, HttpStatus.OK);

    }*/


}
