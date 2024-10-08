package com.game.data.mongo;

import com.mongodb.client.*;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.conversions.Bson;


@Slf4j
public class MongoDBFactory {

    private static MongoClient mongoClient;

    public static void init(){
        String connectionString = "mongodb://admin:123456@192.168.23.130:17017"; // 默认连接到本地 MongoDB 实例
        try {
            mongoClient = MongoClients.create(connectionString);
        } catch (Exception e) {
            log.error(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    
    public static MongoDatabase createDataBase(String name){
        // 连接到数据库
        MongoDatabase database = mongoClient.getDatabase(name);
        return database;
    }

    public static InsertOneResult insert(String database, String collection, Document document){
        MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collection);
        InsertOneResult result = mongoCollection.insertOne(document);
        return result;
    }

    public static FindIterable<Document> find(String database, String collection, Document query) {
        MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collection);
        FindIterable<Document> documents = mongoCollection.find(query);
        return documents;
    }

    public static UpdateResult  update(String database, String collection, Bson old, Bson newData){
        MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collection);
        UpdateResult result = mongoCollection.updateOne(old, newData);
        return result;
    }

    public static DeleteResult delete(String database, String collection ,Bson value){
        MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collection);
        DeleteResult result = mongoCollection.deleteOne(value);
        return result;
    }
}
