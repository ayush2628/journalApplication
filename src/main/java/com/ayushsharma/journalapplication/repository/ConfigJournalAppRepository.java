package com.ayushsharma.journalapplication.repository;

import com.ayushsharma.journalapplication.entity.ConfigJournalAppEntity;
import com.ayushsharma.journalapplication.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId> {

}
