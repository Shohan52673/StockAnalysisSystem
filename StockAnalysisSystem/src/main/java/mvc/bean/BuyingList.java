package mvc.bean;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BuyingList {
	
	private Integer stockListId;
	
	private String stockName;
	
	private String quantity;
	
	private String price;
	
	private Integer userId; 
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
