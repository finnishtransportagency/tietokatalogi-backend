package fi.liike.rest.api;

import java.util.List;

public class DtoResults {

	private List<ContentDto> haettavat;
	private int totalCount;

	public DtoResults(List<ContentDto> haettavat, int totalCount) {
		this.haettavat = haettavat;
		this.totalCount = totalCount;
	}

	public List<ContentDto> getHaettavat() {
		return haettavat;
	}

	public int getTotalCount() {
		return totalCount;
	}

}
