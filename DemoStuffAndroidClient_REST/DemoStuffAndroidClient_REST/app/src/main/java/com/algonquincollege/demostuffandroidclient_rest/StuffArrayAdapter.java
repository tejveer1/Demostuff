package com.algonquincollege.demostuffandroidclient_rest;

import android.widget.ArrayAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by spieda on 2018-03-13.
 */
public class StuffArrayAdapter extends ArrayAdapter<Stuff> {

    private List<Stuff> stuffList = new ArrayList<>();
    private StuffArrayAdapter stuffArrayAdapter;
    private ListView stuffListView;
    // class for reusing views as list items scroll off and onto the screen
    private static class ViewHolder {
     //   Prepared by Stanley Pieda (2018) for Algonquin College 8
        TextView deltaTextView;
        TextView thetaTextView;
        TextView idTextView;
        TextView omegaTextView;
        TextView recordNumberTextView;
        TextView lambdaTextView;
    }
    // constructor to initialize superclass inherited members
    public StuffArrayAdapter(Context context, List<Stuff> stuffs) {
        super(context, -1, stuffs);
    }
    // creates the custom views for the ListView's items
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        try {
            // get Stuff object for this specified ListView position
            Stuff stuffItem = getItem(position);
            ViewHolder viewHolder; // object that reference's list item's views
            // check for reusable ViewHolder from a ListView item that scrolled
            // offscreen; otherwise, create a new ViewHolder
            if (convertView == null) { // no reusable ViewHolder, so create one
                viewHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView =
                        inflater.inflate(R.layout.list_item, parent, false);
                viewHolder.idTextView =
                        (TextView) convertView.findViewById(R.id.idTextView);
                viewHolder.deltaTextView =
                        (TextView) convertView.findViewById(R.id.deltaTextView);
                viewHolder.thetaTextView =
                        (TextView) convertView.findViewById(R.id.thetaTextView);
                viewHolder.omegaTextView =
                        (TextView) convertView.findViewById(R.id.omegaTextView);
                viewHolder.lambdaTextView =
                        (TextView) convertView.findViewById(R.id.lambdaTextView);
                viewHolder.recordNumberTextView =
                        (TextView) convertView.findViewById(R.id.recordNumberTextView);
                convertView.setTag(viewHolder);
            } else { // reuse existing ViewHolder stored as the list item's tag
                viewHolder = (ViewHolder) convertView.getTag();
            }
            // get other data from Stuff object and place into views
            Context context = getContext(); // for loading String resources
            viewHolder.idTextView.setText(context.getString(
                    R.string.demoStuff_id, stuffItem.id));
           // Prepared by Stanley Pieda (2018) for Algonquin College 9
            viewHolder.deltaTextView.setText(
                    context.getString(R.string.demoStuff_stuff, stuffItem.delta));
            viewHolder.thetaTextView.setText(
                    context.getString(R.string.demoStuff_moreStuff, stuffItem.theta));
            viewHolder.omegaTextView.setText(
                    context.getString(R.string.demoStuff_moreStuff, stuffItem.omega));
            viewHolder.lambdaTextView.setText(
                    context.getString(R.string.demoStuff_moreStuff, stuffItem.lambda));
            viewHolder.recordNumberTextView.setText(
                    context.getString(R.string.demoStuff_moreStuff, stuffItem.recordNumber));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return convertView; // return completed list item to display
    }
}