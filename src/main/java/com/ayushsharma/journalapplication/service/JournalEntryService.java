package com.ayushsharma.journalapplication.service;

import com.ayushsharma.journalapplication.entity.JournalEntry;

import com.ayushsharma.journalapplication.entity.User;
import com.ayushsharma.journalapplication.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public void saveJournalEntry(JournalEntry journalEntry, String username) {
        try {
            User user = userService.findByUsername(username);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry save = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(save);
            userService.saveExistingUserEntry(user);

        } catch (Exception e) {
            log.error("Error while saving journal entry", e);
            throw new RuntimeException(e);
        }

    }

    public void saveJournalEntry(JournalEntry journalEntry) {
            journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getJournalEntries() {
        return journalEntryRepository.findAll();
    }

    // idhar pe optional liya h dekhna ek baar
    public Optional<JournalEntry> findById(ObjectId id) {
    return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String username) {
        boolean check = false;
        try{
            User user = userService.findByUsername(username);
            check = user.getJournalEntries().removeIf(journalEntry -> journalEntry.getId().equals(id));
            if(check){
                userService.saveExistingUserEntry(user);
                journalEntryRepository.deleteById(id);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return check;

    }

}
