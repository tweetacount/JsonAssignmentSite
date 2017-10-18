package com.json.assignment.webservice;

import java.util.List;

import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.json.assignment.helper.JsonHelper;

@Path("/jsonWebservice")
public class JsonWebservice {

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String query(@FormParam("country") String country,
			@FormParam("travelStartDate") String travelStartDate, @FormParam("travelEndDate") String travelEndDate,
			@FormParam("regionID") String regionID, @FormParam("city") String city) {
		JsonHelper jsonHelper = new JsonHelper();
		List<JsonObject> results = jsonHelper.query(country, travelStartDate, travelEndDate, regionID, city);
		String resultsString = results.toString();
	//	resultsString=resultsString.substring(1, resultsString.length()-1);
		return resultsString;
	}

}
