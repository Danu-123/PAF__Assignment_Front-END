package com;

import model.ElecAccount;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document; 


@Path("/Account") 
public class ElecAccountService {
	
	ElecAccount elec = new ElecAccount();
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	
	
	public String addAccount(@FormParam("Account_number") String Account_number, 
	 @FormParam("Account_name") String Account_name, 
	 @FormParam("Premises_id") String Premises_id,
	 @FormParam("Cus_id") String Cus_id)
	{ 
	 String output = elec.addAccount(Account_number, Account_name, Premises_id, Cus_id); 
	return output; 
	}
	
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readElecAccounts()
	 {
	 return elec.readElecAccounts();
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateAccount(String accountData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject accObject = new JsonParser().parse(accountData).getAsJsonObject(); 
	 
	//Read the values from the JSON object
	 String AccID=accObject.get("AccID").getAsString();
	 String Account_number = accObject.get("Account_number").getAsString(); 
	 String Account_name = accObject.get("Account_name").getAsString(); 
	 String Premises_id = accObject.get("Premises_id").getAsString(); 
	 String Cus_id = accObject.get("Cus_id").getAsString();
	 
	 String output = elec.updateAccount(AccID,Account_number,Account_name,Premises_id, Cus_id); 
	return output; 
	}
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteItem(String accountData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(accountData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String AccID = doc.select("AccID").text(); 
	 String output = elec.deleteAccount(AccID); 
	return output; 
	}
	
	
}
