package com.json.assignment.helper;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class JsonHelper {

	public List<JsonObject> query(String country, String travelStartDate, String travelEndDate, String regionID,
			String city) {
		URL url;
		try {
			url = new URL(
					"https://offersvc.expedia.com/offers/v2/getOffers?scenario=deal-finder&page=foo&uid=foo&productType=Hotel");
			InputStream is = url.openStream();
			JsonReader rdr = Json.createReader(is);
			JsonObject obj = rdr.readObject();
			return filter(obj, country, travelStartDate, travelEndDate, regionID, city);

		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<JsonObject> filter(JsonObject obj, String country, String travelStartDate, String travelEndDate,
			String regionID, String city) {
		List<JsonObject> stream = obj.getJsonObject("offers").getJsonArray("Hotel").getValuesAs(JsonObject.class)
				.stream().filter(e -> {
					boolean found = false;

					String startDate = e.getJsonObject("offerDateRange").getJsonArray("travelStartDate").toString();
					startDate = startDate.substring(1, startDate.length() - 1);
					String endDate = e.getJsonObject("offerDateRange").getJsonArray("travelEndDate").toString();
					endDate = endDate.substring(1, endDate.length() - 1);
					String regionId = e.getJsonObject("destination").getString("regionID").toString();
					String cityName = e.getJsonObject("destination").getString("city").toString();
					String countryName = e.getJsonObject("destination").getString("country").toString();
					if ((startDate.equals(travelStartDate) && endDate.equals(travelEndDate))
							&& (countryName.toLowerCase().contains(country.toLowerCase()) || regionId.equals(regionID)
									|| cityName.toLowerCase().equals(city.toLowerCase())))
						found = true;

					return found;
				}).collect(Collectors.toList());

		return stream;

	}

}
