package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultObject {
	public String id;
	public String name;
	public String description;
	public Boolean flag;
	public Double real;
	public Map<String, String> properties = new HashMap<String, String>();
	public List<Long> values = new ArrayList<Long>();
}