package de.objectcode.perftests.houghcircle;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import de.objectcode.perftests.R;
import de.objectcode.perftests.utils.PerfLog;
import de.objectcode.perftests.utils.StopWatch;

public class HoughCircleActivity extends Activity {

	TextView text;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linear = new LinearLayout(this);
        linear.setOrientation(LinearLayout.VERTICAL);
        ImageView imageView = new ImageView(this);
        linear.addView(imageView);
        text = new TextView(this);
        linear.addView(text);
        setContentView(linear);
        
        StopWatch w = new StopWatch();
        Bitmap image = BitmapFactory.decodeResource(this.getResources(), R.drawable.balls).copy(Config.ARGB_8888 ,true);
        HoughCircleDetection hough = new HoughCircleDetection();
        int searchRadiusOnOriginalImage = 39;
        int originalImageWidth = 200;
        int radiusScaled = searchRadiusOnOriginalImage*image.getWidth()/originalImageWidth;
        hough.markCircle(image,radiusScaled);
        imageView.setImageBitmap(image);
        log(w.stop());
    }

    public void log(String message) {
    	text.setText(text.getText()+message+"\n");
        PerfLog.get().log(message);
    }

}
