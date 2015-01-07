package com.example.test;

import java.math.BigDecimal;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayResultActivity extends ActionBarActivity {

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Intent intent = getIntent();
        float value;
        
        String toUnit = intent.getStringExtra("EXTRA_toUnit");
        String fromUnit = intent.getStringExtra("EXTRA_fromUnit");
        value = Float.parseFloat(intent.getStringExtra("EXTRA_MESSAGE"));
        
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        float result = 0;
        Toast.makeText(getBaseContext(), toUnit, Toast.LENGTH_SHORT).show();
        
        if((toUnit.equals("\u00B0 Fahrenheit")) && (fromUnit.equals("\u00B0 Celsius"))){
        	result = convertToFahr(value);
        } else if((toUnit.equals("\u00B0 Celsius")) && (fromUnit.equals("\u00B0 Fahrenheit"))){
        	result = convertToCels(value);
        } else if(toUnit.equals(fromUnit)){
        	result = value;
        }
        
        // String.format("%.2f", result);
        textView.setText(Float.toString(round(result, 2)));
        setContentView(textView);
    }
	
	public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    private float convertToFahr(float value) {
		// TODO Auto-generated method stub
    	float result = (float) ((value*1.8)+32);
		return result;
	}
    
    private float convertToCels(float value) {
		// TODO Auto-generated method stub
    	float result = (float) ((value-32)/1.8);
		return result;
	}
    
    @SuppressWarnings("unused")
	private float convertToKelv(float value) {
		// TODO Auto-generated method stub
    	float result = (float) ((value-32)/1.8);
		return result;
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
}
