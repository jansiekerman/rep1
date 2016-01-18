package pck;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
// import java.util.*;
import javax.servlet.http.*;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
// import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

@SuppressWarnings("serial")

public class Uithangbord_2Servlet extends HttpServlet {

  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    // response.setContentType("text/html;charset=UTF-8");
    
    // 1. Create Datastore Service
  
    DatastoreService ds = DatastoreServiceFactory.getDatastoreService();

    // 2. Create Entiteit 
  
    Entity uhb = new Entity ("Uithangbord","Uithangbord_2");

    // 3. Setup properties of Eniteit
        
    String vld1 = request.getParameter("naamBedrijf");
    String vld2 = request.getParameter("contactPersoon");
    String vld3 = request.getParameter("email");
    String vld4 = request.getParameter("aanvraag");
        
    System.out.println("veld1 : "+vld1);
    System.out.println("veld2 : "+vld2);
    System.out.println("veld3 : "+vld3);
    System.out.println("veld4 : "+vld4);
    
    uhb.setProperty("veld1", vld1);
    uhb.setProperty("veld2", vld2);
    uhb.setProperty("veld3", vld3);
    uhb.setProperty("veld4", vld4);
     
    // 4. put Entity in Datastore
  
    ds.put(uhb);
  
    System.out.println("<p>Gegevens toegevoegd Local_db.bin, key = " + KeyFactory.keyToString(uhb.getKey()) + "</p>");
  }
  
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    // resp.setContentType("text/plain");
    
    response.setContentType("application/json");
        
    Key key = KeyFactory.createKey("Uithangbord","Uithangbord_2");
    
    DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
    Entity ent;
    
    try {
      
      // Ophalen data Uithangbord_2
      
      ent = ds.get(key);
      System.out.println("gevonden!");
      
      String naamBedrijf    = (String) ent.getProperty("veld1");
      String contactPersoon = (String) ent.getProperty("veld2");
      String email          = (String) ent.getProperty("veld3");
      String aanvraag       = (String) ent.getProperty("veld4");
            
      // ff checken
      System.out.println("Naam Bedrijf = "+naamBedrijf);
      System.out.println("Naam Contactpersoon = "+contactPersoon);
      System.out.println("E-mail = "+email);
      System.out.println("Aanvraag = "+aanvraag);
          
      try {
        // maak json object
        PrintWriter writer = response.getWriter();
        JSONObject json = new JSONObject();
        json.put("naamBedrijf", naamBedrijf);
        json.put("contactPersoon",contactPersoon);
        json.put("email",email);
        json.put("aanvraag",aanvraag);
        writer.print(json);
        writer.close();
        System.out.println("aanmaken JSON gelukt: " + json);
      }
      catch (JSONException e) {
        System.out.println("aanmaken JSON niet gelukt!");
        e.printStackTrace();
      }
    }
    catch (EntityNotFoundException e) {
       System.out.println("gegevens niet gevonden");
    }
    finally {
    }
  }   
}
