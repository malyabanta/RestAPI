package com.optum.SmartProcidurePredictor.service;


import Util.GraphQLSouce;
import Util.parseJsons;
import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("procedures")
public class ProceduresImp implements procedures{


public List findBydiagnosisCode(String code)
{

    String jsonproObject = new GraphQLSouce().getprocedureCode(code);

    List listOfProcedureCode = new ArrayList();
    if(jsonproObject != null)
    {
        listOfProcedureCode = new parseJsons().parse(jsonproObject);
    }else{
        System.out.println("GrapthQLSource returns a null JSON object ");
    }


   return listOfProcedureCode;

}

public void  setProcedureCode(String code, String procCode)
{
    new GraphQLSouce().setProcedureFrequncy(code,procCode );
}



}
