package Dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import Util.MongoDBUtil;
import entity.Admin;
import entity.User;
import net.sf.json.JSONObject;

public class AdminDaoImpl implements AdminDao{
		static List<User> list1 = new ArrayList<User>();
		String collectionName = "admin";// 集合名称
		String databaseName = "EMS-DB";// 数据库
	    private static String host = "127.0.0.1";// mongo数据库服务器地址,本地为127.0.0.1
	    private static int port = 27017;// mongo默认为27017端口
	    private static MongoClient mongoClient;
	    MongoCollection<Document> mongoCollection;
		public AdminDaoImpl() {
			super();
			MongoDatabase mongoDatabase = MongoDBUtil.getConnect(host,port,databaseName);
			if(null == mongoDatabase)
			{
				System.out.println("数据库不存在，请重新指定");
			}
			else {
				mongoCollection = MongoDBUtil.getConnect(host,port,databaseName).getCollection(collectionName);
			}
			//如果没有找到该集合，则进行创建
			if(null == mongoCollection)
			{
				MongoDBUtil.getConnect(host, port, databaseName).createCollection(collectionName);
				mongoCollection = MongoDBUtil.getConnect(host,port,databaseName).getCollection(collectionName);
				System.out.println("创建了集合");
			}
		}
		public Admin queryOne(String username) {
			if(null == mongoCollection)System.out.println("collection为空");
			//先获取到所有用户对象的集合，查找匹配的admin对象
	        BasicDBObject queryObject = new BasicDBObject("username", username);//查询条件
	        MongoCursor<Document> cursor = mongoCollection.find(queryObject).iterator();
	        System.out.println(cursor.hasNext());
	        try {
	        	if(cursor.hasNext())
	            {
	            	JSONObject jsonObject = JSONObject.fromObject(cursor.next().toJson().toString());//cursor的next用一次，指针就走一次，不要用来测试
	            	String user = jsonObject.getString("username");
	            	String password = jsonObject.getString("password");
	            	Admin admin = new Admin(user, password);	            	
	            	System.out.println("查询到的结果为"+admin);
	            	return admin;
	            }
			} catch (Exception e) {
				System.out.println("找不到管理员");
				return null;
			}
	        return null;
		}
		public void insertOne(Admin admin)
		{
			if(null == mongoCollection)
			{
				System.out.println("数据库连接失败");
				return;
			}
			Document document = new Document("username",admin.getUsername())
					.append("password", admin.getPassword());	
			mongoCollection.insertOne(document);
			System.out.println("数据插入成功");
		}

}
