package com.example.android.sdlogbook;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class ActivityBase extends Activity{
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
    }
}
