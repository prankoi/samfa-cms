package com.samfa.cms.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;

@Entity
public class Promo {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
	
  private Integer promoId;
  public String promoCode;
  public String promoType;
  public Integer discount;
	public String startDate;
  public String endDate;
  
	public Promo() {}
	
	public Promo(Integer promoId, String promoCode, String promoType, Integer discount, String startDate, String endDate) {
		this.promoId = promoId;
		this.promoCode = promoCode;
		this.promoType = promoType;
		this.discount = discount;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
  public Integer getPromoId() {
    return promoId;
  }

  public void setPromoId(Integer promoId) {
    this.promoId = promoId;
  }

  public String getPromoCode() {
    return promoCode;
  }

  public void setPromoCode(String promoCode) {
    this.promoCode = promoCode;
  }

  public String getPromoType() {
    return promoType;
  }

  public void setPromoType(String promoType) {
    this.promoType = promoType;
  }

  public Integer getDiscount() {
    return discount;
  }

  public void setDiscount(Integer discount) {
    this.discount = discount;
  }

	public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }
}
