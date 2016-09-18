package com.example.user123.smartwatch.ammsmartwatch;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SpectacleListAdaptor extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final Integer[] imgid;
    private final String[] times;
    private final Locations[] locs;

    public SpectacleListAdaptor(Activity context, String[] itemname, Integer[] imgid, String[] times, Locations[] locations) {
        super(context, R.layout.spectaclelist, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
        this.times = times;
        this.locs = locations;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.spectaclelist, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView locationText = (TextView) rowView.findViewById(R.id.textView1);
        TextView timetxt = (TextView) rowView.findViewById(R.id.textView);

        txtTitle.setText(itemname[position]);
        imageView.setImageResource(imgid[position]);
        locationText.setText(locs[position].getName());
        timetxt.setText(times[position]);
        return rowView;

    };
}
