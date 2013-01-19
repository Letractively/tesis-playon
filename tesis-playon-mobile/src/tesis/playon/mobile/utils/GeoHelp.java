package tesis.playon.mobile.utils;

import java.io.IOException;
import java.util.List;

import tesis.playon.mobile.Const;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

public class GeoHelp {

    public static Location findLocatioFromName(Context c, String name) throws IOException {

	Geocoder geocoder = new Geocoder(c);

	List<Address> adr;

	adr = geocoder.getFromLocationName(name, 10, Const.MAPS_GEOCODER_LIMITS[0], Const.MAPS_GEOCODER_LIMITS[1],
		Const.MAPS_GEOCODER_LIMITS[2], Const.MAPS_GEOCODER_LIMITS[3]);
	if (!adr.isEmpty()) {
	    Address address = adr.get(0);
	    if (((int) address.getLatitude() != 0) && ((int) address.getLongitude() != 0)) {
		Location location = new Location("mylocation");
		location.setLatitude(address.getLatitude());
		location.setLongitude(address.getLongitude());
		return location;
	    }
	}
	return null;
    }
}
