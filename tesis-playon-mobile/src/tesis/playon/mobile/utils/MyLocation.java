package tesis.playon.mobile.utils;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class MyLocation {

    private static final String TAG = MyLocation.class.getSimpleName();

    private Timer mTimer;

    private LocationManager lm;

    private LocationResult locationResult;

    private boolean gps_enabled = false;

    private boolean network_enabled = false;

    public boolean getLocation(Context context, LocationResult result) {

	locationResult = result;

	if (lm == null) {
	    lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
	}

	try {
	    gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
	} catch (Exception e) {
	    Log.e(TAG, e.getStackTrace().toString());
	}
	try {
	    network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
	} catch (Exception e) {
	    Log.e(TAG, e.getStackTrace().toString());
	}

	if (!gps_enabled && !network_enabled) {
	    return false;
	}

	if (gps_enabled) {
	    lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListenerGps);
	}

	if (network_enabled) {
	    lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListenerNetwork);
	}

	mTimer = new Timer();

	mTimer.schedule(new GetLastLocation(), 20000);

	return true;
    }

    LocationListener locationListenerGps = new LocationListener() {

	public void onLocationChanged(Location location) {
	    mTimer.cancel();
	    locationResult.gotLocation(location);
	    lm.removeUpdates(this);
	    lm.removeUpdates(locationListenerNetwork);
	}

	public void onProviderDisabled(String provider) {
	}

	public void onProviderEnabled(String provider) {
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

    };

    LocationListener locationListenerNetwork = new LocationListener() {

	public void onLocationChanged(Location location) {
	    mTimer.cancel();
	    locationResult.gotLocation(location);
	    lm.removeUpdates(this);
	    lm.removeUpdates(locationListenerGps);
	}

	public void onProviderDisabled(String provider) {
	}

	public void onProviderEnabled(String provider) {
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

    };

    class GetLastLocation extends TimerTask {
	@Override
	public void run() {

	    lm.removeUpdates(locationListenerGps);
	    lm.removeUpdates(locationListenerNetwork);

	    Location net_loc = null, gps_loc = null;

	    if (gps_enabled) {
		gps_loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	    }

	    if (network_enabled) {
		net_loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
	    }

	    if (gps_loc != null && net_loc != null) {

		if (gps_loc.getTime() > net_loc.getTime()) {
		    locationResult.gotLocation(gps_loc);
		} else {
		    locationResult.gotLocation(net_loc);
		}
		return;
	    }

	    if (gps_loc != null) {
		locationResult.gotLocation(gps_loc);
		return;
	    }
	    if (net_loc != null) {
		locationResult.gotLocation(net_loc);
		return;
	    }
	    locationResult.gotLocation(null);
	}
    }

    public static abstract class LocationResult {
	public abstract void gotLocation(Location location);
    }
}