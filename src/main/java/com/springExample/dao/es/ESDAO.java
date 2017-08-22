package com.springExample.dao.es;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.json.JSONObject;

public class ESDAO {


	private TransportClient client;
	private String CLUSTER_NAME;
	private String INDEX;
	private String TYPE;
	
	
	
	
	public ESDAO(String HOST_IP,int HOST_PORT, String CLUSTER_NAME, String INDEX, String TYPE) {
		
		this.client = client;
		this.CLUSTER_NAME = CLUSTER_NAME;
		this.INDEX = INDEX;
		this.TYPE = TYPE;
		
		Settings settings = Settings.builder()
				.put("cluster.name", CLUSTER_NAME)
				.put("client.transport.ping_timeout","5s")
				.put("client.transport.nodes_sampler_interval","5s")
				.put("client.transport.sniff",false)
				.build();
		
		try{
			this.client = new PreBuiltTransportClient(settings)
				 .addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress(HOST_IP,HOST_PORT)));
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		
		
	}
	
	
	public RestStatus create(JSONObject json){
		
		
		IndexResponse response = this.client.prepareIndex(INDEX, TYPE).setId(json.getString("id"))
		        .setSource(json.toString())
		        .get();
		
		
		RestStatus status = response.status();

		return status;
	
	}
	
	
	public SearchHits searchAll(){
		
		QueryBuilder qb = QueryBuilders.matchAllQuery();
		SearchResponse response = client
				.prepareSearch(INDEX)
				.setTypes(TYPE)
				.setQuery(qb)
				.setFrom(0).setSize(10)
				.execute().actionGet();
		
		
	
		return response.getHits();
	
	}
	
	public SearchHits search(String userName){
		
		int from = 0;
		int size = 5;
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		SearchRequestBuilder search = client.prepareSearch(INDEX).setTypes(TYPE)
				.setQuery(bq.must(QueryBuilders.termQuery("user.keyword", userName)))
				.setFrom(from).setSize(size);

		SearchResponse response = search.execute().actionGet();
		
		return response.getHits();
	}
	
	
	
	public RestStatus update(String id, String msg){
		UpdateRequest updateRequest = new UpdateRequest();
		updateRequest.index(INDEX);
		updateRequest.type(TYPE);
		updateRequest.id(id);
		
		JSONObject json = new JSONObject();
		json.put("message", msg);
		updateRequest.doc("message", msg);
		
		
		UpdateResponse response = null;
		try {
			response = client.update(updateRequest).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response.status();
		
	}
	
	
	public RestStatus delete(String id){
		
		DeleteResponse response = client.prepareDelete(INDEX, TYPE, id).get();

		return response.status();
		
	}
	
	
	public Terms  nameAgg(){
		
		
		SearchRequestBuilder search = client.prepareSearch(INDEX).setTypes(TYPE)
				.setSize(0);
		
		TermsAggregationBuilder agg = AggregationBuilders.terms("_username").field("user.keyword");
		
		
		search.addAggregation(agg);
		
        SearchResponse response = search.execute().actionGet();

		return response.getAggregations().get("_username");
		
	}
	
	
	
	

}
