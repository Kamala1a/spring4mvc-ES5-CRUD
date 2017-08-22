package com.springExample.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.springExample.dao.es.ESDAO;

import org.json.JSONArray;

@Controller
@RequestMapping(value = "/api",produces = "application/json; charset=utf-8")
public class APIsController {

	@Autowired
	ESDAO esdao;
	
	@RequestMapping(value = { "/create1"}, method = { RequestMethod.GET })
	public @ResponseBody String create1(){
		
		
		/** create new user1 */
		JSONObject jsonUser = new JSONObject();
		jsonUser.put("id","1");
		jsonUser.put("user", "testUser");
		jsonUser.put("postDate", "2017-08-21");
		jsonUser.put("message", "trying out Elasticsearch");
		
		
		RestStatus status = esdao.create(jsonUser);
		JSONObject json = new JSONObject();
		json.put("status", status.getStatus());
		return json.toString();
	}
	
	
	
	@RequestMapping(value = { "/create2"}, method = { RequestMethod.GET })
	public @ResponseBody String create2(){
		
		
		/** create new user2 */
		JSONObject jsonUser = new JSONObject();
		jsonUser.put("id","2");
		jsonUser.put("user", "testUser2");
		jsonUser.put("postDate", "2017-08-21");
		jsonUser.put("message", "trying out Elasticsearch2");
		
		
		RestStatus status = esdao.create(jsonUser);
		JSONObject json = new JSONObject();
		json.put("status", status.getStatus());
		return json.toString();
	}
	
	
	@RequestMapping(value = { "/getAll"}, method = { RequestMethod.GET })
	public @ResponseBody String getAll(){
		JSONArray jsonArray = new JSONArray();
		
		
		SearchHits hits = esdao.searchAll();
		
		/** prepare result */
		for (SearchHit hit : hits) {
			JSONObject json = new JSONObject(hit.getSourceAsString());
			
			jsonArray.put(json);
			
		}
		
		
		return jsonArray.toString();
		 
		
	}
	
	
	@RequestMapping(value = { "/getUser"}, method = { RequestMethod.GET })
	public @ResponseBody String getUser(@RequestParam String name){
		
		JSONArray jsonArray = new JSONArray();
		
		/** get user by username */
		SearchHits hits = esdao.search(name);
		for (SearchHit hit : hits) {
			JSONObject json = new JSONObject(hit.getSourceAsString());
			
			jsonArray.put(json);
			
		}
		
		
		return jsonArray.toString();
		 
		
	}
	
	@RequestMapping(value = { "/getNameList"}, method = { RequestMethod.GET })
	public @ResponseBody String getNameList(){
		JSONArray jsonArray = new JSONArray();
		
		/** get user name list by aggregation */
		Terms terms = esdao.nameAgg();
		
		for (Terms.Bucket entry : terms.getBuckets()) {
			
			jsonArray.put(entry.getKey());
		  
		}

		
		
		return jsonArray.toString();
		 
		
	}
	
	@RequestMapping(value = { "/update"}, method = { RequestMethod.PUT })
	public @ResponseBody String updateUser(@RequestParam String id, @RequestParam String msg){
		
		
		RestStatus status = esdao.update(id,msg);
	
		JSONObject json = new JSONObject();
		json.put("status", status.getStatus());
		return json.toString();
		 
		
	}
	
	@RequestMapping(value = { "/delete"}, method = { RequestMethod.DELETE })
	public @ResponseBody String deleteUser(@RequestParam String id){
		
		
		RestStatus status = esdao.delete(id);
	
		JSONObject json = new JSONObject();
		json.put("status", status.getStatus());
		return json.toString();
		 
		
	}
	
}
