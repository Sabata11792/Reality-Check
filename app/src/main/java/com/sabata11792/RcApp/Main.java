package com.sabata11792.RcApp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

//SABATA11792


public class Main extends Activity {
	//
	private static Handler mHandler = new Handler();
	//
		public static TextView txtUser1;
		public static TextView txtUser2;
		public static TextView txtDate;
		public static TextView txtDayNumber;
		public static TextView txtRandNumber;
		public static TextView txtRandNumber2;
		public static TextView txtSumNumber;
		public static TextView txtSumNumber2;
		public static Button btnRefresh;
		public static Button btnQuit;
		public static Button btnChkRandoms;
		//public static Button btnRandomTog;

    ///////GET AND SET


    public static Button getBtnChkRandoms() {
        return btnChkRandoms;
    }

    public static void setBtnChkRandoms(Button btnChkRandoms) {
        Main.btnChkRandoms = btnChkRandoms;
    }

    static Random rndNumber = new Random();
		
	static int intRandom1 = rndNumber.nextInt(100);
    int intVersioN = 4 ;
    static int intRefreshCount = 0;
    static boolean bMissRoll = false;
    static int iRepMiss = 0;
    private boolean bEnableMisses = true;
    private CharSequence txtToast;
	
		
		// goto refresh 
		
		
	
		
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // aassoicate stuff to the things
        btnRefresh = (Button)findViewById(R.id.btn_refresh);
		btnChkRandoms = (Button)findViewById(R.id.btnChkMisses);
        btnQuit= (Button)findViewById(R.id.btn_quit);
	
        txtUser1 = (TextView)findViewById(R.id.txtUser1);
        txtUser2 = (TextView)findViewById(R.id.txtUser2);
        txtDate = (TextView)findViewById(R.id.txtDate);
        txtDayNumber = (TextView)findViewById(R.id.txtDayNumber);
        txtRandNumber = (TextView)findViewById(R.id.txtRandNumber);
        txtRandNumber2 = (TextView)findViewById(R.id.txtRandNumber2);
        txtSumNumber = (TextView)findViewById(R.id.txtSum1);
        txtSumNumber2 = (TextView)findViewById(R.id.txtSum2);

        

        btnQuit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				

			if (bMissRoll == false){

				Exit();
			}
				if (bMissRoll == true){
					//Display error, insult user
					WrongCheck();
					
					bMissRoll = false;
				}	
				
			
			
			
				///// Refresh();/// set up qit function that will check if not showing inval numbers if crap numbers warn user popup else exit.
			}

			public void Exit() {
			
				
				SaveCount("iRepMiss",iRepMiss);
				System.exit(0);
			}

			public void WrongCheck() {
				//  CALL USER WONG
				

               // Button btnQuit= (Button)findViewById(R.id.btn_quit);
              Integer  intRoll2 = rndNumber.nextInt(4);
                if (intRoll2 == 1) {
                      txtToast = "Something's odd...";
                }else if (intRoll2 == 2){
                     txtToast = "Wait a minute...";
                }else if (intRoll2 == 3) {
                     txtToast= "That's not right.";
                    intRoll2 = rndNumber.nextInt(1000);
                    txtSumNumber.setText(intRoll2.toString());
                }else if (intRoll2 == 4){
                     txtToast =  "THINK!";
                }else {
                    txtToast = "Are you sure?";
                }


                Toast(txtToast);
                intRefreshCount = 0;
                Refresh();
		        btnQuit.setText("I'm awake.");

			}
		});

        btnRefresh.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			
				 Refresh();
			}
		});
        
        ///////
       Refresh();
        
        
    }

public void Toast(CharSequence msg){
    Context context = getApplicationContext();

    int duration = Toast.LENGTH_SHORT;
    Toast tToast = Toast.makeText(context, msg, duration);
    tToast.show();

}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    
  
    @Override//handels action menu
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.btnRefresh:
                Refresh();
                return true;
            case R.id.btnSettings:
                editText();
                return true;
            case R.id.btnChkMisses:

                toggleMisroll();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void toggleMisroll() {
        //on check
        //toggles fakeout based of checkbox



        if(bEnableMisses == true){
            bEnableMisses = false;
            Toast("Miss Rolls Disabled");
            intRefreshCount = 0  ;
        }else if (bEnableMisses == false){
            bEnableMisses = true;
            Toast("Miss Rolls Enabled");
        }



        //save prefrence bEnableMisses
        SavePreferences("bEnableMisses",bEnableMisses);

    }


    public void editText(){
    	//////TOP MESSAGE
    	AlertDialog.Builder alert = new AlertDialog.Builder(this);

    	alert.setTitle("Top Message");
    	alert.setMessage("Customise the top message");

    	// Set an EditText view to get user input 
    	final EditText input = new EditText(this);
    	alert.setView(input);

    	alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
    	public void onClick(DialogInterface dialog, int whichButton) {
    	 String strUser1 = input.getText().toString();
    	  // Do something with value!
    	 SavePreferences("txtUser1",strUser1);
    	 editText2();
    	 Refresh();
    	  }
    	});

    	alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

    	alert.show();
    	
    }
    	
    
    
    
    public void editText2(){
    	//////TOP MESSAGE
    	AlertDialog.Builder alert = new AlertDialog.Builder(this);

    	alert.setTitle("Bottom Message");
    	alert.setMessage("Customise the bottom message");

    	// Set an EditText view to get user input 
    	final EditText input = new EditText(this);
    	alert.setView(input);

    	alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String strUser2 = input.getText().toString();
                // Do something with value!
                SavePreferences("txtUser2", strUser2);
               // AllowRandom();
                Refresh();
            }
        });

    	alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

    	alert.show();
    	
    }
    	




    //savaing and loading options


    public void SavePreferences(String key, boolean value){

        //save bool enablemisses and others
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();

    }

	  public  void SavePreferences(String key, String value){
	    	SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
	    	SharedPreferences.Editor editor = sharedPreferences.edit();
	    	editor.putString(key, value);
	    	editor.commit();
	    }




    public void SaveCount (String key, int value){
	    	SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
	    	SharedPreferences.Editor editor = sharedPreferences.edit();
	    	editor.putInt(key, value);
	    	editor.commit();
		  
		 // SaveCount("iRepMiss",iRepMiss);
		  
	  }
	    
	  public void LoadCount(){
		  SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
		   iRepMiss = sharedPreferences.getInt("iRepMiss", 1);
		  
	  }
    public void LoadAllowMisses(){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        bEnableMisses = sharedPreferences.getBoolean("bEnableMisses", true);

    }
	  
	  
    public  void LoadPreferences(){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        String strUser1 = sharedPreferences.getString("txtUser1","Are you awake?" );
        String strUser2 = sharedPreferences.getString("txtUser2","You're Dreaming..." );
	    	
	    	txtUser1.setText(strUser1);
	    	txtUser2.setText(strUser2);
	    }



    public  void Refresh() {
    	//
    	
    	// look up shared prefs
    	
    	LoadPreferences();
    	
    	LoadCount();
        LoadAllowMisses();
    	
    	//shared prefs//
    	bMissRoll = false;
    	String strDate = DateFormat.getDateInstance().format(new Date());





		int intRandom1 = rndNumber.nextInt(100);
    	Integer.toString(intRandom1);
    	
    	
    	txtRandNumber.setText(Integer.toString(intRandom1));
    	txtRandNumber2.setText(Integer.toString(intRandom1));
    	
    	txtSumNumber.setText(Integer.toString(intRandom1 * 2));
    	
    	
    	
    	//generate unique date number
       	txtDate.setText(strDate);
    	Date dDate = new Date();
          	
       	
       	Calendar cal = Calendar.getInstance();
       	cal.setTime(dDate);
       	
       	int day = cal.get(Calendar.DAY_OF_YEAR) + 1;
       	int month = cal.get(Calendar.MONTH)+ 1;
       	int year = cal.get(Calendar.YEAR ) -2000 + 1 ;//shortened to display smaller number
       	
       	int dailyNumber = (day + month * (year - intVersioN));
       	
       	txtDayNumber.setText(Integer.toString(dailyNumber));
       	
       	txtSumNumber2.setText(Integer.toString(dailyNumber + intRandom1));
       	
       	intRefreshCount += 1;  //if refresh is too much prompt question
       	
       	//roll to on occasion scramble confirm number after 

       	int intRoll = rndNumber.nextInt((5000 / (intRefreshCount + 1)));
       //test roll//	int intRoll = 1;

        if (bEnableMisses==true){
       	    if (intRoll <= 1){
            //Toast("MisRoll");//DEBUG
       		fakeOut();
       	    };
        }




    }
    public void fakeOut(){
    	//set confirm number up or down
    	//bMissRoll = true;
    	//roll a  new number and send it to confirm text
        LoadCount();
    	int intRoll2 = rndNumber.nextInt(150);
    	txtRandNumber.setText(Integer.toString(intRoll2));
    	intRoll2 = rndNumber.nextInt(100 + intRefreshCount);
    	txtRandNumber2.setText(Integer.toString(intRoll2));

    	
    	bMissRoll = true;
    	// after changeing this if user press "this is ok" have it call them an idiot

    }

	public static Handler getmHandler() {
		return mHandler;
	}


	public static void setmHandler(Handler mHandler) {
		Main.mHandler = mHandler;
	}



    //start of reminders


}

 
    	
    	
        
    	
	
    

