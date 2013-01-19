package tesis.playon.mobile;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import tesis.playon.mobile.Const.PrefsNames;
import tesis.playon.mobile.Const.PrefsValues;
import tesis.playon.mobile.service.DistanceUpdateService;
import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.location.Location;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class PlayonApp extends Application {
    protected static final String TAG = "PlayonApp";

    private SharedPreferences prefs;

    private String mUnits;
    private String mLanguage;
    private Toast mToast;
    private boolean mHasLoadedData;

    private GregorianCalendar mParkingCalendar;
    private int mParkingDuration;

    private Location mLocation;

    // TODO: verify possible memory leakage of the following code
    private static PlayonApp instance = null;

    public static PlayonApp getInstance() {
	checkInstance();
	return instance;
    }

    private static void checkInstance() {
	if (instance == null)
	    throw new IllegalStateException("Application not created yet!");
    }

    @SuppressLint("ShowToast")
    @Override
    public void onCreate() {
	super.onCreate();
	instance = this;

	PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
	prefs = getSharedPreferences(Const.APP_PREFS_NAME, Context.MODE_PRIVATE);

	/**
	 * Initialize UI settings based on preferences.
	 */
	mUnits = prefs.getString(PrefsNames.UNITS_SYSTEM, PrefsValues.UNITS_ISO);

	mLanguage = prefs.getString(PrefsNames.LANGUAGE, Locale.getDefault().getLanguage());
	if (!mLanguage.equals(PrefsValues.LANG_EN) && !mLanguage.equals(PrefsValues.LANG_ES)) {
	    mLanguage = PrefsValues.LANG_EN;
	}

	mHasLoadedData = prefs.getBoolean(PrefsNames.HAS_LOADED_DATA, Const.HAS_OFFLINE);

	resetParkingCalendar();

	/**
	 * Having a single Toast instance allows overriding (replacing) the messages and avoiding Toast stack delays.
	 */
	mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);

	updateUiLanguage();
    }

    public void showToastText(int res, int duration) {
	mToast.setText(res);
	mToast.setDuration(duration);
	mToast.show();
    }

    public void showToastText(String msg, int duration) {
	mToast.setText(msg);
	mToast.setDuration(duration);
	mToast.show();
    }

    /**
     * Used to force distance calculations. Mainly on first launch where an empty or partial DB cursor receives the
     * location update, ends up doing partial distance updates.
     */
    public void initializeLocation() {
	// TODO replace this by a listener or synch tasks
	mLocation = null;

	Editor prefsEditor = prefs.edit();

	prefsEditor.putFloat(PrefsNames.LAST_UPDATE_LAT, Float.NaN);
	prefsEditor.putFloat(PrefsNames.LAST_UPDATE_LNG, Float.NaN);
	prefsEditor.putLong(PrefsNames.LAST_UPDATE_TIME_GEO, System.currentTimeMillis());
	prefsEditor.commit();
    }

    public Location getLocation() {
	/**
	 * Background services save a passively set location in the Preferences.
	 */
	Float lastLat = prefs.getFloat(PrefsNames.LAST_UPDATE_LAT, Float.NaN);
	Float lastLng = prefs.getFloat(PrefsNames.LAST_UPDATE_LNG, Float.NaN);

	if (lastLat.equals(Float.NaN) || lastLng.equals(Float.NaN)) {
	    return mLocation;
	}

	mLocation = new Location(Const.LOCATION_PROVIDER_PREFS);
	mLocation.setLatitude(lastLat.doubleValue());
	mLocation.setLongitude(lastLng.doubleValue());

	return mLocation;
    }

    public void setLocation(Location location) {
	// Log.v(TAG, "setLocation");
	if (location == null) {
	    return;
	} else {
	    // Log.v(TAG, "new location = " + location.getLatitude() + "," +
	    // location.getLongitude());
	    if ((mLocation == null) || (this.mLocation.distanceTo(location) > Const.MAX_DISTANCE)) {
		Intent intent = new Intent(this.getApplicationContext(), DistanceUpdateService.class);
		intent.putExtra(Const.INTENT_EXTRA_GEO_LAT, location.getLatitude());
		intent.putExtra(Const.INTENT_EXTRA_GEO_LNG, location.getLongitude());
		startService(intent);
	    }
	    /**
	     * No need to save location in Preferences because it's done in the background services.
	     */

	    mLocation = location;
	}
    }

    public String getUnits() {
	return mUnits;
    }

    public void setUnits(String units) {
	this.mUnits = units;
    }

    public String getLanguage() {
	return mLanguage;
    }

    public void setLanguage(String lang) {
	this.mLanguage = lang;
	updateUiLanguage();
    }

    public boolean hasLoadedData() {
	return mHasLoadedData;
    }

    public void setHasLoadedData(boolean mHasLoadedData) {
	this.mHasLoadedData = mHasLoadedData;
	Editor prefsEditor = prefs.edit();
	prefsEditor.putBoolean(PrefsNames.HAS_LOADED_DATA, mHasLoadedData).commit();
    }

    public GregorianCalendar getParkingCalendar() {
	return mParkingCalendar;
    }

    public void setParkingCalendar(GregorianCalendar calendar) {
	mParkingCalendar = calendar;
    }

    public void resetParkingCalendar() {
	mParkingCalendar = new GregorianCalendar();
	mParkingDuration = Const.DURATION_DEFAULT;
    }

    public void setParkingDate(int year, int month, int day) {
	int hourOfDay = mParkingCalendar.get(Calendar.HOUR_OF_DAY);
	int minute = mParkingCalendar.get(Calendar.MINUTE);
	mParkingCalendar.set(year, month, day, hourOfDay, minute);

	// Log.v(TAG, "setParkingDate = " + mParkingCalendar.toString());
    }

    public void setParkingTime(int hourOfDay, int minute) {
	int year = mParkingCalendar.get(Calendar.YEAR);
	int month = mParkingCalendar.get(Calendar.MONTH);
	int day = mParkingCalendar.get(Calendar.DAY_OF_MONTH);

	mParkingCalendar.set(year, month, day, hourOfDay, minute);
    }

    public int getParkingDuration() {
	return mParkingDuration;
    }

    public void setParkingDuration(int duration) {
	// Log.v(TAG, "setParkingDuration = " + duration);
	mParkingDuration = duration;
    }

    /**
     * Force the configuration change to a locale different that the phone's.
     */
    public void updateUiLanguage() {
	Locale locale = new Locale(mLanguage);
	Configuration config = new Configuration();
	config.locale = locale;
	Locale.setDefault(locale);
	getBaseContext().getResources()
		.updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }

}
