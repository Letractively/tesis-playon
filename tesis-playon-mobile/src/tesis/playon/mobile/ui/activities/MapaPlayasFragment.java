package tesis.playon.mobile.ui.activities;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class MapaPlayasFragment extends ListFragment {

    String[] countries = new String[] { "1111", "2222", "3333", "4444", "5555", "6666", "7777", "8888", "9999", "0000" };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

	ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(),
		android.R.layout.simple_list_item_1, countries);

	setListAdapter(adapter);

	return super.onCreateView(inflater, container, savedInstanceState);
    }

}
