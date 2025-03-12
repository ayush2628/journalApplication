package com.ayushsharma.journalapplication.cache;

import com.ayushsharma.journalapplication.entity.ConfigJournalAppEntity;
import com.ayushsharma.journalapplication.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public static Map<String,String> appCache ;

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    @PostConstruct
    public void init(){
        appCache = new HashMap<>();
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
        for(ConfigJournalAppEntity curr: all){
            appCache.put(curr.getKey(), curr.getValue());
        }
    }


}
