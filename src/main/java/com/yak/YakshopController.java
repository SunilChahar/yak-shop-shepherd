package com.yak;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yak.been.Herd;
import com.yak.been.Labyak;
import com.yak.been.Stock;
import com.yak.json.HerdResponse;
import com.yak.json.LabyakResponse;
import com.yak.json.StockResponse;
import com.yak.json.request.OrderRequest;
import com.yak.json.response.MilkStockStatus;
import com.yak.json.response.SkinStockStatus;
import com.yak.json.response.StockStatus;
import com.yak.response.Product;
import com.yak.service.ReportService;

@ComponentScan
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/yak-shop")
public class YakshopController {

    @Autowired
    ReportService service;

    @RequestMapping("/stock/{days}")
    public StockResponse stock(@PathVariable(value="days") int days) {
    	StockResponse response = new StockResponse();
    	if(days > 0 && days <=1000) {
	    	Stock stock = service.calculateStock(days);
	    	for (Product product : stock.getProductList()) {
				switch (product.getName()) {
				case MILK:
					response.setMilk(product.getValue());
					break;
	
				case WOOL:
					response.setSkin(product.getValue());
					break;
				}
			}
    	}
    	return response;
    }
    
    @RequestMapping("/herd/{days}")
    public HerdResponse herd(@PathVariable(value="days") int days) {
    	HerdResponse response = new HerdResponse();
    	if(days > 0 && days <=1000) {
	    	Herd herd = service.updateHerdAge(days);
	    	LabyakResponse yakResponse;
	    	for (Labyak labyak : herd.getLabyak()) {
	    		yakResponse = new LabyakResponse();
	    		yakResponse.setName(labyak.getName());
	    		yakResponse.setAge(labyak.getAge());
	    		yakResponse.setAge_last_shaved(labyak.getAgelastshaved());
	    		response.addLabYak(yakResponse);
			}
    	}
    	return response;
    }
    
    @RequestMapping("/order/test")
    public String test() {
    	return "string";
    }
    
    @RequestMapping(value="/order/{days}", method = RequestMethod.POST, consumes = {"application/json" })
    public List<StockStatus> order(@PathVariable(value="days") int days, @RequestBody OrderRequest parameter, HttpServletResponse  response) {
    	List<StockStatus> orderResponse = new ArrayList<StockStatus>();
    	if(days > 0 && days <=1000) {
	    	if(parameter != null && parameter.getOrder() != null){
	    		float milkOrdered = parameter.getOrder().getMilk();
	    		int skinOrdered = parameter.getOrder().getSkin();
	    		if(milkOrdered > 0 || skinOrdered > 0) {
	    			Stock stock = service.calculateStock(days);
	    	    	for (Product product : stock.getProductList()) {
	    				switch (product.getName()) {
	    				case MILK:
	    					if(milkOrdered > 0 && Float.parseFloat(product.getValue()) >= milkOrdered) {
	    						orderResponse.add(new MilkStockStatus(milkOrdered));
	    						response.setStatus(201);
	    					} else if(Float.parseFloat(product.getValue()) < milkOrdered) {
	    						response.setStatus(206);
	    					}
	    					break;
	    	
	    				case WOOL:
	    					if(skinOrdered > 0 && Integer.parseInt(product.getValue()) >= skinOrdered) {
	    						orderResponse.add(new SkinStockStatus(skinOrdered));
	    						response.setStatus(201);
	    					} else if(Integer.parseInt(product.getValue()) < skinOrdered) {
	    						response.setStatus(206);
	    					}
	    					break;
	    				}
	    			}
	    		}
	    	}
	    	
    	}
    	return orderResponse;
    }
    
}
