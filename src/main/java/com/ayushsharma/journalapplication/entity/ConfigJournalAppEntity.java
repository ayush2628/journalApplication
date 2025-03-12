package com.ayushsharma.journalapplication.entity;


import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

// Pojo class plain old java object
@Data
@Document(collection = "config_journal_app")
public class ConfigJournalAppEntity {
    private String key;
    private String value;

}
