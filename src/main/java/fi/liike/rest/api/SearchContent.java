package fi.liike.rest.api;

import java.util.*;

import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Sets;

public class SearchContent {

	private Integer size;
	private Integer offset;
	private String filter;
	private String sort;
	private Map<String, List<String>> fields = new HashMap<String, List<String>>();
	private Date modAfterDate;
	private Boolean filterDisabled;
	private Set<String> searchFields = ImmutableSet.of("nimi", "kuvaus");

	private final Set<String> allowedSorting = Sets.newHashSet("asc", "desc");

	public SearchContent(String filter, String sort) {
		this.filter = filter;
		this.sort = sort;
	}

	public SearchContent(String filter, String sort, Date modAfterDate) {
		this.filter = filter;
		this.sort = sort;
		this.modAfterDate = modAfterDate;
	}

	public SearchContent(String filter, String sort, Date modAfterDate, Boolean filterDisabled) {
		this.filter = filter;
		this.sort = sort;
		this.modAfterDate = modAfterDate;
		this.filterDisabled = filterDisabled;
	}

	public SearchContent(String filter, String sort, Boolean filterDisabled) {
		this.filter = filter;
		this.sort = sort;
		this.filterDisabled = filterDisabled;
	}

	public SearchContent(String filter, String sort, Boolean filterDisabled, Set<String> searchFields) {
		this.filter = filter;
		this.sort = sort;
		this.filterDisabled = filterDisabled;
		this.searchFields = searchFields;
	}

	public SearchContent(String filter, String sort, Set<String> searchFields) {
		this.filter = filter;
		this.sort = sort;
		this.searchFields = searchFields;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public List<String> getFilter() {
		if (filter == null)
			return Collections.emptyList();
		return Arrays.asList(filter.split(" "));
	}

	public String getSort() {
		if (StringUtils.isEmpty(sort) || !allowedSorting.contains(sort))
			return "asc";
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public void addFields(String fieldName, List<String> values) {
		this.fields.put(fieldName, values);
	}

	public Map<String, List<String>> getFields() {
		return fields;
	}

	public Date getModAfterDate() {
		return modAfterDate;
	}

	public Boolean getFilterDisabled() { return filterDisabled; }

	public Set<String> getSearchFields() {
		return searchFields;
	}

	@Override
	public String toString() {
		return "SearchContent{" +
				"size=" + size +
				", offset=" + offset +
				", filter='" + filter + '\'' +
				", sort='" + sort + '\'' +
				", fields=" + fields +
				", modAfterDate='" + modAfterDate + '\'' +
				", allowedSorting=" + allowedSorting +
				", filterDisabled=" + filterDisabled +
				'}';
	}
}
