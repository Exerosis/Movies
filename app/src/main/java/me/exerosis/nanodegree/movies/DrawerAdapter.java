package me.exerosis.nanodegree.movies;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;


public class DrawerAdapter extends ArrayAdapter<Drawer> implements ListView.OnItemClickListener {
    public DrawerAdapter(Context context, int resource, List<Drawer> drawers) {
        super(context, resource, drawers);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Drawer drawer = getItem(position);

    }
}
