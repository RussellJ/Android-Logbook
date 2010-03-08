package com.example.android.sdlogbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ReportingActivity extends ActivityBase {
	//Reporting Controls
	private DataAccess dx;
	private TextView tvRptJumpNumber;
	private  TextView tvRptFreefallTime;
	private TextView tvRptDZCount;
	private  TextView tvRptMostVisited;
	private TextView tvRptAircraftJumped;
	private  TextView tvRptMostJumped;
	public ReportingActivity()
	{
		
	}
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dx = Application.getInstance(this).getDataAccess();
        setContentView(R.layout.reporting);
        showReporting();
    }
    private void showReporting()
    {
    	setReportLine(tvRptFreefallTime, "Total time in Freefall (h.mm.ss): %s", Application.formatFreefallTime(dx.getFreefallTime()), R.id.tvRptFreefallTime);
    	setReportLine(tvRptDZCount, "Total DZs visited: %s", ""+dx.getNumberOfDropzones(), R.id.tvRptDZCount);
    	setReportLine(tvRptMostVisited,"%s is your most regular DZ", dx.getMostCommonDZ(),R.id.tvRptMostVisited);
    }
    private void setReportLine(TextView tv, String message, String value, int id)
    {
    	tv = (TextView)this.findViewById(id);
    	tv.setText(String.format(message,value));
    }/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
     super.onOptionsItemSelected(item);
     Intent myIntent = null;
     switch(item.getItemId())
     {
     case Application.REPORTING_PAGE:
 		myIntent = new Intent(this.getApplicationContext(), ReportingActivity.class);
        startActivityForResult(myIntent, 0);
    	 break;
     case Application.ADD_JUMPS_PAGE:
         myIntent = new Intent(this.getApplicationContext(), AddJumpActivity.class);
         startActivityForResult(myIntent, 0);
    	 break;
     case Application.OPTIONS_PAGE:
    	// showOptions();
    	 break;
     }
     return false;
    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {  
    	super.onCreateOptionsMenu(menu);
    	
    	menu.add(0,Application.ADD_JUMPS_PAGE,0,"Add Jump");
    	menu.add(0,Application.REPORTING_PAGE,0,"Report");
    	menu.add(0,Application.OPTIONS_PAGE,0,"Options");
    	return true;
    }*/
}
