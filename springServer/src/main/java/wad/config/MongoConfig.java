package wad.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "wad")
public class MongoConfig /*extends AbstractMongoClientConfiguration*/ {

//    @Value("${spring.data.mongodb.database}")
//    String databaseName;
//
//    @Override
//    protected String getDatabaseName() {
//        return databaseName;
//    }
//
//    /*
//     * Factory bean that creates the com.mongodb.client.MongoClient instance
//     */
//    @Bean
//    public MongoClientFactoryBean mongo(@Value("${spring.data.mongodb.host}") String host,
//                                        @Value("${spring.data.mongodb.port}") Integer port) {
//        MongoClientFactoryBean mongo = new MongoClientFactoryBean();
//        mongo.setHost(host);
//        mongo.setPort(port);
//        return mongo;
//    }
}