package com.example.jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bean.SemiProductStock;
import com.example.bean.StockSymbol;
import com.example.dao.SemiProductStockDao;

@Component
public class StockQueryUtil {
    
	@Autowired
    private SemiProductStockDao semiProductStockDao;
	
	
    public List<SemiProductStock> getStockQueryUtil() throws IOException{
    	List<StockSymbol> stockSymbols = semiProductStockDao.findAllStockSymbolFromDatabase();
    	
    	System.out.println(stockSymbols);
    	List<SemiProductStock> products = new ArrayList<>();
    	
    	for(StockSymbol stockSymbol: stockSymbols) {
    		
    		System.out.println(stockSymbol);
           
//    		String a = "0050.TW";
            String url = "https://tw.stock.yahoo.com/quote/" + stockSymbol.getStockSymbol();  // 取得的 stockSymbol
//            String url = "https://tw.stock.yahoo.com/quote/" + a;
    	
            Document doc = Jsoup.connect(url).get();
            
            Elements stockName = doc.select("div.D\\(f\\).Ai\\(c\\).Mb\\(6px\\) > h1");
            Elements stockNumber = doc.select("div.D\\(f\\).Ai\\(c\\).Mb\\(6px\\) > span");

            // 選擇具有 Fw(600) 類的前12個 span 元素
            Elements spans = doc.select("span.Fw\\(600\\)");

            List<String> spanTexts = new ArrayList<>();

            for (int i = 0; i < 12 && i < spans.size(); i++) {
                Element span = spans.get(i);
                spanTexts.add(span.text());
            }

         // 創建 SemiProductStock 對象，設置相應的屬性值
            SemiProductStock semiProduct = SemiProductStock.builder()
                    .stockName(stockName.text())
                    .stockNumber(stockNumber.text())
                    .price(spanTexts.get(0))
                    .openingPrice(spanTexts.get(1))
                    .highPrice(spanTexts.get(2))
                    .lowPrice(spanTexts.get(3))
                    .averagePrice(spanTexts.get(4))
                    .transactionAmountBillion(spanTexts.get(5))
                    .previousClosingPrice(spanTexts.get(6))
                    .changePercentage(spanTexts.get(7))
                    .changeP(spanTexts.get(8))
                    .totalVolume(spanTexts.get(9))
                    .previousVolume(spanTexts.get(10))
                    .amplitude(spanTexts.get(11))
                    .build();

            // 將 SemiProductStock 對象添加到列表中
            products.add(semiProduct);
    	}
           return products;
        
    }
}
