package myDiaryApp.controllers.entry;

import myDiaryApp.Dtos.request.CreateEntryRequest;
import myDiaryApp.Dtos.response.EntryResponse;
import myDiaryApp.model.Entry;
import myDiaryApp.service.entry.EntryService;
import myDiaryApp.service.entry.EntryServiceImpl;

import java.util.List;

public class EntryController {
    private final EntryService entryService = new EntryServiceImpl();


    public void deleteAllEntries(String userId) {
        entryService.deleteAllEntries(userId);
    }

    public void deleteById(String userId, int entryId) {
        entryService.deleteById(userId, entryId);
    }

    public long countEntries(String userId) {
        return entryService.countEntries(userId);
    }

    public List<EntryResponse> getAllEntriesFor(String userId) {
        return entryService.getAllEntries(userId);
    }

    public EntryResponse getEntryById(String userId, int entryId) {
        return entryService.getEntryById(userId, entryId);
    }


    public EntryResponse getEntryByTitle(CreateEntryRequest createEntryRequest) {
        return entryService.getEntryByTitle(createEntryRequest);
    }

    public boolean findExistingEntryById(String userId, int entryId) {
     return entryService.existsEntryById(userId, entryId);
    }

}
