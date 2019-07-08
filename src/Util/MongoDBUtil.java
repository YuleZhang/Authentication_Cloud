package Util;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
 
//mongodb �������ݿ⹤����
public class MongoDBUtil {
    //��ͨ����֤��ȡ�������ݿ����
    public static MongoDatabase getConnect(String ip, int port, String colName){
        //���ӵ� mongodb ����
        MongoClient mongoClient = new MongoClient(ip, port);
 
        //���ӵ����ݿ�
        MongoDatabase mongoDatabase = mongoClient.getDatabase(colName);
 
        
        //�����������ݿ����
        return mongoDatabase;
    }
 
    //��Ҫ������֤��ʽ����
    public static MongoDatabase getConnect2(){
        List<ServerAddress> adds = new ArrayList<>();
        //ServerAddress()���������ֱ�Ϊ ��������ַ �� �˿�
        ServerAddress serverAddress = new ServerAddress("localhost", 27017);
        adds.add(serverAddress);
        
        List<MongoCredential> credentials = new ArrayList<>();
        //MongoCredential.createScramSha1Credential()���������ֱ�Ϊ �û��� ���ݿ����� ����
        MongoCredential mongoCredential = MongoCredential.createScramSha1Credential("username", "databaseName", "password".toCharArray());
        credentials.add(mongoCredential);
        
        //ͨ��������֤��ȡMongoDB����
        MongoClient mongoClient = new MongoClient(adds, credentials);
 
        //���ӵ����ݿ�
        MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
 
        //�����������ݿ����
        return mongoDatabase;
    }
}