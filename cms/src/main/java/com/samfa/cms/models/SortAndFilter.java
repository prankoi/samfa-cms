package com.samfa.cms.models;

public class SortAndFilter {
	public String sorting;
	public String filter;
	
	public SortAndFilter() {}
	
	public SortAndFilter(String sorting, String filter) {
		this.sorting = sorting;
		this.filter = filter;
	}
	
	public String getSorting() {
		return sorting;
	}
	
	public void setSorting(String sorting) {
		this.sorting = sorting;
	}
	
	public String getFilter() {
		return filter;
	}
	
	public void setFilter(String filter) {
		this.filter = filter;	
	}
}