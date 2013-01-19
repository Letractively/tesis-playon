package tesis.playon.mobile.utils.base;

import android.app.PendingIntent;
import android.location.Criteria;
import android.location.LocationManager;

public abstract class LocationUpdateRequester {

    protected LocationManager locationManager;

    protected LocationUpdateRequester(LocationManager locationManager) {
	this.locationManager = locationManager;
    }

    public void requestLocationUpdates(long minTime, long minDistance, Criteria criteria, PendingIntent pendingIntent) {
    }

    public void requestPassiveLocationUpdates(long minTime, long minDistance, PendingIntent pendingIntent) {
    }

}
