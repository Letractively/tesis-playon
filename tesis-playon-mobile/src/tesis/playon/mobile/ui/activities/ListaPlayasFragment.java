package tesis.playon.mobile.ui.activities;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListaPlayasFragment extends ListFragment {

    String[] countries = new String[] { "India", "Pakistan", "Sri Lanka", "China", "Bangladesh", "Nepal",
	    "Afghanistan", "North Korea", "South Korea", "Japan" };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	/** Creating an array adapter to store the list of countries **/
	ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(),
		android.R.layout.simple_list_item_1, countries);

	/** Setting the list adapter for the ListFragment */
	setListAdapter(adapter);

	return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
	Log.i("FragmentList", "Item clicked: " + id);
    }

}