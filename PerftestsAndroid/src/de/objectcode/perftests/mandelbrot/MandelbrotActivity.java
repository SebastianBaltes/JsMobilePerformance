package de.objectcode.perftests.mandelbrot;

import de.objectcode.perftests.utils.PerfLog;
import android.app.Activity;
import android.os.Bundle;

public class MandelbrotActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MandelbrotView(this));
    }

}
