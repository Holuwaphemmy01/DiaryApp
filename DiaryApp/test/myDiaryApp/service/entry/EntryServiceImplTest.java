package myDiaryApp.service.entry;

import myDiaryApp.Dtos.request.CreateEntryRequest;
import myDiaryApp.Dtos.response.EntryResponse;
import myDiaryApp.Repository.entry.EntryRepository;
import myDiaryApp.Repository.entry.EntryRepositoryImpl;
import myDiaryApp.model.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class EntryServiceImplTest {
    EntryServiceImpl entryService;
    EntryRepository entryRepository;

    @BeforeEach
    void setUp() {
        entryService = new EntryServiceImpl();
        entryRepository = new EntryRepositoryImpl();
        entryRepository.deleteAll();
    }

    @Test
    void testToCreateEntryAndSaveItToTheRepositoryAndCountEntriesToBeOne() {
        CreateEntryRequest createEntryRequest = new CreateEntryRequest();
        createEntryRequest.setUserName("user");
        createEntryRequest.setTitle("title");
        createEntryRequest.setContent("content");
        entryService.createEntry(createEntryRequest);
        assertEquals(1, entryService.countEntries("user"));
    }

    @Test
    void testToCreateTwoEntriesForXAndEntriesIdIsNotSame(){
        CreateEntryRequest createEntryRequest = new CreateEntryRequest();
        createEntryRequest.setUserName("user");
        createEntryRequest.setTitle("title");
        createEntryRequest.setContent("content");
        int first = entryService.createEntry(createEntryRequest);
        CreateEntryRequest createEntryRequest2 = new CreateEntryRequest();
        createEntryRequest2.setUserName("user");
        createEntryRequest2.setTitle("title2");
        createEntryRequest2.setContent("content2");
        int second = entryService.createEntry(createEntryRequest2);
        assertNotEquals(first, second);
    }

    @Test
    void testToCreateTwoEntriesForXAndAlsoEntriesForYAndXEntriesIsTwoAndYEntriesIsAlsoTwo(){
        CreateEntryRequest createEntryRequest = new CreateEntryRequest();
        createEntryRequest.setUserName("user");
        createEntryRequest.setTitle("title");
        createEntryRequest.setContent("content");
        entryService.createEntry(createEntryRequest);
        CreateEntryRequest createEntryRequest1 = new CreateEntryRequest();
        createEntryRequest1.setUserName("user");
        createEntryRequest1.setTitle("title1");
        createEntryRequest1.setContent("content1");
        entryService.createEntry(createEntryRequest1);
        CreateEntryRequest createEntryRequest2 = new CreateEntryRequest();
        createEntryRequest2.setUserName("user1");
        createEntryRequest2.setTitle("title1");
        createEntryRequest2.setContent("content2");
        entryService.createEntry(createEntryRequest2);
        CreateEntryRequest createEntryRequest3 = new CreateEntryRequest();
        createEntryRequest3.setUserName("user1");
        createEntryRequest3.setTitle("title2");
        createEntryRequest3.setContent("content2");
        entryService.createEntry(createEntryRequest3);
        assertEquals(2, entryService.countEntries("user"));
        assertEquals(2, entryService.countEntries("user1"));
    }

    @Test
    void testToCreateTwoEntriesForXAndAlsoEntriesForYAndDeleteAllEntriesForYAndAndXEntriesIsTwo(){
        CreateEntryRequest createEntryRequest = new CreateEntryRequest();
        createEntryRequest.setUserName("user");
        createEntryRequest.setTitle("title");
        createEntryRequest.setContent("content");
        entryService.createEntry(createEntryRequest);
        CreateEntryRequest createEntryRequest1 = new CreateEntryRequest();
        createEntryRequest1.setUserName("user");
        createEntryRequest1.setTitle("title1");
        createEntryRequest1.setContent("content1");
        entryService.createEntry(createEntryRequest1);
        CreateEntryRequest createEntryRequest2 = new CreateEntryRequest();
        createEntryRequest2.setUserName("user1");
        createEntryRequest2.setTitle("title1");
        createEntryRequest2.setContent("content2");
        entryService.createEntry(createEntryRequest2);
        CreateEntryRequest createEntryRequest3 = new CreateEntryRequest();
        createEntryRequest3.setUserName("user1");
        createEntryRequest3.setTitle("title2");
        createEntryRequest3.setContent("content2");
        entryService.createEntry(createEntryRequest3);
        assertEquals(2, entryService.countEntries("user"));
        assertEquals(2, entryService.countEntries("user1"));
        entryService.deleteAllEntries("user1");
        assertEquals(0, entryService.countEntries("user1"));
        assertEquals(2, entryService.countEntries("user"));
    }

    @Test
    void testToCreateTwoEntriesForXAndDeleteSecondEntryById(){
        CreateEntryRequest createEntryRequest = new CreateEntryRequest();
        createEntryRequest.setUserName("user");
        createEntryRequest.setTitle("title");
        createEntryRequest.setContent("content");
        entryService.createEntry(createEntryRequest);
        CreateEntryRequest createEntryRequest1 = new CreateEntryRequest();
        createEntryRequest1.setUserName("user");
        createEntryRequest1.setTitle("title1");
        createEntryRequest1.setContent("content1");
        int second =entryService.createEntry(createEntryRequest1);
        entryService.deleteById("user", second);
        assertEquals(1, entryService.countEntries("user"));
    }
    @Test
    void testToCreateTwoEntriesForXAndAlsoForYAndGetAllTheEntriesBelongingToX(){
        CreateEntryRequest createEntryRequest = new CreateEntryRequest();
        createEntryRequest.setUserName("user");
        createEntryRequest.setTitle("title");
        createEntryRequest.setContent("content");
        entryService.createEntry(createEntryRequest);
        CreateEntryRequest createEntryRequest1 = new CreateEntryRequest();
        createEntryRequest1.setUserName("user");
        createEntryRequest1.setTitle("title1");
        createEntryRequest1.setContent("content1");
        entryService.createEntry(createEntryRequest1);
        CreateEntryRequest createEntryRequest2 = new CreateEntryRequest();
        createEntryRequest2.setUserName("user1");
        createEntryRequest2.setTitle("title1");
        createEntryRequest2.setContent("content2");
        entryService.createEntry(createEntryRequest2);
        CreateEntryRequest createEntryRequest3 = new CreateEntryRequest();
        createEntryRequest3.setUserName("user1");
        createEntryRequest3.setTitle("title2");
        createEntryRequest3.setContent("content2");
        entryService.createEntry(createEntryRequest3);
        assertEquals(2, entryService.countEntries("user"));
        assertEquals(2, entryService.countEntries("user1"));
        List <EntryResponse> result = entryService.getAllEntries("user");
        assertEquals(2, result.size());
    }
    @Test
    void testToCreateTwoEntriesForXUserAndCheckIfFirstEntriesExistByCheckingUsingId(){
        CreateEntryRequest createEntryRequest = new CreateEntryRequest();
        createEntryRequest.setUserName("user");
        createEntryRequest.setTitle("title");
        createEntryRequest.setContent("content");
        entryService.createEntry(createEntryRequest);
        CreateEntryRequest createEntryRequest1 = new CreateEntryRequest();
        createEntryRequest1.setUserName("user");
        createEntryRequest1.setTitle("title1");
        createEntryRequest1.setContent("content1");
        entryService.createEntry(createEntryRequest1);
        assertTrue(entryService.existsEntryById("user", 1));
    }

    @Test
    void testToCreateTwoEntriesForXUserAndCheckUsingWrongEntryIdTestShouldBeFalse(){
        CreateEntryRequest createEntryRequest = new CreateEntryRequest();
        createEntryRequest.setUserName("user");
        createEntryRequest.setTitle("title");
        createEntryRequest.setContent("content");
        entryService.createEntry(createEntryRequest);
        CreateEntryRequest createEntryRequest1 = new CreateEntryRequest();
        createEntryRequest1.setUserName("user");
        createEntryRequest1.setTitle("title1");
        createEntryRequest1.setContent("content1");
        entryService.createEntry(createEntryRequest1);
        assertFalse(entryService.existsEntryById("user", 5));
    }

    @Test
    void testToCreateTwoEntriesForXAndAlsoForYAndGetSecondEntryByIdForUserX(){
        CreateEntryRequest createEntryRequest = new CreateEntryRequest();
        createEntryRequest.setUserName("user");
        createEntryRequest.setTitle("title");
        createEntryRequest.setContent("content");
        entryService.createEntry(createEntryRequest);
        CreateEntryRequest createEntryRequest1 = new CreateEntryRequest();
        createEntryRequest1.setUserName("user");
        createEntryRequest1.setTitle("title1");
        createEntryRequest1.setContent("content1");
        entryService.createEntry(createEntryRequest1);
        CreateEntryRequest createEntryRequest2 = new CreateEntryRequest();
        createEntryRequest2.setUserName("user1");
        createEntryRequest2.setTitle("title1");
        createEntryRequest2.setContent("content2");
        entryService.createEntry(createEntryRequest2);
        CreateEntryRequest createEntryRequest3 = new CreateEntryRequest();
        createEntryRequest3.setUserName("user1");
        createEntryRequest3.setTitle("title2");
        createEntryRequest3.setContent("content2");
        entryService.createEntry(createEntryRequest3);
        assertEquals(2, entryService.countEntries("user"));
        assertEquals(2, entryService.countEntries("user1"));
        EntryResponse result = entryService.getEntryById("user", 2);
        assertEquals("title1", result.getTitle());
    }


    @Test
    void testToCreateTwoEntriesForXAndAlsoForYAndReturnNullForUserXWhenPassedWrongEntryId(){
        CreateEntryRequest createEntryRequest = new CreateEntryRequest();
        createEntryRequest.setUserName("user");
        createEntryRequest.setTitle("title");
        createEntryRequest.setContent("content");
        entryService.createEntry(createEntryRequest);
        CreateEntryRequest createEntryRequest1 = new CreateEntryRequest();
        createEntryRequest1.setUserName("user");
        createEntryRequest1.setTitle("title1");
        createEntryRequest1.setContent("content1");
        entryService.createEntry(createEntryRequest1);
        CreateEntryRequest createEntryRequest2 = new CreateEntryRequest();
        createEntryRequest2.setUserName("user1");
        createEntryRequest2.setTitle("title1");
        createEntryRequest2.setContent("content2");
        entryService.createEntry(createEntryRequest2);
        CreateEntryRequest createEntryRequest3 = new CreateEntryRequest();
        createEntryRequest3.setUserName("user1");
        createEntryRequest3.setTitle("title2");
        createEntryRequest3.setContent("content2");
        entryService.createEntry(createEntryRequest3);
        assertEquals(2, entryService.countEntries("user"));
        assertEquals(2, entryService.countEntries("user1"));
        assertThrows(IllegalArgumentException.class, () -> entryService.getEntryById("user", 12));
    }

    @Test
    void testToCreateTwoEntriesForXAndYAndGetSecondEntryByTitleForUserY(){
        CreateEntryRequest createEntryRequest = new CreateEntryRequest();
        createEntryRequest.setUserName("user");
        createEntryRequest.setTitle("title");
        createEntryRequest.setContent("content");
        entryService.createEntry(createEntryRequest);
        CreateEntryRequest createEntryRequest1 = new CreateEntryRequest();
        createEntryRequest1.setUserName("user");
        createEntryRequest1.setTitle("title1");
        createEntryRequest1.setContent("content1");
        entryService.createEntry(createEntryRequest1);
        CreateEntryRequest createEntryRequest2 = new CreateEntryRequest();
        createEntryRequest2.setUserName("user1");
        createEntryRequest2.setTitle("title1");
        createEntryRequest2.setContent("content2");
        entryService.createEntry(createEntryRequest2);
        CreateEntryRequest createEntryRequest3 = new CreateEntryRequest();
        createEntryRequest3.setUserName("user1");
        createEntryRequest3.setTitle("title2");
        createEntryRequest3.setContent("content2");
        entryService.createEntry(createEntryRequest3);
        assertEquals(2, entryService.countEntries("user"));
        assertEquals(2, entryService.countEntries("user1"));
        CreateEntryRequest createEntryRequest4 = new CreateEntryRequest();
        createEntryRequest4.setUserName("user1");
        createEntryRequest4.setTitle("title2");
        EntryResponse result = entryService.getEntryByTitle(createEntryRequest4);
        assertEquals(4, result.getId());
    }



}