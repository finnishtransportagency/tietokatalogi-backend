package fi.liike.rest.api;

import java.util.Objects;

public class KasiteArvoContent {

	private Integer id;
	private String resourceName;
	private String value;

	public KasiteArvoContent(Integer id, String resourceName, String value) {
		this.setId(id);
		this.setResourceName(resourceName);
		this.setValue(value);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "resource name: " + resourceName + " value: " + value + " id: " + id;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof KasiteArvoContent)) {
			return false;
		}
		KasiteArvoContent other = (KasiteArvoContent)obj;
		return Objects.equals(this.getId(), other.getId())
				&& Objects.equals(this.getResourceName(), other.getResourceName())
				&& Objects.equals(this.getValue(), other.getValue());
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getId(), this.getResourceName(), this.getValue());
	}
}
