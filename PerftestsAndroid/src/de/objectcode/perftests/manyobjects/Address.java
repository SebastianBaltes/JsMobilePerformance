package de.objectcode.perftests.manyobjects;

import java.util.Arrays;

import de.objectcode.perftests.utils.RandomValues;

public class Address implements Dummy {
	private String[] line = new String[3];
	private String city;
	private String plz;
	private String country;
	@Override
	public void fillDummyValues(RandomValues rnd) {
		for (int i = 0; i < line.length; i++) {
			line[i] = rnd.nextString(60);
		}
		city = rnd.nextString(10);
		plz = rnd.nextString(5);
		country = rnd.nextString(10);
	}
	public String[] getLine() {
		return line;
	}
	public void setLine(String[] line) {
		this.line = line;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPlz() {
		return plz;
	}
	public void setPlz(String plz) {
		this.plz = plz;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + Arrays.hashCode(line);
		result = prime * result + ((plz == null) ? 0 : plz.hashCode());
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
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (!Arrays.equals(line, other.line))
			return false;
		if (plz == null) {
			if (other.plz != null)
				return false;
		} else if (!plz.equals(other.plz))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Address [line=" + Arrays.toString(line) + ", city=" + city
				+ ", plz=" + plz + ", country=" + country + "]";
	}
	
}
