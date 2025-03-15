package com.ayushsharma.journalapplication.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Data
public class SentimentAnalysisService {
    public String getSentiment(String text){
        return "1";
    }

}
