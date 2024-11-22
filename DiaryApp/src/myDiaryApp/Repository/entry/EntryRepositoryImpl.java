package myDiaryApp.Repository.entry;

import myDiaryApp.model.Entry;

import java.util.ArrayList;
import java.util.List;

public class EntryRepositoryImpl implements EntryRepository {

    private static final List<Entry> entries = new ArrayList<>();

    @Override
    public void save(Entry entry) throws IllegalArgumentException {
        if (entry.getTitle() != null) entries.add(entry);
        else throw new IllegalArgumentException("Title is empty");
    }

    @Override
    public void delete(Entry entry) {
        entries.removeIf(entry1 -> entry1.getId() == entry.getId());
    }

    @Override
    public List<Entry> findAllByUserName(String userName) {
        List<Entry> result = new ArrayList<>();
        for (Entry entry : entries) if (entry.getUserName().equals(userName)) result.add(entry);
        return result;

    }

    @Override
    public Entry findByTitle(String userName, String title) {
        for (Entry entry : entries) {
            if (entry.getTitle().equalsIgnoreCase(title) && entry.getUserName().equals(userName)) return entry;
        }
        return null;
    }

    @Override
    public Entry findById(String userName, long id) {
        for(Entry entry: entries){
            if(entry.getUserName().equals(userName) && entry.getId() == id) return entry;
        }

        return null;
    }

    @Override
    public boolean exists(String userName, int id) {
        for (Entry entry : entries) {
            if (entry.getUserName().equals(userName) && entry.getId() == id) return true;
        }
        return false;
    }

    @Override
    public long count(String userName) {
        int count = 0;
        for(Entry entry : entries) {
            if(entry.getUserName().equals(userName)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public void deleteAllById(String userName) {
        entries.removeIf(entry -> entry.getUserName().equals(userName));

    }

    @Override
    public void deleteAll() {
        entries.clear();
    }



}
