package com.samfa.cms.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;

@Entity
public class Item {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
	
  private Integer itemId;
  public String itemCategory;
  public String itemDetails;
  public Integer itemWeight;
  
	public Item() {}
	
	public Item(Integer itemId, String itemCategory, String itemDetails, Integer itemWeight) {
		this.itemId = itemId;
		this.itemCategory = itemCategory;
		this.itemDetails = itemDetails;
		this.itemWeight = itemWeight;
	}
	
  public Integer itemId() {
    return itemId;
  }

  public String getItemCategory() {
    return itemCategory;
  }

  public void setItemCategory(String itemCategory) {
    this.itemCategory = itemCategory;
  }

  public String getItemDetails() {
    return itemDetails;
  }

  public void setItemDetails(String itemDetails) {
    this.itemDetails = itemDetails;
  }

  public Integer getItemWeight() {
    return itemWeight;
  }

  public void setItemWeight(Integer itemWeight) {
    this.itemWeight = itemWeight;
  }
}
