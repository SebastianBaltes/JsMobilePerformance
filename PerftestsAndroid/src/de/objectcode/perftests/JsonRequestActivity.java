package de.objectcode.perftests;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import de.objectcode.perftests.utils.RandomValues;
import de.objectcode.perftests.utils.ShowLogActivity;
import de.objectcode.perftests.utils.StopWatch;

public class JsonRequestActivity extends ShowLogActivity {

    // TODO change this to the url of your test server (see JsonDummyServer)
	public final static String url = "http://172.22.2.50:9000";
	
	RandomValues rnd;

	@Override
	public void perform() {
	    // a real application would use AsyncTask instead
	    new Thread() {
	        @Override
	        public void run() {
	            try {
	                rnd = new RandomValues();
	                StopWatch w = new StopWatch();
	                log("perform 10 json requests with 100 objects each and parse the response");
	                for (int i = 0; i < 10; i++) {
	                    List<ResultObject> result = request(url+"/" + rnd.nextInt());
	                    log("returned " + result.size() + " objects");
	                }
	                log(w.stop());
	            } catch (Exception e) {
	                log("There is no internet connection or server at "+url+" is not online!");
	                log(e.toString());
	            }
	        }
	    }.start();
	}

	public static class ResultObject {
		String id;
		String name;
		String description;
		Boolean flag;
		Double real;
		Map<String, String> properties = new HashMap<String, String>();
		List<Long> values = new ArrayList<Long>();

		public void parse(JSONObject o) {
			id = o.optString("id");
			name = o.optString("name");
			description = o.optString("description");
			flag = o.optBoolean("flag");
			real = o.optDouble("real");
			JSONObject props = o.optJSONObject("properties");
			if (props != null) {
				for (@SuppressWarnings("unchecked")
				Iterator<String> i = props.keys(); i.hasNext();) {
					String key = i.next();
					properties.put(key, props.optString(key));
				}
			}
			JSONArray valuesArray = o.optJSONArray("values");
			if (valuesArray != null) {
				for (int i = 0; i < valuesArray.length(); i++) {
					values.add(valuesArray.optLong(i));
				}
			}
		}
	}

	public List<ResultObject> request(String url) throws Exception {
		HttpGet httpGet = new HttpGet(url);
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(httpGet);
		StatusLine statusLine = response.getStatusLine();
		int statusCode = statusLine.getStatusCode();
		if (statusCode != 200) {
		    throw new RuntimeException("statusCode" + statusCode + "!=200");
		}
		HttpEntity entity = response.getEntity();
		InputStream content = entity.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				content));
		String line;
		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}
		List<ResultObject> result = new ArrayList<ResultObject>();
		JSONArray json = new JSONArray(builder.toString());
		for (int i = 0; i < json.length(); i++) {
			JSONObject o = json.optJSONObject(i);
			ResultObject r = new ResultObject();
			r.parse(o);
			result.add(r);
		}
		return result;
	}

}
