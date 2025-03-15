package com.ayushsharma.journalapplication.Scheduler;

import com.ayushsharma.journalapplication.entity.JournalEntry;
import com.ayushsharma.journalapplication.entity.User;
import com.ayushsharma.journalapplication.enums.Sentiment;
import com.ayushsharma.journalapplication.repository.UserRepositoryImpl;
import com.ayushsharma.journalapplication.service.EmailService;
import com.ayushsharma.journalapplication.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;
//    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void fetchUsersAndSendSaMail(){
        List<User> users = userRepository.getUserForSA();
        for(User user : users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> filteredList = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x->x.getSentiment()).toList();

            Map<Sentiment,Integer> frequency = new HashMap<>();

            for(Sentiment sentiment : filteredList){
                if(sentiment != null){
                    frequency.put(sentiment,frequency.getOrDefault(sentiment,0) + 1);
                }
            }
            Sentiment maximum_time_occuring = null;
            int max_frequency = 0;
            for(Map.Entry<Sentiment,Integer> entry : frequency.entrySet()){
                if(entry.getValue() > max_frequency){
                    maximum_time_occuring = entry.getKey();
                    max_frequency = entry.getValue();
                }
            }
            if(maximum_time_occuring != null){
                emailService.sendEmail(user.getEmail(),"setiment for previous week" , maximum_time_occuring.toString());
            }
        }
    }
}