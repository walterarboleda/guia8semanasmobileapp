package com.example.app8semanas;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import com.example.app8semanas.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CalendarioView extends LinearLayout implements OnClickListener, OnItemClickListener{

	private GridView 						mGrid;
	private View 							mConvertView;
	private GregorianCalendar 				mCalendar;
	private Date[] 							mWeek;
	private Context							mContext;
	private TextView						mMonthText;
	private SimpleDateFormat 				mFormatMonth;
	private SimpleDateFormat 				mFormatDay;
	private SimpleDateFormat 				mFormatYear;
	private OnDispatchDateSelectListener 	mListenerDateSelect;
	private Button							mArrowRight;
	private Button							mArrowLeft;
	private CalendarioAdapter					mAdapter;
	public interface OnDispatchDateSelectListener {
		public void onDispatchDateSelect(Date date);
	}
	@SuppressLint("SimpleDateFormat")
	public CalendarioView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext			= context;
		mFormatMonth		= new SimpleDateFormat("MMMM");
		mFormatDay			= new SimpleDateFormat("d");
		mFormatYear			= new SimpleDateFormat("yyyy");
		  
		mConvertView = LayoutInflater.from(context).inflate(R.layout.calendario, this);
		mGrid=(GridView)mConvertView.findViewById(R.id.calendar_days);
		
		mGrid.setOnItemClickListener(this);
			
		mMonthText=(TextView)mConvertView.findViewById(R.id.calendar_month);
		mArrowLeft=(Button)findViewById(R.id.calendar_arrow_left);
		mArrowLeft.setOnClickListener(this);
		
		mArrowRight=(Button)mConvertView.findViewById(R.id.calendar_arrow_right);
		mArrowRight.setOnClickListener(this);
		
		mCalendar = (GregorianCalendar)GregorianCalendar.getInstance();
		mCalendar.setTime(new Date());
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		day--;
		mCalendar.add(Calendar.DAY_OF_YEAR, -day);
			
	    mWeek=new Date[7];
	    for(int i=0;i<7;i++)
	    {
	    	mWeek[i]=mCalendar.getTime();
	 
	    	mCalendar.add(Calendar.DAY_OF_YEAR, 1);
	    }
	    setSelectedMonthText();

	    mAdapter=new CalendarioAdapter(mContext, mWeek);
    	
	    mGrid.setAdapter(mAdapter);
	}
	
	public void fechaHoy(Date fechahoy){
		mListenerDateSelect.onDispatchDateSelect(fechahoy);
	}
	
	@Override
	public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {
		clearBackground();
		v.setBackgroundColor(Color.parseColor("#3A9CE9"));
		mListenerDateSelect.onDispatchDateSelect(mWeek[arg2]);
	}
	
	

	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
			case R.id.calendar_arrow_left:
				subWeek();
			break;
			case R.id.calendar_arrow_right:
				addWeek();
			break;
		}
	}
	
	private void addWeek()
	{
		for(int i=0;i<7;i++)
		{
			mWeek[i]=mCalendar.getTime();
	    	mCalendar.add(Calendar.DAY_OF_YEAR, 1);
		}

		mAdapter.notifyDataSetChanged();
		
		setSelectedMonthText(); 
		clearBackground();
	}
	
	private void clearBackground()
	{
		for(int i=0;i<mGrid.getCount();i++)
		{
			mGrid.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
		}
	}
	
	private void subWeek()
	{
		 mCalendar.add(Calendar.DAY_OF_YEAR, -14);
		 for(int i=0;i<7;i++)
		 {
		    	mWeek[i]=mCalendar.getTime();
		    	mCalendar.add(Calendar.DAY_OF_YEAR, 1);
		}
		 setSelectedMonthText();
		 mAdapter.notifyDataSetChanged();

		 clearBackground();
	}
	
	private void setSelectedMonthText()
	{
		String monthText;
		if(Integer.parseInt(mFormatDay.format(mWeek[0]))>Integer.parseInt(mFormatDay.format(mWeek[6])))
			monthText=mFormatMonth.format(mWeek[0])+" / "+mFormatMonth.format(mWeek[6]);
		else
			monthText=mFormatMonth.format(mWeek[0]);
		
		mMonthText.setText(monthText+" "+mFormatYear.format(mWeek[6]));
	}
	
	public void setOnDispatchDateSelectListener(OnDispatchDateSelectListener v) {
		mListenerDateSelect = v;
	}

}

