package tesis.playon.mobile.utils;

import tesis.playon.mobile.Const;
import tesis.playon.mobile.utils.base.ILastLocationFinder;
import tesis.playon.mobile.utils.base.LocationUpdateRequester;
import android.app.AlarmManager;
import android.content.Context;
import android.location.LocationManager;

/**
 * Factory class to create the correct instances of a variety of classes with
 * platform specific implementations.
 */
public class PlatformSpecificImplementationFactory {

    /**
     * Create a new LastLocationFinder instance
     * 
     * @param context Context
     * @return LastLocationFinder
     */
    public static ILastLocationFinder getLastLocationFinder(Context context) {
        return Const.SUPPORTS_GINGERBREAD ? new GingerbreadLastLocationFinder(context)
                : new LegacyLastLocationFinder(context);
    }

    /**
     * Create a new LocationUpdateRequester
     * 
     * @param context Context
     * @param locationManager Location Manager
     * @return LocationUpdateRequester
     */

    public static LocationUpdateRequester getLocationUpdateRequester(Context context,
            LocationManager locationManager) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // TWEAK: Froyo devices use LegacyLocationUpdateRequester instead of the
        // original FroyoLocationUpdateRequester, to avoid FC!
        return Const.SUPPORTS_GINGERBREAD ? new GingerbreadLocationUpdateRequester(locationManager)
                : new LegacyLocationUpdateRequester(locationManager, alarmManager);

        // return Const.SUPPORTS_GINGERBREAD ? new
        // GingerbreadLocationUpdateRequester(locationManager)
        // : (Const.SUPPORTS_FROYO ? new
        // FroyoLocationUpdateRequester(locationManager)
        // : new LegacyLocationUpdateRequester(locationManager, alarmManager));
    }
}
