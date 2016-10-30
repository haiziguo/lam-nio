package lam.mongo.db;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
* <p>
* mongo db client
* </p>
* @author linanmiao
* @date 2016年10月28日
* @versio 1.0
*/
public class MongoDBClient {
	
	private MongoClient mongoClient;
	
	private String host;
	
	private int port;
	
	private String defaultDatabase;
	
	public MongoDBClient(String host, int port, String defaultDatabase){
		this.host = host;
		this.port = port;
		this.defaultDatabase = defaultDatabase;
	}
	
	public MongoDBClient init(){
		String uri = String.format("mongodb://%s:%d", host, port);
		
		System.out.println("uri:" + uri);
		
		MongoClientOptions.Builder build = new MongoClientOptions.Builder();
		
		// 与目标数据库能够建立的最大connection数量为50
		build.connectionsPerHost(50);
			
		// 如果当前所有的connection都在使用中，则每个connection上可以有50个线程排队等待
		build.threadsAllowedToBlockForConnectionMultiplier(50);
		
		//客户端获取数据库连接的最长等待时间为2分钟
		build.maxWaitTime(1000 * 60 * 2);
		
		//客户端与数据库建立连接的timeout设置为1分钟
		build.connectTimeout(1000 * 60 * 1);
		
		MongoClientURI mongoClinetUri = new MongoClientURI(uri, build);
		mongoClient = new MongoClient(mongoClinetUri);
		
		return this;
	}
	
	public MongoDBClient close(){
		if(mongoClient != null){
			mongoClient.close();
			System.out.println("mongoClient closed.");
		}
		return this;
	}
	
	public MongoClient getClient(){
		return mongoClient;
	}
	
	public MongoDatabase getDatabase(){
		return getClient().getDatabase(defaultDatabase);
	}
	
	public <T> MongoCollection<Document> getCollection(Class<T> clazz){
		return getDatabase().getCollection(clazz.getSimpleName().toLowerCase());
	}

}