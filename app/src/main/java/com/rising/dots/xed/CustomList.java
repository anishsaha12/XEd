package com.rising.dots.xed;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by anish on 31-03-2018.
 */

public class CustomList extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] web;

    public CustomList(Activity context,
                      String[] web) {
        super(context, R.layout.list_item, web);
        this.context = context;
        this.web = web;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_item, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.tvInfo);
        txtTitle.setText(web[position]);
        return rowView;
    }
}