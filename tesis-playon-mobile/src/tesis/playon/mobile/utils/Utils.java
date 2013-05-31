package tesis.playon.mobile.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import tesis.playon.mobile.json.model.GoogleGeoCodeResponse;
import tesis.playon.mobile.json.model.Playa;
import tesis.playon.mobile.json.model.Playas;
import android.util.Log;

public class Utils {

    private double lat;

    private double lng;

    public static Playa playaselected;

    public InputStream retrieveStream(String url) {

	DefaultHttpClient client = new DefaultHttpClient();
	HttpGet getRequest = new HttpGet(url);
	try {
	    HttpResponse getResponse = client.execute(getRequest);
	    final int statusCode = getResponse.getStatusLine().getStatusCode();
	    if (statusCode != HttpStatus.SC_OK) {
		Log.w(getClass().getSimpleName(), "Error " + statusCode + " for URL " + url);
		return null;
	    }
	    HttpEntity getResponseEntity = getResponse.getEntity();
	    return getResponseEntity.getContent();
	} catch (IOException e) {
	    getRequest.abort();
	    Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
	}
	return null;
    }

    public Playas buscarPlaya(Playas playas, GoogleGeoCodeResponse result, Integer distancia) {

	List<Playa> playaResultadoBusqueda = new ArrayList<Playa>();
	if (null != playas && null != result) {
	    try {
		for (Playa playaAux : playas.getPlayas()) {

		    lat = Double.parseDouble(result.results[0].geometry.location.lat);
		    lng = Double.parseDouble(result.results[0].geometry.location.lng);

		    Double comparacion = playaAux.getDistanceFrom(lat, lng);
		    if (comparacion < distancia) {
			playaResultadoBusqueda.add(playaAux);
		    }
		}
		Collections.sort(playaResultadoBusqueda, new Comparar());
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	playas.setPlayas(playaResultadoBusqueda);

	return playas;
    }

    class Comparar implements Comparator<Playa> {
	public int compare(Playa p1, Playa p2) {
	    Double comparacion1 = p1.getDistanceFrom(lat, lng);
	    Double comparacion2 = p2.getDistanceFrom(lat, lng);
	    return comparacion1.compareTo(comparacion2);
	}
    }

}