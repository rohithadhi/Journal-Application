package com.example.journalApp.service;

import com.example.journalApp.entity.JournalEntry;
import com.example.journalApp.entity.User;
import com.example.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalRepo;
    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry entry, String userName){
        try{
            User user = userService.findByUserName(userName);
            entry.setDate(LocalDateTime.now());
            JournalEntry saved = journalRepo.save(entry);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);
        }
        catch(Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occured while saving");
        }

    }

    public void saveEntry(JournalEntry entry){
        journalRepo.save(entry);

    }

    public List<JournalEntry> getAll() {
        return journalRepo.findAll();

    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalRepo.findById(id);
    }

    public void deleteById(ObjectId id, String userName){
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        journalRepo.deleteById(id);
    }
}
