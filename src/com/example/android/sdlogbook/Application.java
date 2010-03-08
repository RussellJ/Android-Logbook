package com.example.android.sdlogbook;

import android.app.Activity;

public class Application {
	private static Application _instance = null;
	private DataAccess dx = null;
	public static final int REPORTING_PAGE = 0;
	public static final int ADD_JUMPS_PAGE = 1;
	public static final int OPTIONS_PAGE = 2;
	public static Application getInstance(Activity act)
	{
		if (_instance == null)
			_instance = new Application(act);
		return _instance;
	}
	private Application(Activity act)
	{
		dx = new DataAccess(act);
		dx.open();
	}
	public DataAccess getDataAccess()
	{
		return dx;
	}
    public static String formatFreefallTime(int totalSeconds)
    {
    	int hrs = 0;
    	int minutes = 0;
    	int seconds = 0;
    	hrs = (int)Math.floor( totalSeconds / 3600);
    	minutes = (int)Math.floor((totalSeconds-hrs*3600)/60);
    	seconds = totalSeconds-hrs*3600-minutes*60;
    	return formatFreefallTime(hrs,minutes,seconds);
    }
    public static String formatFreefallTime(int hours,int minutes, int seconds)
    {
    	return "" + hours + ":" +String.format("%02d", minutes)  +":" + String.format("%02d", seconds);
    }
}
