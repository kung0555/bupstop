package com.example.faster.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Ben Kung on 05-Jul-17.
 */

public class BusAdapter extends BaseAdapter {

    private Context context;
    private String[] NameBusStrings,detailBusStrings;
    private TextView NumberBusTextView,detailBusTextView;


    public BusAdapter(Context context, String[] nameBusStrings, String[] detailBusStrings) {
        this.context = context;
        NameBusStrings = nameBusStrings;
        this.detailBusStrings = detailBusStrings;
    }


    @Override
    public int getCount() {
        return NameBusStrings.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.buslisview,viewGroup,false);


        NumberBusTextView = (TextView) view1.findViewById(R.id.txtNumberBus);
        NumberBusTextView.setText(NameBusStrings[i]);

        detailBusTextView =(TextView) view1.findViewById(R.id.txtdetialBus);
        detailBusTextView.setText(detailBusStrings[i]);

        return view1;
    }
}
