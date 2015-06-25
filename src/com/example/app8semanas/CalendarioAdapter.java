package com.example.app8semanas;


import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.app8semanas.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CalendarioAdapter extends BaseAdapter {

	private Context 			mContext;
    private Date[]  			mWeek;
    private SimpleDateFormat 	mFormatNumber;
    private SimpleDateFormat 	mFormatDay;
    private LayoutInflater		mInflater;
	
	public CalendarioAdapter(Context _context, Date[] _week) {
		 	mContext 		= _context;
	        mWeek			= _week;
	        mFormatNumber	= new SimpleDateFormat("d");
	        mFormatDay  	= new SimpleDateFormat("EEE");
	        mInflater 		= LayoutInflater.from(mContext);  
	}

	private static class ViewHolder {
        public TextView		tvDay;
        public TextView		tvNumber;
    }
	
	@Override
	public int getCount() {
		return mWeek.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
        if (convertView == null) {
        	convertView		= mInflater.inflate(R.layout.calendario_dia, null);
        	holder			= new ViewHolder();
        	holder.tvDay 	= (TextView)  convertView.findViewById(R.id.tv_day);
        	holder.tvNumber	= (TextView)  convertView.findViewById(R.id.tv_number);
        	convertView.setTag(holder);
        } else {
        	holder = (ViewHolder) convertView.getTag();
        }

        holder.tvDay.setText(mFormatDay.format(mWeek[position]));
        holder.tvNumber.setText(mFormatNumber.format(mWeek[position]));
        return convertView;
	}

}
