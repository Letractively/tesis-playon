package tesis.playon.mobile.receiver;

import tesis.playon.mobile.Const;
import tesis.playon.mobile.PlayonApp;
import tesis.playon.mobile.service.DistanceUpdateService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;

/**
 * This Receiver class is used to listen for Broadcast Intents that announce that a location change has occurred. This
 * is used instead of a LocationListener within an Activity is our only action is to start a service.
 */
public class LocationChangedReceiver extends BroadcastReceiver {

    protected static String TAG = "LocationChangedReceiver";
    protected PlayonApp mAppHelper;

    /**
     * When a new location is received, extract it from the Intent and use it to start the Service used to update the
     * list of nearby places. This is the Active receiver, used to receive Location updates when the Activity is
     * visible.
     */
    @Override
    public void onReceive(Context context, Intent intent) {
	String locationKey = LocationManager.KEY_LOCATION_CHANGED;
	String providerEnabledKey = LocationManager.KEY_PROVIDER_ENABLED;
	if (intent.hasExtra(providerEnabledKey)) {
	    if (!intent.getBooleanExtra(providerEnabledKey, true)) {
		Intent providerDisabledIntent = new Intent(Const.ACTIVE_LOCATION_UPDATE_PROVIDER_DISABLED);
		context.sendBroadcast(providerDisabledIntent);
	    }
	}
	if (intent.hasExtra(locationKey)) {
	    Location location = (Location) intent.getExtras().get(locationKey);

	    Intent updateIntent = new Intent(context, DistanceUpdateService.class);
	    updateIntent.putExtra(Const.INTENT_EXTRA_GEO_LAT, location.getLatitude());
	    updateIntent.putExtra(Const.INTENT_EXTRA_GEO_LNG, location.getLongitude());

	    context.startService(updateIntent);
	}
    }
}
