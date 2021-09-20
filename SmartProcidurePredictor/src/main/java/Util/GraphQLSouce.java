package Util;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class GraphQLSouce {

    public String getprocedureCode(String code ) {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;

        client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://ect-hackathon.optum.com/procedurepredictors/v1/graphql");

        httpPost.addHeader("x-hasura-admin-secret", "procedurepredictorsadminsecretkey");
        httpPost.addHeader("Accept", "application/json");

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("query", "query { MSBAVProcedureDiagcodes(where: {DiagnosisCode: {_eq:\""+code+"\"}},order_by: {Frequency:desc,ProcedureCode:asc}) { ProcedureCode } }");

        try {
            StringEntity entity = new StringEntity(jsonObj.toString());
            httpPost.setEntity(entity);
            response = client.execute(httpPost);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = null;
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {

                builder.append(line);

            }
            System.out.println(builder.toString());
            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }


    public void setProcedureFrequncy(String code, String procCode)
    {
        System.out.println("code"+code);
        System.out.println("procCode" +procCode);

        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;

        client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://ect-hackathon.optum.com/procedurepredictors/v1/graphql");

        httpPost.addHeader("x-hasura-admin-secret", "procedurepredictorsadminsecretkey");
        httpPost.addHeader("Accept", "application/json");

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("query", " mutation MSBAVProcedureDiagcodes {​​​​​ update_MSBAVProcedureDiagcodes_by_pk ( pk_columns: {​​​​​DiagnosisCode: \""+code+"\",ProcedureCode: \""+procCode+"\"}​​​​​  _inc: {​​​​​Frequency: 1}​​​​​ ) {​​​​​ DiagnosisCode ProcedureCode }​​​​​}​​​​​");
        try {
            StringEntity entity = new StringEntity(jsonObj.toString());
            httpPost.setEntity(entity);
            response = client.execute(httpPost);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(response.toString());

        /*try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = null;
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {

                builder.append(line);

            }
            System.out.println(builder.toString());

        } catch (Exception e) {
            e.printStackTrace();

        }*/


    }



}
