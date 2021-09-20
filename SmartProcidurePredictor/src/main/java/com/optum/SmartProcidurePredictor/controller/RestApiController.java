package com.optum.SmartProcidurePredictor.controller;



import Util.CustomErrorType;
import com.optum.SmartProcidurePredictor.service.procedures;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import com.optum.SmartProcidurePredictor.service.ProceduresImp;


@RestController
//@RequestMapping("/diagnosis")
public class RestApiController {
    public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    procedures procedure;


    @RequestMapping(value = "/diagnosis/{code}", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getAllProcedures(@PathVariable("code") String code)
    {
        logger.info("Fetching Diagnosis with code {}", code);
        List<String> procedures = procedure.findBydiagnosisCode(code);
                if (procedures.isEmpty()) {
            logger.error("Diagnosis with code {} not found.", code);
            return new ResponseEntity(new CustomErrorType("Diagnosis with code " + code
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<String>>(procedures, HttpStatus.OK);

    }

    @RequestMapping(value = "/diagnosis/{code}/{procCode}", method = RequestMethod.POST)
    public void setProcedures(@PathVariable("code") String code, @PathVariable("procCode") String procCode)
    {
        logger.info("Diagnosis with code {} and Procedure code {}", code, procCode);
        procedure.setProcedureCode(code, procCode);


    }


}
