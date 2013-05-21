package tesis.playon.mobile.ui.activities;

import tesis.playon.mobile.R;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PlayaFragment extends Fragment {

    private Context context;
    private View mView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	this.context = getActivity();
	super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	mView = inflater.inflate(R.layout.playa_desc, container, false);
	return mView;
    }

}
