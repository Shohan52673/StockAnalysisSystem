package com.example.bean;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Watchlist {
	
	
private String stockName;
	
	private String stockNumber;
	
	private String price;
	
	private String openingPrice;
	
	private String highPrice;
	
	private String lowPrice;
	
	private String averagePrice;
	
	private String transactionAmountBillion;
	
	private String previousClosingPrice;
	
	private String changePercentage;
	
	private String changeP;
	
	private String totalVolume;
	
	private String previousVolume;
	
	private String amplitude;
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
