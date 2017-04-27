package org.blocorganization.blocapp.utils;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.blocorganization.blocapp.R;

import java.util.List;

class SpinnerAdapter extends BaseAdapter {

    private List<String> lstData;
    private LayoutInflater inflater;

    SpinnerAdapter(List<String> lstData, Activity activity) {
        this.lstData = lstData;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return lstData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            view = inflater.inflate(R.layout.create_spinner_item, parent, false);
        }
        TextView tvSpinner = (TextView) view.findViewById(R.id.tvSpinner);
        tvSpinner.setText(lstData.get(position));
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);
        LinearLayout layout = (LinearLayout) view;
        TextView tvSpinner = (TextView) layout.findViewById(R.id.tvSpinner);
        tvSpinner.setGravity(Gravity.START);
        tvSpinner.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        return view;
    }

}
