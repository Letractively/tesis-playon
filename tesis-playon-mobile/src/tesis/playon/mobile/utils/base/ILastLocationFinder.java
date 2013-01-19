package tesis.playon.mobile.utils.base;

import android.location.Location;
import android.location.LocationListener;

public interface ILastLocationFinder {

    public Location getLastBestLocation(int minDistance, long minTime);

    public void setChangedLocationListener(LocationListener l);

    public void cancel();
}
