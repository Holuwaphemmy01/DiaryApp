package myDiaryApp.service.entry;

import myDiaryApp.Dtos.request.CreateEntryRequest;
import myDiaryApp.Dtos.response.EntryResponse;
import myDiaryApp.model.Entry;

import java.util.List;

public interface EntryService {
    int createEntry(CreateEntryRequest createEntryRequest);
    int updateEntry(String userName, String title, String content);
    void deleteAllEntries(String userName);
    void deleteById(String userName, int entryId);
    long countEntries(String userName);
    List<EntryResponse> getAllEntries(String userName);
    EntryResponse getEntryById(String userName, int entryId);
    EntryResponse getEntryByTitle(CreateEntryRequest createEntryRequest);
    boolean existsEntryById(String userName, int entryId);

}
