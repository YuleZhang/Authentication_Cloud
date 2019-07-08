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
		String collectionName = "admin";// ��������
		String databaseName = "EMS-DB";// ���ݿ�
	    private static String host = "127.0.0.1";// mongo���ݿ��������ַ,����Ϊ127.0.0.1
	    private static int port = 27017;// mongoĬ��Ϊ27017�˿�
	    private static MongoClient mongoClient;
	    MongoCollection<Document> mongoCollection;
		public AdminDaoImpl() {
			super();
			MongoDatabase mongoDatabase = MongoDBUtil.getConnect(host,port,databaseName);
			if(null == mongoDatabase)
			{
				System.out.println("���ݿⲻ���ڣ�������ָ��");
			}
			else {
				mongoCollection = MongoDBUtil.getConnect(host,port,databaseName).getCollection(collectionName);
			}
			//���û���ҵ��ü��ϣ�����д���
			if(null == mongoCollection)
			{
				MongoDBUtil.getConnect(host, port, databaseName).createCollection(collectionName);
				mongoCollection = MongoDBUtil.getConnect(host,port,databaseName).getCollection(collectionName);
				System.out.println("�����˼���");
			}
		}
		public Admin queryOne(String username) {
			if(null == mongoCollection)System.out.println("collectionΪ��");
			//�Ȼ�ȡ�������û�����ļ��ϣ�����ƥ���admin����
	        BasicDBObject queryObject = new BasicDBObject("username", username);//��ѯ����
	        MongoCursor<Document> cursor = mongoCollection.find(queryObject).iterator();
	        System.out.println(cursor.hasNext());
	        try {
	        	if(cursor.hasNext())
	            {
	            	JSONObject jsonObject = JSONObject.fromObject(cursor.next().toJson().toString());//cursor��next��һ�Σ�ָ�����һ�Σ���Ҫ��������
	            	String user = jsonObject.getString("username");
	            	String password = jsonObject.getString("password");
	            	Admin admin = new Admin(user, password);	            	
	            	System.out.println("��ѯ���Ľ��Ϊ"+admin);
	            	return admin;
	            }
			} catch (Exception e) {
				System.out.println("�Ҳ�������Ա");
				return null;
			}
	        return null;
		}
		public void insertOne(Admin admin)
		{
			if(null == mongoCollection)
			{
				System.out.println("���ݿ�����ʧ��");
				return;
			}
			Document document = new Document("username",admin.getUsername())
					.append("password", admin.getPassword());	
			mongoCollection.insertOne(document);
			System.out.println("���ݲ���ɹ�");
		}

}
