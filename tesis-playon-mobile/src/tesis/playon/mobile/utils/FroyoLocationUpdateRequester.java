package tesis.playon.mobile.utils;

import tesis.playon.mobile.Const;
import tesis.playon.mobile.utils.base.LocationUpdateRequester;
import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Build;

@TargetApi(Build.VERSION_CODES.FROYO)
public class FroyoLocationUpdateRequester extends LocationUpdateRequester {
    protected static final String TAG = "FroyoLocationUpdateRequester";

    public FroyoLocationUpdateRequester(LocationManager locationManager) {
	super(locationManager);
    }

    @Override
    public void requestLocationUpdates(long minTime, long minDistance, Criteria criteria, PendingIntent pendingIntent) {
	String bestAvailableProvider = locationManager.getBestProvider(criteria, true);

	if (bestAvailableProvider != null) {
	    try {
		locationManager.requestLocationUpdates(bestAvailableProvider, minTime, minDistance, pendingIntent);
	    } catch (IllegalArgumentException e) {
		e.printStackTrace();
	    }
	}
    }

    @Override
    public void requestPassiveLocationUpdates(long minTime, long minDistance, PendingIntent pendingIntent) {

	locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, Const.MAX_TIME, Const.MAX_DISTANCE,
		pendingIntent);
    }

}