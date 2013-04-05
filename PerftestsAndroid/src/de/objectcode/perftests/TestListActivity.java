package de.objectcode.perftests;

import de.objectcode.perftests.houghcircle.HoughCircleActivity;
import de.objectcode.perftests.mandelbrot.MandelbrotActivity;
import de.objectcode.perftests.manyobjects.ManyObjectsActivity;
import de.objectcode.perftests.utils.PerfLog;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class TestListActivity extends ListActivity {

	Test[] tests = new Test[] {
			new Test(MandelbrotActivity.class,"Mandelbrot"),
			new Test(ManyObjectsActivity.class,"ManyObjects"),
			new Test(StringActivity.class,"String"),
			new Test(HoughCircleActivity.class,"HoughCircle"),
			new Test(BinaryTreesActivity.class,"BinaryTrees"),
			new Test(SqlActivity.class,"Sql"),
			new Test(JsonRequestActivity.class,"JsonRequest"),
            new Test(LongScrollListActivity.class,"LongScrollList"),
            new SendLog(),
	};

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		ArrayAdapter<Test> adapter = new ArrayAdapter<Test>(this, android.R.layout.simple_list_item_1, tests);
		setListAdapter(adapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		tests[position].start();
	}

	class Test {
		Class<? extends Activity> activity;
		String name;
		
		public Test(Class<? extends Activity> activity, String name) {
			this.activity = activity;
			this.name = name;
		}
		
		public void start() {
			Toast.makeText(TestListActivity.this, "starting "+this, Toast.LENGTH_LONG).show();
            PerfLog.get().log("----------------------");
			PerfLog.get().log(this.toString());
            PerfLog.get().log("----------------------");
			new Thread() {
				public void run() {
					Intent intent = new Intent(TestListActivity.this, activity);
					startActivity(intent);
				};
			}.start();
		}
		
		public String toString() {
			return name;
		}
	}
	
	class SendLog extends Test {

        public SendLog() {
            super(null, null);
        }
	    
        public void start() {
            PerfLog.get().sendMail(TestListActivity.this);
        }
        
        public String toString() {
            return "send mail with log";
        }
	}
	
}