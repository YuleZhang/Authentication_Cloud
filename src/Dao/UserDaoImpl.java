package Dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.sun.net.httpserver.Filter;

import Util.MongoDBUtil;
import entity.User;
import jdk.internal.dynalink.beans.StaticClass;
import net.sf.json.JSONObject;

public class UserDaoImpl implements UserDao{
	//创建一个集合
	static List<User> list1 = new ArrayList<User>();
	String collectionName = "user";// 集合名称
	String databaseName = "EMS-DB";// 数据库
    private static String host = "127.0.0.1";// mongo数据库服务器地址,本地为127.0.0.1
    private static int port = 27017;// mongo默认为27017端口
    private static MongoClient mongoClient;
    MongoCollection<Document> mongoCollection;
	public UserDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		mongoCollection = MongoDBUtil.getConnect(host,port,databaseName).getCollection(collectionName);
	}
	@Override
	public void insertOne(User user) {
		// TODO Auto-generated method stub
		if(null == mongoCollection)
		{
			System.out.println("数据库连接失败");
			return;
		}
		Document document = new Document("username",user.getUserName())
				.append("password", user.getPassword())
				.append("email", user.getEmail())
				.append("phone", user.getPhoneNum())
				.append("status", user.getStatus())
				.append("sourceSystem", user.getSystemSource())
				.append("registerDate", user.getRegisterDate())
				.append("finalip", user.getFinalip())
				.append("finalTime", user.getFinalTime());		
		mongoCollection.insertOne(document);
		System.out.println("数据插入成功");
	}

	@Override
	public User queryOne(String username) {
		// TODO Auto-generated method stub
		//先获取到所有用户对象的集合，查找匹配的user对象
        BasicDBObject queryObject = new BasicDBObject("username", username);
//        queryObject.put();
        MongoCursor<Document> cursor = mongoCollection.find(queryObject).iterator();
        System.out.println(cursor.hasNext());
        try {
        	if(cursor.hasNext())
            {
            	JSONObject jsonObject = JSONObject.fromObject(cursor.next().toJson().toString());//cursor的next用一次，指针就走一次，不要用来测试
            	String user = jsonObject.getString("username");
            	String password = jsonObject.getString("password");
            	String email = jsonObject.getString("email");
            	String phone = jsonObject.getString("phone");
            	String status = jsonObject.getString("status");
            	String sourceSystem = jsonObject.getString("sourceSystem");
            	String registerDate = jsonObject.getString("registerDate");
            	String finalip = jsonObject.getString("finalip");
            	String finalTime = jsonObject.getString("finalTime");
            	User u = new User(user, password, email, phone, status, sourceSystem, registerDate, finalip,finalTime);
            	System.out.println("查询到的结果为"+u);
            	return u;
            }
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("找不到该用户");
			return null;
		}
        return null;
	}
	@Override
	public List<User> searchAllUser() {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<>();
		BasicDBObject queryObject = new BasicDBObject();
		MongoCursor<Document> cursor = mongoCollection.find(queryObject).iterator();
		 try {
	        	while(cursor.hasNext())
	            {
	            	JSONObject jsonObject = JSONObject.fromObject(cursor.next().toJson().toString());//cursor的next用一次，指针就走一次，不要用来测试
	            	String user = jsonObject.getString("username");
	            	String password = jsonObject.getString("password");
	            	String email = jsonObject.getString("email");
	            	String phone = jsonObject.getString("phone");
	            	String status = jsonObject.getString("status");
	            	String sourceSystem = jsonObject.getString("sourceSystem");
	            	String registerDate = jsonObject.getString("registerDate");
	            	String finalip = jsonObject.getString("finalip");
	            	String finalTime = jsonObject.getString("finalTime");
	            	User u = new User(user, password, email, phone, status, sourceSystem, registerDate, finalip,finalTime);
	            	System.out.println(u);
	            	users.add(u);
	            }
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("查询出错啦"+e);
				return null;
			}
		return users;
	}
	@Override
	public void deleteOne(User user) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		BasicDBObject queryObject = new BasicDBObject();
		queryObject.put("username",user.getUserName());
		System.out.println("username"+user.getUserName()+"status"+user.getStatus());
		queryObject.put("status", user.getStatus());
		try
		{
			mongoCollection.deleteOne(queryObject);
			System.out.println("删除成功");
		}
		catch(Exception exception){
			System.out.println("删除失败"+exception);
		}
	}
	@Override
	public void updateTime(User user) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String curDate = sdf.format(new Date());
		Bson filter = Filters.eq("username", user.getUserName());
		Bson update = new Document("$set",new Document("finalTime",curDate));
		try {
    		mongoCollection.updateOne(filter, update);
    		System.out.println("更新成功");
		}
		catch(Exception exception)
		{
			System.out.println("更新失败"+exception);
		}
	}
	@Override
	public void updateInfo(User user) {
		// TODO Auto-generated method stub
		Bson filter = Filters.eq("username", user.getUserName());
		Bson update = new Document("$set",
				new Document("email",user.getEmail())
				.append("phone", user.getPhoneNum())
				.append("status", user.getStatus()));
		try {
    		mongoCollection.updateOne(filter, update);
    		System.out.println("更新成功");
		}
		catch(Exception exception)
		{
			System.out.println("更新失败"+exception);
		}
	}

}
