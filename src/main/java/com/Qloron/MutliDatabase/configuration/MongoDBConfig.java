package com.Qloron.MutliDatabase.configuration;

import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.Qloron.MutliDatabase.repository")
public class MongoDBConfig {

    @Bean
    public MongoTemplate mongoTemplate(){
        return new MongoTemplate(MongoClients.create("mongodb://localhost:27017"), "OrderDB");
    }
}
