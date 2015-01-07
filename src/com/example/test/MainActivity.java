package com.example.test;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	public static final String PREFS_NAME = "MyPrefsFIle";
	EditText tempText;
	String value;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		
		tempText = (EditText) findViewById(R.id.edit_message);
		value = settings.getString("rememberedTemp", "");
		tempText.setText(value);
		
		Spinner spinner = (Spinner) findViewById(R.id.units_spinner);
		
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.unitsArray, android.R.layout.simple_spinner_item);
		
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		
		// setup for the second spinner
		Spinner selector = (Spinner) findViewById(R.id.conversion_selector);
		selector.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onStop(){
		super.onStop();
		storeTemp();
	}
	
	private void storeTemp(){
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("rememberTemp", value);
		editor.commit();
	}
	
	/** Called when the user clicks the Send button */
	public void sendMessage(View view) {
	    // Do something in response to button
		value = tempText.getText().toString();
		
		Spinner spinner = (Spinner)findViewById(R.id.units_spinner);
		String fromUnit = spinner.getSelectedItem().toString();
		
		spinner = (Spinner)findViewById(R.id.conversion_selector);
		String toUnit = spinner.getSelectedItem().toString();
		
		if(value.matches("")){
			Toast.makeText(getBaseContext(), R.string.toastText, Toast.LENGTH_SHORT).show();
		} else {
			Intent intent = new Intent(this, DisplayResultActivity.class);
			intent.putExtra("EXTRA_MESSAGE", value);
			intent.putExtra("EXTRA_toUnit", toUnit);
			intent.putExtra("EXTRA_fromUnit", fromUnit);
			startActivity(intent);
		}
	}
}
