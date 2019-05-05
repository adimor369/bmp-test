package com.adi.springresttasksbmp.beans;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("filter-counter")
public class Counter {

	private List<String> searchText;
	
	private List<Map<String, Integer>> counts;

	public List<String> getSearchText() {
		return searchText;
	}

	public void setSearchText(List<String> searchText) {
		this.searchText = searchText;
	}

	public List<Map<String, Integer>> getCounts() {
		return counts;
	}

	public void setCounts(List<Map<String, Integer>> counts) {
		this.counts = counts;
	}

	@Override
	public String toString() {
		return String.format("Counter [searchText=%s, counts=%s]", searchText, counts);
	}
	




	



	
	
}
