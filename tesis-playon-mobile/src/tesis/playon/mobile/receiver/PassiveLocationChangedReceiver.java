package tesis.playon.mobile.receiver;

import tesis.playon.mobile.Const;
import tesis.playon.mobile.service.DistanceUpdateService;
import tesis.playon.mobile.utils.LegacyLastLocationFinder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;

public class PassiveLocationChangedReceiver extends BroadcastReceiver {

    protected static String TAG = "PassiveLocationChangedReceiver";
    protected SharedPreferences prefs;
    protected SharedPreferences.Editor prefsEditor;

    @Override
    public void onReceive(Context context, Intent intent) {
	String key = LocationManager.KEY_LOCATION_CHANGED;
	Location location = null;

	if (intent.hasExtra(key)) {
	    location = (Location) intent.getExtras().get(key);
	} else {
	    LegacyLastLocationFinder lastLocationFinder = new LegacyLastLocationFinder(context);
	    location = lastLocationFinder.getLastBestLocation(Const.MAX_DISTANCE, System.currentTimeMillis()
		    - Const.MAX_TIME);
	    SharedPreferences prefs = context.getSharedPreferences(Const.APP_PREFS_NAME, Context.MODE_PRIVATE);

	    long lastTime = prefs.getLong(Const.PrefsNames.LAST_UPDATE_TIME_GEO, Long.MIN_VALUE);
	    Float lastLat = prefs.getFloat(Const.PrefsNames.LAST_UPDATE_LAT, Float.NaN);
	    Float lastLng = prefs.getFloat(Const.PrefsNames.LAST_UPDATE_LNG, Float.NaN);

	    if (lastLat.equals(Float.NaN) || lastLng.equals(Float.NaN)) {
		return;
	    }

	    Location lastLocation = new Location(Const.LOCATION_PROVIDER_SERVICE);
	    lastLocation.setLatitude(lastLat.doubleValue());
	    lastLocation.setLongitude(lastLng.doubleValue());

	    if ((lastTime > System.currentTimeMillis() - Const.MAX_TIME)
		    || (lastLocation.distanceTo(location) < Const.MAX_DISTANCE)) {
		location = null;
	    }
	}

	if (location != null) {
	    Intent updateIntent = new Intent(context, DistanceUpdateService.class);
	    updateIntent.putExtra(Const.INTENT_EXTRA_GEO_LAT, location.getLatitude());
	    updateIntent.putExtra(Const.INTENT_EXTRA_GEO_LNG, location.getLongitude());

	    context.startService(updateIntent);
	}
    }
}