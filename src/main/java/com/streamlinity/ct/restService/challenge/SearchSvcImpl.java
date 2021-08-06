package com.streamlinity.ct.restService.challenge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.streamlinity.ct.model.Item;

import ch.qos.logback.core.pattern.parser.Parser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.boot.jackson.JsonObjectDeserializer;

/*
 * Provide your implementation of the SearchSvcImpl here.
 * Also annotate your methods with Rest end point wrappers as required in the problem statement
 *
 * You can create any auxiliary classes or interfaces in this package if you need them.
 *
 * Do NOT add annotations as a Bean or Component or Service.   This is being handled in the custom Config class
 * PriceAdjustConfiguration
 */

public class SearchSvcImpl implements SearchSvcInterface {
	
	Item[] item_array;
	
    @Override
    public void init(String itemPriceJsonFileName) {
    	try {
			//FileReader file = new FileReader(itemPriceJsonFileName);
			ObjectMapper mapper = new ObjectMapper();
			this.item_array = mapper.readValue(new File(itemPriceJsonFileName), Item[].class);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public void init(File itemPriceJsonFile) {
    	try {
			ObjectMapper mapper = new ObjectMapper();
			this.item_array = mapper.readValue(itemPriceJsonFile, Item[].class);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public List<Item> getItems() {
    	List<Item> result = new LinkedList<>();
    	for(int i = 0; i < this.item_array.length; i++) {
    		result.add(item_array[i]);
    	}
        return result;
    }

    @Override
    public List<Item> getItems(String category) {
    	List<Item> result = new LinkedList<>();
    	for(int i = 0; i < this.item_array.length; i++) {
    		if(this.item_array[i].getCategory_short_name().equals(category)) {
    			result.add(item_array[i]);
    		}
    	}
        return result;
    }

    @Override
    public List<Item> getItem(String itemShortName) {
    	List<Item> result = new LinkedList<>();
    	for(int i = 0; i < this.item_array.length; i++) {
    		if(this.item_array[i].getShort_name().equals(itemShortName)) {
    			result.add(item_array[i]);
    		}
    	}
        return result;
    }
}
