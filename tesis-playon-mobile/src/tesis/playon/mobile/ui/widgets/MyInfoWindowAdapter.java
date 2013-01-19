package tesis.playon.mobile.ui.widgets;

import tesis.playon.mobile.R;
import android.app.Activity;
import android.text.SpannableString;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;

public class MyInfoWindowAdapter implements InfoWindowAdapter {

    private final View mView;

    public MyInfoWindowAdapter(Activity activity) {

	mView = activity.getLayoutInflater().inflate(R.layout.custom_info_window, null);
    }

    @Override
    public View getInfoContents(Marker marker) {

	final String title = marker.getTitle();
	final String snippet = marker.getSnippet();
	final TextView titleUi = (TextView) mView.findViewById(R.id.title);
	final View subtitleUi = mView.findViewById(R.id.subtitle);
	final TextView snippetUi = ((TextView) mView.findViewById(R.id.snippet));

	if (title != null) {
	    titleUi.setText(title);
	} else {
	    titleUi.setText(R.string.map_marker_snippet_title);
	}

	if (snippet == null) {
	    subtitleUi.setVisibility(View.GONE);
	    snippetUi.setVisibility(View.GONE);
	} else {
	    subtitleUi.setVisibility(View.VISIBLE);
	    snippetUi.setVisibility(View.VISIBLE);
	    final SpannableString snippetText = new SpannableString(snippet);
	    snippetUi.setText(snippetText);
	}
	return mView;
    }

    @Override
    public View getInfoWindow(Marker marker) {

	return null;
    }
}