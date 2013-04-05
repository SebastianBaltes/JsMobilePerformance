package de.objectcode.perftests.manyobjects;

import de.objectcode.perftests.utils.RandomValues;
import de.objectcode.perftests.utils.ShowLogActivity;
import de.objectcode.perftests.utils.StopWatch;

public class ManyObjectsActivity extends ShowLogActivity {

	@Override
	public void perform() {
		log("create 200 Items x 10 Parts x 10 Properties + 200 Users");
		StopWatch w = new StopWatch();
		Shop shop = new Shop();
		shop.fillDummyValues(new RandomValues());
		log(w.stop());
	}

}