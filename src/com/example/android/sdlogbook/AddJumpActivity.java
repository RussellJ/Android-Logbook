package com.example.android.sdlogbook;

//import com.example.android.sdlogbook.AddJumpActivity.TextBoxFocusChangeListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
//PageConstants

public class AddJumpActivity extends ActivityBase {
	//Add jump controls
	private Button btnLogJump;
	private EditText etLocation; 
	private EditText etAltitude;
	private EditText etAircraft;
	private EditText etFreefallTime;
	private EditText etDescription;
	private TextView tvJumpNumber;
	private TextView tvFreefallTime;
	private DataAccess dx;
	
	private static final float MOVE_THRESHOLD = 20.0f;
	
	public AddJumpActivity()
	{
		
	}
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dx = Application.getInstance(this).getDataAccess();
        setContentView(R.layout.addjump);
        showAddJump();
    }
    private void showAddJump()
    {
    	setContentView(R.layout.addjump);
        this.btnLogJump = (Button)this.findViewById(R.id.btnLogJump);
        this.etLocation=HookUpEditText("Location",R.id.etLocation);
        this.etAircraft=HookUpEditText("Aircraft",R.id.etAircraft);
        this.etAltitude=HookUpEditText("Altitude",R.id.etAltitude);
        this.etFreefallTime=HookUpEditText("Delay",R.id.etDelay);
        this.etDescription=HookUpEditText("Description",R.id.etDescription);
        tvFreefallTime = (TextView)this.findViewById(R.id.tvFreefallTime);
        tvJumpNumber = (TextView)this.findViewById(R.id.tvJumpCount);
        setJumpNumber();
        setFreefallTime();
        this.btnLogJump.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				saveLogbookEntry(v);
			}
        });
    }
    private EditText HookUpEditText(String prompt,int etId)
    {
    	EditText edt = (EditText)this.findViewById(etId);
    	edt.setOnFocusChangeListener(new TextBoxFocusChangeListener(edt,prompt));
    	edt.setText(prompt);
    	return edt;
    }
    private void saveLogbookEntry(View v)
    {
    	dx.logJump(etLocation.getText().toString(), etAircraft.getText().toString(), etAltitude.getText().toString(),etFreefallTime.getText().toString());
    	setJumpNumber(dx.getCurrentJumpNumber());
    	tvFreefallTime.setText("Freefall time: " + Application.formatFreefallTime((int)dx.getFreefallTime()));
    }
    private void setFreefallTime()
    {
    	tvFreefallTime.setText("Freefall time: " + Application.formatFreefallTime((int)dx.getFreefallTime()));
    }
    private void setJumpNumber()
    {
    	setJumpNumber(dx.getCurrentJumpNumber());
    }
    private void setJumpNumber(int jumpNumber)
    {
    	tvJumpNumber.setText("Next Jump No.: "+(jumpNumber+1));
    }
    public class TextBoxFocusChangeListener implements OnFocusChangeListener
    {
    	private EditText edittext;
    	private String Prompt;
    	public  TextBoxFocusChangeListener(EditText editText, String prompt)
    	{
    		edittext = editText;
    		Prompt = prompt;
    	}
		public void onFocusChange(View v, boolean hasFocus) {
			if(hasFocus)
			{
				edittext.setText("");
			}	
			else
			{
				if(edittext.getText().toString().equals(""))
				{
					edittext.setText(Prompt);
				}
			}
		}
    }
    /*@Override
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
	public boolean onTouch(View arg0, MotionEvent arg1) {
		float startX = 0;
		float startY = 0;
		switch(arg1.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			startX = arg1.getX();
			startY = arg1.getY();
			break;
		case MotionEvent.ACTION_UP:
			float diffX = startX - arg1.getX();
			if ( Math.abs(diffX) > MOVE_THRESHOLD)
			{
		         Intent myIntent = new Intent(this.getApplicationContext(), ReportingActivity.class);
		         startActivityForResult(myIntent, 0);
			}
			break;
		}
		return false;
	}*/
}
