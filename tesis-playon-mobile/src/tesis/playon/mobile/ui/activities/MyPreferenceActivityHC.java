package tesis.playon.mobile.ui.activities;

import java.util.List;
import java.util.Locale;

import tesis.playon.mobile.Const;
import tesis.playon.mobile.Const.PrefsNames;
import tesis.playon.mobile.Const.PrefsValues;
import tesis.playon.mobile.PlayonApp;
import tesis.playon.mobile.R;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockPreferenceActivity;
import com.actionbarsherlock.view.MenuItem;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MyPreferenceActivityHC extends SherlockPreferenceActivity {
    protected static final String TAG = "MyPreferenceActivityHC";

    protected PlayonApp mParkingApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	mParkingApp = (PlayonApp) getApplicationContext();
	mParkingApp.updateUiLanguage();

	ActionBar ab = getSupportActionBar();

	ab.setDisplayHomeAsUpEnabled(true);
	ab.setTitle(R.string.activity_preferences);

	showBreadCrumbs(getResources().getString(R.string.prefs_breadcrumb), null);
    }

    @Override
    public Intent getIntent() {
	final Intent intent = new Intent(super.getIntent());
	intent.putExtra(PreferenceActivity.EXTRA_SHOW_FRAGMENT, MyPrefsFragment.class.getName());
	intent.putExtra(PreferenceActivity.EXTRA_NO_HEADERS, true);
	return intent;
    }

    @Override
    public void onBuildHeaders(List<Header> target) {
	loadHeadersFromResource(R.xml.preference_headers, target);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	if (item.getItemId() == android.R.id.home) {
	    finish();
	    return true;
	}
	return super.onOptionsItemSelected(item);
    }

    protected void onConfigurationChanged(String lg) {

	mParkingApp.setLanguage(lg);
	mParkingApp.updateUiLanguage();

	finish();
	Intent intent = new Intent(getApplicationContext(), MyPreferenceActivityHC.class);
	overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
	startActivity(intent);
    }

    public static class MyPrefsFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {

	protected SharedPreferences mSharedPrefs;

	ListPreference tUnits;
	ListPreference tLanguage;

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	    PreferenceManager pm = this.getPreferenceManager();
	    pm.setSharedPreferencesName(Const.APP_PREFS_NAME);
	    pm.setSharedPreferencesMode(Context.MODE_PRIVATE);

	    // Load the preferences from an XML resource
	    addPreferencesFromResource(R.xml.preferences);

	    mSharedPrefs = pm.getSharedPreferences();

	    tUnits = (ListPreference) findPreference(PrefsNames.UNITS_SYSTEM);
	    tLanguage = (ListPreference) findPreference(PrefsNames.LANGUAGE);
	}

	@Override
	public void onResume() {
	    super.onResume();

	    tUnits.setSummary(getSummaryByValue(mSharedPrefs.getString(PrefsNames.UNITS_SYSTEM, PrefsValues.UNITS_ISO)));

	    String lg = mSharedPrefs.getString(PrefsNames.LANGUAGE, Locale.getDefault().getLanguage());
	    if (!lg.equals(PrefsValues.LANG_EN) && !lg.equals(PrefsValues.LANG_ES)) {
		lg = PrefsValues.LANG_EN;
	    }
	    tLanguage.setSummary(getSummaryByValue(mSharedPrefs.getString(PrefsNames.LANGUAGE, lg)));
	    tLanguage.setValue(lg);

	    if (mSharedPrefs != null) {
		mSharedPrefs.registerOnSharedPreferenceChangeListener(this);
	    }
	}

	@Override
	public void onPause() {
	    if (mSharedPrefs != null) {
		mSharedPrefs.unregisterOnSharedPreferenceChangeListener(this);
	    }

	    super.onPause();
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
	    if (key.equals(PrefsNames.UNITS_SYSTEM)) {
		String units = sharedPreferences.getString(key, PrefsValues.UNITS_ISO);
		tUnits.setSummary(getSummaryByValue(units));
		((PlayonApp) getActivity().getApplicationContext()).setUnits(units);
	    } else if (key.equals(PrefsNames.LANGUAGE)) {
		String lg = sharedPreferences.getString(key, Locale.getDefault().getLanguage());
		tLanguage.setSummary(getSummaryByValue(lg));
		((MyPreferenceActivityHC) getActivity()).onConfigurationChanged(lg);
	    }
	}

	private String getSummaryByValue(String index) {
	    if (index == null) {
		return "";
	    } else if (index.equals(PrefsValues.UNITS_ISO)) {
		return getResources().getString(R.string.prefs_units_iso);
	    } else if (index.equals(PrefsValues.UNITS_IMP)) {
		return getResources().getString(R.string.prefs_units_imperial);
	    } else if (index.equals(PrefsValues.LANG_ES)) {
		return getResources().getString(R.string.prefs_language_spanish);
	    } else if (index.equals(PrefsValues.LANG_EN)) {
		return getResources().getString(R.string.prefs_language_english);
	    } else {
		return "";
	    }
	}
    }
}