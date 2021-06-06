package fi.liike.rest.api;

import java.util.List;

import fi.liike.rest.Model.Haettava;

public class ModelResults {

	private List<Haettava> haettavat;
	private int totalCount;

	public ModelResults(List<Haettava> haettavat, int totalCount) {
		this.haettavat = haettavat;
		this.totalCount = totalCount;
	}

	public List<Haettava> getHaettavat() {
		return haettavat;
	}

	public int getTotalCount() {
		return totalCount;
	}

}
