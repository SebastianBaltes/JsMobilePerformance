package de.objectcode.perftests.manyobjects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.objectcode.perftests.utils.RandomValues;

public class Item implements Dummy {
	
	private Long id;
	private String name;
	private String businessKey;
	private Date start;
	private Date end;
	private String description;
	private Double width;
	private Double height;
	private Boolean important;
	private List<Part> parts = new ArrayList<Part>();
	
	@Override
	public void fillDummyValues(RandomValues rnd) {
		id = rnd.nextLong();
		name = rnd.nextString(50);
		businessKey = rnd.nextString(10);
		start = rnd.nextDate();
		end = rnd.nextDate();
		description = rnd.nextString(500);
		width = rnd.nextDouble();
		height = rnd.nextDouble();
		important = rnd.nextBoolean();
		for (int i = 0; i < 10; i++) {
			Part part = new Part();
			part.fillDummyValues(rnd);
			parts.add(part);
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

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Boolean getImportant() {
		return important;
	}

	public void setImportant(Boolean important) {
		this.important = important;
	}

	public List<Part> getParts() {
		return parts;
	}

	public void setParts(List<Part> parts) {
		this.parts = parts;
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
		Item other = (Item) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", businessKey="
				+ businessKey + ", start=" + start + ", end=" + end
				+ ", description=" + description + ", width=" + width
				+ ", height=" + height + ", important=" + important
				+ ", parts=" + parts + "]";
	}
	
	
}
