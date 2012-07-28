package tesis.playon.web.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

public class LatitudlongitudUtil {

    public static String getLocationFromAddress(String address) throws Exception {

	String respuesta;

	String encodedAddress = URLEncoder.encode(address, "UTF-8");
	URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?address=" + encodedAddress
		+ "&sensor=false");
	String addresses = readURL(url);
	JSONObject json = new JSONObject(addresses);

	String status = (String) json.get(LatitudLongitudConstantes.STATUS_LABEL);

	if (LatitudLongitudConstantes.OK_MESSAGE.equalsIgnoreCase(status)) {
	    JSONArray results = json.getJSONArray(LatitudLongitudConstantes.RESULTS_LABEL);
	    if (results.length() <= 0) {
		return null;
	    }
	    JSONObject result = results.getJSONObject(0);
	    JSONObject jsonLocation = result.getJSONObject(LatitudLongitudConstantes.GEOMETRY_LABEL).getJSONObject(
		    LatitudLongitudConstantes.LOCATION_LABEL);
	    Double latitude = (Double) jsonLocation.get(LatitudLongitudConstantes.LATITUDE_LABEL);
	    Double longitude = (Double) jsonLocation.get(LatitudLongitudConstantes.LONGITUDE_LABEL);
	    respuesta = "Lat: " + latitude.toString() + " - Long: " + longitude.toString();
	} else {
	    return null;
	}
	return respuesta;
    }

    private static String readURL(URL url) throws Exception {
	URLConnection connection = url.openConnection();
	StringBuilder builder = new StringBuilder();
	BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	String line;
	while ((line = reader.readLine()) != null) {
	    builder.append(line);
	}
	return builder.toString();
    }

}