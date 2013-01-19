package tesis.playon.mobile.utils;

import tesis.playon.mobile.Const;
import tesis.playon.mobile.utils.base.LocationUpdateRequester;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.location.Criteria;
import android.location.LocationManager;

public class LegacyLocationUpdateRequester extends LocationUpdateRequester {

    protected static String TAG = "LegacyLocationUpdateRequester";

    protected AlarmManager alarmManager;

    protected LegacyLocationUpdateRequester(LocationManager locationManager, AlarmManager alarmManager) {
	super(locationManager);
	this.alarmManager = alarmManager;
    }

    @Override
    public void requestLocationUpdates(long minTime, long minDistance, Criteria criteria, PendingIntent pendingIntent) {
	// Prior to Gingerbread we needed to find the best provider manually.
	// Note that we aren't monitoring this provider to check if it becomes
	// disabled - this is handled by the calling Activity.
	String provider = locationManager.getBestProvider(criteria, true);

	if (provider != null) {
	    try {
		locationManager.requestLocationUpdates(provider, minTime, minDistance, pendingIntent);
	    } catch (IllegalArgumentException e) {
		e.printStackTrace();
	    }
	}
    }

    @Override
    public void requestPassiveLocationUpdates(long minTime, long minDistance, PendingIntent pendingIntent) {
	alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, System.currentTimeMillis() + Const.MAX_TIME,
		Const.MAX_TIME, pendingIntent);
    }

}