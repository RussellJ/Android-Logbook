package com.example.android.sdlogbook;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Parcelable.Creator;
import android.util.Log;

public class DataAccess  {
	private static final String DATABASE_NAME = "LogBook";
    private static final String DATABASE_TABLE = "Jumps";
    public static final String KEY_ROWID = "_id";
    public static final String KEY_PLACE = "place";
    public static final String KEY_AIRCRAFT = "aircraft";
    public static final String KEY_ALTITUDE = "altitude";  
    public static final String KEY_FFTIME = "fftime";
    private static final String TAG = "DBAdapter";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
        "create table Jumps (_id integer primary key autoincrement, "
        + "place text not null, "
        +"aircraft text not null, " 
        +"fftime int not null, "
        + "altitude text not null);";
        
    private final Context context; 
    private DatabaseHelper DBHelper;
    SQLiteDatabase db = null; 
    public DataAccess(Context ctx)
    {
    	context = ctx;
    	this.DBHelper = new DatabaseHelper(context);
    }
    public DataAccess open() throws SQLException 
    {
    	try
    	{
        db = DBHelper.getWritableDatabase();
    	}
    	catch(Exception ex)
    	{
    		int i =0;
    		i++;
    		
    	}
        return this;
    }

    //---closes the database---    
    public void close() 
    {
        DBHelper.close();
    }
    public void logJump(String location,String aircraft, String altitude, String ffTime)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_PLACE, location);
        initialValues.put(KEY_AIRCRAFT, aircraft);
        initialValues.put(KEY_ALTITUDE, altitude);
        initialValues.put(KEY_FFTIME, ffTime);
        db.insert(DATABASE_TABLE, null, initialValues);
    }
    public int getCurrentJumpNumber()
    {
    	Cursor curs =db.rawQuery("SELECT COUNT (*) FROM " + DATABASE_TABLE, null);
    	curs.moveToFirst();
    	return curs.getInt(0);
    	//Cursor curs = db.query(DATABASE_TABLE, new String[]{KEY_PLACE,KEY_AIRCRAFT,KEY_ALTITUDE}, null, null, null, null, null);
    	//return curs.getCount();
    }
    public int getNumberOfDropzones()
    {
    	Cursor curs = db.rawQuery("SELECT COUNT(DISTINCT "+KEY_PLACE+")FROM " + DATABASE_TABLE, null);
    	curs.moveToFirst();
    	return curs.getInt(0);
    }
    public int getFreefallTime()
    {
    		Cursor curs = db.rawQuery("SELECT SUM("+KEY_FFTIME+")FROM " + DATABASE_TABLE, null);
	    	curs.moveToFirst();
	    	return curs.getInt(0);
    }
    public String getMostCommonDZ()
    {
	    	String sql = String.format("select %s, count(%s) from %s group by %s",KEY_PLACE,KEY_PLACE,DATABASE_TABLE,KEY_PLACE);
	    	Cursor curs = db.rawQuery(sql,null);
	    	curs.moveToFirst();
	    	if(curs.getCount()>0)
	    		return curs.getString(0);
	    	return "";
    }
    private static class DatabaseHelper extends SQLiteOpenHelper 
    {
        DatabaseHelper(Context context) 
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) 
        {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, 
                              int newVersion) 
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion 
                  + " to "
                  + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }    

}
