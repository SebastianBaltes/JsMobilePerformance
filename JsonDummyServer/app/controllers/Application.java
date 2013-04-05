package controllers;

import java.util.ArrayList;
import java.util.List;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

	public static Result index(final long seed) {
		final RandomValues rnd = new RandomValues((int) seed);
		final List<ResultObject> result = new ArrayList<>();
		while (result.size() < 100) {
			final ResultObject o = new ResultObject();
			o.id = rnd.nextString(10);
			o.name = rnd.nextString(30);
			o.description = rnd.nextString(255);
			o.flag = rnd.nextBoolean();
			o.real = rnd.nextDouble();
			while (o.properties.size() < 10) {
				o.properties.put(rnd.nextTextWord(), rnd.nextTextWord());
			}
			while (o.values.size() < 20) {
				o.values.add(rnd.nextLong());
			}
			result.add(o);
		}
		return ok(Json.toJson(result));
	}

}
