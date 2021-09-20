package com.optum.ProvisioningApi.Util;

import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


import com.azure.spring.aad.webapp.GraphClient;
import com.microsoft.spring.data.gremlin.annotation.Graph;
import org.apache.tinkerpop.gremlin.server.GremlinServer;
import org.apache.tinkerpop.gremlin.structure.Property;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.VertexProperty;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microsoft.azure.documentdb.ConnectionPolicy;
import com.microsoft.azure.documentdb.ConsistencyLevel;
import com.microsoft.azure.documentdb.DataType;
import com.microsoft.azure.documentdb.Database;
import com.microsoft.azure.documentdb.DocumentClient;
import com.microsoft.azure.documentdb.DocumentClientException;
import com.microsoft.azure.documentdb.DocumentCollection;
import com.microsoft.azure.documentdb.Index;
import com.microsoft.azure.documentdb.IndexingPolicy;
import com.microsoft.azure.documentdb.RangeIndex;
import com.microsoft.azure.documentdb.RequestOptions;
import com.microsoft.spring.data.gremlin.*;

import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.apache.tinkerpop.gremlin.driver.Result;
import org.apache.tinkerpop.gremlin.driver.ResultSet;
import org.apache.tinkerpop.gremlin.driver.exception.ResponseException;
import org.apache.tinkerpop.gremlin.driver.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class Connection {

    /*public static void main(String[] args)
    {
        Connection cons = new Connection();
        cons.connectToDB();


    }*/

    public String constructGreminQuery(String ingestDate)
    {
        String query = "g.V().haslabel('IPCA').has('IngestDate','<ingestDate>')";
        String gremlinQueries = query.replace("<ingestDate>",ingestDate);
        //String gremlinQueries = "g.V().haslabel('IPCA')";
        return gremlinQueries;
    }

    public String constructGreminQueryWithMedicareId(String MedicareId)
    {
        String query = "g.V().haslabel('IPCA').has('MedicareID','<MedicareId>')";
        String gremlinQueries = query.replace("<MedicareId>",MedicareId);
        return gremlinQueries;
    }


    public List<String> connectToDB(String Query)
    {
        List<String> data = new ArrayList<String>();

        try {

            /*String gremlinQueries[] = new String[] {
                    "g.V().haslabel('ALT')"
                    };*/

            String gremlinQueries = Query;

            Cluster cluster;
            Client client;
            cluster = Cluster.build(new File("src/remote.yaml")).create();
            client = cluster.connect();

            System.out.println("\nSubmitting this Gremlin query: " + gremlinQueries);

            // Submitting remote query to the server.
            ResultSet results = client.submit(gremlinQueries);

            CompletableFuture<List<Result>> completableFutureResults;
            CompletableFuture<Map<String, Object>> completableFutureStatusAttributes;
            List<Result> resultList = null;
            Map<String, Object> statusAttributes = null;

            try{
                completableFutureResults = results.all();
                completableFutureStatusAttributes = results.statusAttributes();
                resultList = completableFutureResults.get();
                statusAttributes = completableFutureStatusAttributes.get();
            }
                catch(ExecutionException | InterruptedException e){
                    e.printStackTrace();

            }
            catch(Exception e){
                    ResponseException re = (ResponseException) e.getCause();

                    // Response status codes. You can catch the 429 status code response and work on retry logic.
                    System.out.println("Status code: " + re.getStatusAttributes().get().get("x-ms-status-code"));
                    System.out.println("Substatus code: " + re.getStatusAttributes().get().get("x-ms-substatus-code"));

                    // If error code is 429, this value will inform how many milliseconds you need to wait before retrying.
                    System.out.println("Retry after (ms): " + re.getStatusAttributes().get().get("x-ms-retry-after"));

                    // Total Request Units (RUs) charged for the operation, upon failure.
                    System.out.println("Request charge: " + re.getStatusAttributes().get().get("x-ms-total-request-charge"));

                    // ActivityId for server-side debugging
                    System.out.println("ActivityId: " + re.getStatusAttributes().get().get("x-ms-activity-id"));
                    throw(e);
                }


                for (Result result : resultList) {



                    LinkedHashMap v = (LinkedHashMap) result.getObject();
                    LinkedHashMap v1 =(LinkedHashMap) v.get("properties");

                   ArrayList<LinkedHashMap> cName =  (ArrayList<LinkedHashMap>)v1.get("clientName");
                   String cName1 = cName.get(0).get("value").toString();
                    System.out.println( "client name:"+cName.get(0).get("value"));

                    ArrayList<LinkedHashMap> FN =  (ArrayList<LinkedHashMap>)v1.get("FN");
                    String FN1 = FN.get(0).get("value").toString();
                    System.out.println( "FN:"+FN.get(0).get("value"));

                    ArrayList<LinkedHashMap> LN =  (ArrayList<LinkedHashMap>)v1.get("LN");
                    String LN1 = LN.get(0).get("value").toString();
                    System.out.println( "LN:"+LN.get(0).get("value"));

                    ArrayList<LinkedHashMap> Dob =  (ArrayList<LinkedHashMap>)v1.get("Dob");
                    String Dob1 = Dob.get(0).get("value").toString();
                    System.out.println( "DOB:"+Dob.get(0).get("value"));

                    ArrayList<LinkedHashMap> MedicareID =  (ArrayList<LinkedHashMap>)v1.get("MedicareID");
                    String MedicareID1 = MedicareID.get(0).get("value").toString();
                    System.out.println( "MedicareID:"+MedicareID.get(0).get("value"));


                    ArrayList<LinkedHashMap> IngestDate =  (ArrayList<LinkedHashMap>)v1.get("IngestDate");
                    String IngestDate1 = IngestDate.get(0).get("value").toString();
                    System.out.println( "IngestDate:"+IngestDate.get(0).get("value"));


                    /*System.out.println("\nQuery result:");
                    System.out.println(result.toString());*/

                    //String str = cName1+"|"+FN1+"|"+LN1+"|"+Dob1+"|"+MedicareID1+"|"+IngestDate1;

                    String str = String.join("|",cName1,FN1,LN1,Dob1,MedicareID1,IngestDate1);

                    System.out.println("Query Result"+str);

                   // data.add(result.toString());
                    data.add(str);
                }

                // Status code for successful query. Usually HTTP 200.
                System.out.println("Status: " + statusAttributes.get("x-ms-status-code").toString());


                // Total Request Units (RUs) charged for the operation, after a successful run.
                System.out.println("Total charge: " + statusAttributes.get("x-ms-total-request-charge").toString());

        }catch(Exception e){
            e.getMessage();

        }
        return data;

    }

}
