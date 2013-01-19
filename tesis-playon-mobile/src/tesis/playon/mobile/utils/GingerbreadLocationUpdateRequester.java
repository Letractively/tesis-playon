package tesis.playon.mobile.utils;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Build;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class GingerbreadLocationUpdateRequester extends FroyoLocationUpdateRequester {
    
    protected static final String TAG = "GingerbreadLocationUpdateRequester";

    public GingerbreadLocationUpdateRequester(LocationManager locationManager) {
	super(locationManager);
    }

    @Override
    public void requestLocationUpdates(long minTime, long minDistance, Criteria criteria, PendingIntent pendingIntent) {

	try {
	    locationManager.requestLocationUpdates(minTime, minDistance, criteria, pendingIntent);
	} catch (IllegalArgumentException e) {
	    e.printStackTrace();
	}
    }
}
