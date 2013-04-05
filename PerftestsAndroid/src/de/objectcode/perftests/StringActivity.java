package de.objectcode.perftests;

import de.objectcode.perftests.utils.RandomValues;
import de.objectcode.perftests.utils.ShowLogActivity;
import de.objectcode.perftests.utils.StopWatch;

public class StringActivity extends ShowLogActivity {
	
	RandomValues rnd;
	String text;

	@Override
	public void perform() {
        rnd = new RandomValues();
		appendTest();
		splitEs();
		replaceRandomRegexpTest();
	}
	
	private void splitEs() {
		StopWatch w = new StopWatch();
		log("split 'e'");
		String[] parts = text.split("e");
		log("into "+parts.length+" parts");
		log(w.stop());
	}

	private void replaceRandomRegexpTest() {
		StopWatch w = new StopWatch();
		log("compile, search and replace 100 regexps");
		String result = text;
		for (int i = 0; i < 100; i++) {
			result = result.replaceAll("["+rnd.nextString(10).toLowerCase()+"]{1,2}[aeiou]+\\s+", rnd.nextString(10));
		}
        log(w.stop());
        checksum(result);
	}

    private void checksum(String result) {
        StopWatch w = new StopWatch();
        int check = 0;
        for (int i = 0; i < result.length(); i++) {
            check = (check + result.charAt(i)) % 10000;
        }
        log("checksum: "+check);
        log(w.stop());
    }

	private void appendTest() {
		StopWatch w = new StopWatch();
		log("appending 10000 words into a String");
		text = rnd.nextTextWithWords(10000);
        log("the string is "+text.length()+" characters long");
        checksum(text);
		log(w.stop());
	}

}
