package org.wind.k.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.wind.k.entity.mongo.UserInfo;

public interface UserMongoDao extends MongoRepository<UserInfo,String>{

}
