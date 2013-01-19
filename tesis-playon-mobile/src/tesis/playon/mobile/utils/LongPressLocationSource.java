package tesis.playon.mobile.utils;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.model.LatLng;

import android.location.Location;

public class LongPressLocationSource implements LocationSource, OnMapLongClickListener {

    protected static final String TAG = "LongPressLocationSource";

    private OnLocationChangedListener mListener;

    public LongPressLocationSource(GoogleMap map) {
	this.mMap = map;
    }

    private boolean mPaused;

    private GoogleMap mMap;

    @Override
    public void activate(OnLocationChangedListener listener) {
	mListener = listener;
    }

    @Override
    public void deactivate() {
	mListener = null;
    }

    @Override
    public void onMapLongClick(LatLng point) {
	mMap.setLocationSource(this);

	if (mListener != null && !mPaused) {

	    Location location = new Location("LongPressLocationProvider");
	    location.setLatitude(point.latitude);
	    location.setLongitude(point.longitude);
	    mListener.onLocationChanged(location);
	}
    }

    public void onPause() {
	mPaused = true;
	mMap.setLocationSource(null);
    }

    public void onResume() {
	mPaused = false;
    }
}