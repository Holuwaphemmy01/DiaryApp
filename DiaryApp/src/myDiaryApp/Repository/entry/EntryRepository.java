package myDiaryApp.Repository.entry;

import myDiaryApp.model.Entry;

import java.util.List;

public interface EntryRepository {
    void save(Entry entry) throws IllegalArgumentException;
    void delete(Entry entry);
    List<Entry> findAllByUserName(String userName);
    Entry findByTitle(String userName, String title);
    Entry findById(String userName, long id);
    boolean exists(String userName, int id);
    long count(String userName);
    void deleteAllById(String userName);
    void deleteAll();
}
