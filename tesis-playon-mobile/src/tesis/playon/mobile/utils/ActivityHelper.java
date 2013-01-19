package tesis.playon.mobile.utils;

import tesis.playon.mobile.Const;
import tesis.playon.mobile.R;
import tesis.playon.mobile.ui.activities.AboutActivity;
import tesis.playon.mobile.ui.activities.HelpActivity;
import tesis.playon.mobile.ui.activities.MyPreferenceActivity;
import tesis.playon.mobile.ui.activities.MyPreferenceActivityHC;

import com.actionbarsherlock.view.MenuItem;

import android.app.Activity;
import android.content.Intent;

public class ActivityHelper {
    
    @SuppressWarnings("unused")
    private static final String TAG = "ActivityHelper";

    protected Activity mActivity;

    public static ActivityHelper createInstance(Activity activity) {
	System.setProperty("http.keepAlive", "false");
	return new ActivityHelper(activity);
    }

    protected ActivityHelper(Activity activity) {
	mActivity = activity;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
	Intent intent = new Intent();

	int id = item.getItemId();

	if (id == R.id.menu_help) {
	    intent = new Intent(mActivity, HelpActivity.class);
	    mActivity.startActivity(intent);
	    return true;
	} else if (id == R.id.menu_settings) {

	    if (Const.SUPPORTS_HONEYCOMB) {
		intent = new Intent(mActivity, MyPreferenceActivityHC.class);
	    } else {
		intent = new Intent(mActivity, MyPreferenceActivity.class);
	    }

	    mActivity.startActivity(intent);
	    return true;
	} else if (id == R.id.menu_about) {
	    intent = new Intent(mActivity, AboutActivity.class);
	    mActivity.startActivity(intent);
	    return true;
	}

	return false;
    }
}