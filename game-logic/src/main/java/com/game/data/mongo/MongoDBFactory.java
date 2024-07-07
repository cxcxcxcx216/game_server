package com.game.data.mongo;

import com.mongodb.client.ListCollectionsIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;


@Slf4j
public class MongoDBFactory {

    private static MongoClient mongoClient;

    public static void init(){
        // 设置 MongoDB 连接字符串
        String connectionString = "mongodb://localhost:27017"; // 默认连接到本地 MongoDB 实例

        // 连接到 MongoDB 服务器
        try {
            mongoClient = MongoClients.create(connectionString);
            // 连接到数据库
            MongoDatabase database = mongoClient.getDatabase("mydatabase");

            ListCollectionsIterable<Document> documents = database.listCollections();
            for (Document document : documents) {
                log.info(document.toJson());
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
