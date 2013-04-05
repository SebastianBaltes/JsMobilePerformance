package de.objectcode.perftests.manyobjects;

import java.util.Date;

import de.objectcode.perftests.utils.RandomValues;

public class User implements Dummy {
	private Long id;
	private String name;
	private String email;
	private Date birthday;
	private Address address;
	@Override
	public void fillDummyValues(RandomValues rnd) {
		id = rnd.nextLong();
		name = rnd.nextString(40);
		email = rnd.nextString(40);
		birthday = rnd.nextDate();
		address = new Address();
		address.fillDummyValues(rnd);
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email
				+ ", birthday=" + birthday + ", address=" + address + "]";
	}
	
}
