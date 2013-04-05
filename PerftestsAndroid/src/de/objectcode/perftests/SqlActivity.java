package de.objectcode.perftests;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import de.objectcode.perftests.utils.RandomValues;
import de.objectcode.perftests.utils.ShowLogActivity;
import de.objectcode.perftests.utils.StopWatch;

public class SqlActivity extends ShowLogActivity {

	MySQLiteHelper dbHelper;
	SQLiteDatabase db;
	RandomValues rnd;

	@Override
	public void perform() {
		rnd = new RandomValues();

		log("open db");
		StopWatch w = new StopWatch();
		dbHelper = new MySQLiteHelper(this);
		db = dbHelper.getWritableDatabase();
		log(w.stop());
		
		log("delete all previous rows");
		w.start();
		db.execSQL("delete from demo_table");
		log(w.stop());

		log("insert 1000 rows");
		w.start();
		List<String> ids = new ArrayList<String>();
		db.beginTransaction();
		try {
		    for (int i = 0; i < 1000; i++) {
		        String id = String.valueOf(rnd.nextLong());
		        ids.add(id);
		        db.execSQL("insert into demo_table (id, name, description, peak, counting, businessKey, lastone) values ("
		                + id
		                + ",\""
		                + rnd.nextString(20)
		                + "\",\""
		                + rnd.nextString(255)
		                + "\","
		                + rnd.nextDouble()
		                + ","
		                + rnd.nextLong()
		                + ",\""
		                + rnd.nextTextWord()
		                + "\",\""
		                + rnd.nextTextWord() + "\") ");
		    }
		    db.setTransactionSuccessful();
		} finally {
		    db.endTransaction();
		}
		log(w.stop());
		
		log("perform 1000 queries");
		w.start();
		double sum = 0;
		for (int i = 0; i < 1000; i++) {
			String id = rnd.next(ids);
			Cursor cursor = db.rawQuery("select * from demo_table where id="+id, new String[] {});
			cursor.moveToFirst();
			if (!id.equals(String.valueOf(cursor.getLong(0)))) {
				log("id incorrect");
			}
			sum += cursor.getDouble(4);
			cursor.close();
		}
		if (sum==0) {
			log("sum should not be zero");
		}
		log(w.stop());
		
		db.close();
	}

	public static class MySQLiteHelper extends SQLiteOpenHelper {

		public MySQLiteHelper(Context context) {
			super(context, "demo_table", null, 1);
		}

		@Override
		public void onCreate(SQLiteDatabase database) {
			database.execSQL("create table demo_table ( id integer primary key, name text, description text, peak real, counting integer, businessKey text, lastone text)");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("drop table if exists demo_table;");
			onCreate(db);
		}

	}

}
