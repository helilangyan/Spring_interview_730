package com.streamlinity.ct.restService.challenge;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.streamlinity.ct.model.Item;

/*
 * This controller needs to expose the following rest endpoints.  You need to fill in the implementation here
 *
 * Required REST Endpoints
 *
 *      /item                       Get all items
 *      /item?category=C            Get all items in category specified by Category shortName
 *      /item/{itemShortName}       Get item that matches the specified Item shortName
 */

@Profile("default")
@RestController
public class SearchRestControllerImpl {
	//String fileaddress = "./src/main/resources/itemPrices.json";
	@Autowired
	SearchSvcInterface searchservice;
	
	//item
	//item?category=C
	@RequestMapping(value = "/item", method = RequestMethod.GET)
	public @ResponseBody List<Item> getitemCategory(@RequestParam(required = false) String category){
		//searchservice.init(fileaddress);
		if(category == null) 
			return searchservice.getItems();
		else
			return searchservice.getItems(category);
	}
	
	@RequestMapping(value = "/item/{itemShortName}", method = RequestMethod.GET)
	public @ResponseBody List<Item> reverse_pathvariable(@PathVariable String itemShortName) {
		//searchservice.init(fileaddress);
		return searchservice.getItem(itemShortName);
	}
}
