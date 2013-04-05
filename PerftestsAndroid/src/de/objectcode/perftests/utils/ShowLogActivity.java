package de.objectcode.perftests.utils;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public abstract class ShowLogActivity extends Activity {

	TextView v;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = new TextView(this);
        setContentView(v);
        perform();
    }

    public void log(final String message) {
        runOnUiThread(new Runnable() {
            public void run() {
                v.setText(v.getText()+message+"\n");
                PerfLog.get().log(message);
            }
        });        
    }
    
	public abstract void perform();
    
}
