package myDiaryApp.service.entry;

import myDiaryApp.Dtos.request.CreateEntryRequest;
import myDiaryApp.Dtos.response.EntryResponse;
import myDiaryApp.Repository.entry.EntryRepository;
import myDiaryApp.Repository.entry.EntryRepositoryImpl;
import myDiaryApp.model.Entry;

import java.util.ArrayList;
import java.util.List;

public class EntryServiceImpl implements EntryService {
    private final EntryRepository entryRepository = new EntryRepositoryImpl();
    private int counter = 0;
    @Override
    public int createEntry(CreateEntryRequest createEntryRequest) {
        Entry entry = new Entry();
        entry.setUserName(createEntryRequest.getUserName());
        entry.setTitle(createEntryRequest.getTitle());
        entry.setContent(createEntryRequest.getContent());
        entry.setDate();
        entry.setId(counter+=1);
        entryRepository.save(entry);
        return entry.getId();
    }

    @Override
    public int updateEntry(String userName, String title, String content) {
        return 0;
    }

    @Override
    public void deleteAllEntries(String userName) {
        entryRepository.deleteAllById(userName);
    }

    @Override
    public void deleteById(String userName, int entryId) {
        Entry entry = entryRepository.findById(userName, entryId);
        entryRepository.delete(entry);
    }

    @Override
    public long countEntries(String userName) {
        return entryRepository.count(userName);

    }

    @Override
    public List<EntryResponse> getAllEntries(String userName) {
        List<Entry> entries = entryRepository.findAllByUserName(userName);
        List<EntryResponse> responses = new ArrayList<>();
        EntryResponse entryResponse = new EntryResponse();
        for (Entry entry : entries) {
            entryResponse.setUserName(entry.getUserName());
           entryResponse.setId(entry.getId());
           entryResponse.setTitle(entry.getTitle());
           entryResponse.setContent(entry.getContent());
           responses.add(entryResponse);
        }
        return responses;

    }

    @Override
    public EntryResponse getEntryById(String userName, int entryId) {
         Entry entry = entryRepository.findById(userName, entryId);
        return getEntryResponse(entry);
    }



    @Override
    public EntryResponse getEntryByTitle(CreateEntryRequest createEntryRequest) {
        Entry entry =  entryRepository.findByTitle(createEntryRequest.getUserName(), createEntryRequest.getTitle() );
        return getEntryResponse(entry);
    }

    @Override
    public boolean existsEntryById(String userName, int entryId) {
        return entryRepository.exists(userName, entryId);
    }



    private EntryResponse getEntryResponse(Entry entry) {
        if(entry == null) {throw new IllegalArgumentException("Entry not found");}
        else{
            EntryResponse entryResponse = new EntryResponse();
            entryResponse.setId(entry.getId());
            entryResponse.setTitle(entry.getTitle());
            entryResponse.setContent(entry.getContent());
            return entryResponse;}
    }
}
