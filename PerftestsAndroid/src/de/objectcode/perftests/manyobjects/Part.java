package de.objectcode.perftests.manyobjects;

import java.util.HashMap;
import java.util.Map;


import de.objectcode.perftests.utils.RandomValues;

public class Part implements Dummy {
	private Long id;
	private String name;
	private String businessKey;
	private String description;
	private Map<String,String> properties = new HashMap <String,String>();
	
	@Override
	public void fillDummyValues(RandomValues rnd) {
		id = rnd.nextLong();
		name = rnd.nextString(20);
		businessKey = rnd.nextString(10);
		description = rnd.nextString(100);
		for (int i = 0; i < 10; i++) {
			properties.put(rnd.nextString(10), rnd.nextString(30));
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((businessKey == null) ? 0 : businessKey.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Part other = (Part) obj;
		if (businessKey == null) {
			if (other.businessKey != null)
				return false;
		} else if (!businessKey.equals(other.businessKey))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Part [id=" + id + ", name=" + name + ", businessKey="
				+ businessKey + ", description=" + description
				+ ", properties=" + properties + "]";
	}

	
}
