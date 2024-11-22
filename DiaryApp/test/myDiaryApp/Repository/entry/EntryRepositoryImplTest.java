package myDiaryApp.Repository.entry;

import myDiaryApp.model.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EntryRepositoryImplTest {
    private EntryRepository repository;

    @BeforeEach
    void setUp() {
        repository = new EntryRepositoryImpl();
        repository.deleteAll();
    }
    @Test
    void testToSaveEntryIntoTheRepositoryAndCountIsOne()   throws IllegalArgumentException{
        Entry entry = new Entry();
        entry.setTitle("title");
        entry.setContent("content");
        entry.setDate();
        entry.setUserName("user");
        repository.save(entry);
        assertEquals(1 ,repository.count("user"));
    }

    @Test
    void testToSave3EntriesIntoTheRepositoryAndCountIsThree()  throws IllegalArgumentException {
        Entry entry = new Entry();
        entry.setTitle("title1");
        entry.setContent("content");
        entry.setDate();
        entry.setUserName("user");
        repository.save(entry);
        Entry entry1 = new Entry();
        entry1.setTitle("title2");
        entry1.setContent("content");
        entry1.setDate();
        entry1.setUserName("user");
        repository.save(entry1);
        Entry entry2 = new Entry();
        entry2.setTitle("title3");
        entry2.setContent("content");
        entry2.setDate();
        entry2.setUserName("user");
        repository.save(entry2);
        assertEquals(3 ,repository.count("user"));
    }

    @Test
    public void testNotToRegisterEmptyEntry(){
        Entry entry = new Entry();
        assertThrows(IllegalArgumentException.class, () -> repository.save(entry));
    }


    @Test
    public void testToCreateEntryXAndDeleteXAndCountIsZero(){
        Entry entry = new Entry();
        entry.setTitle("title1");
        entry.setContent("content");
        entry.setDate();
        entry.setUserName("user");
        repository.save(entry);
        repository.delete(entry);
        assertEquals(0 ,repository.count("user"));
    }


    @Test
    public void testToCreateThreeEntriesAndSecondEntryByTitle(){
        Entry entry = new Entry();
        entry.setTitle("title1");
        entry.setContent("content");
        entry.setDate();
        entry.setUserName("user");
        repository.save(entry);
        Entry entry1 = new Entry();
        entry1.setTitle("title2");
        entry1.setContent("content");
        entry1.setDate();
        entry1.setUserName("user");
        repository.save(entry1);
        Entry entry2 = new Entry();
        entry2.setTitle("title3");
        entry2.setContent("content");
        entry2.setDate();
        entry2.setUserName("user");
        repository.save(entry2);
        assertEquals(3 ,repository.count("user"));
        assertEquals(entry1, repository.findByTitle("user", "title2"));
    }

    @Test
    public void testToCreateTwoEntriesAndTestShouldReturnNullWhenWrongTitleIsBeingPassed(){
        Entry entry = new Entry();
        entry.setTitle("title1");
        entry.setContent("content");
        entry.setDate();
        entry.setUserName("user");
        repository.save(entry);
        Entry entry1 = new Entry();
        entry1.setTitle("title2");
        entry1.setContent("content");
        entry1.setDate();
        entry1.setUserName("user");
        repository.save(entry1);
        assertEquals(2 ,repository.count("user"));
        assertNull(repository.findByTitle("user", "title"));
    }

    @Test
    public void testToCreateThreeEntriesAndDeleteAllByUSerIdAndCountIsZero(){
        Entry entry = new Entry();
        entry.setTitle("title1");
        entry.setContent("content");
        entry.setDate();
        entry.setUserName("user");
        repository.save(entry);
        Entry entry1 = new Entry();
        entry1.setTitle("title2");
        entry1.setContent("content");
        entry1.setDate();
        entry1.setUserName("user");
        repository.save(entry1);
        Entry entry2 = new Entry();
        entry2.setTitle("title3");
        entry2.setContent("content");
        entry2.setDate();
        entry2.setUserName("user");
        repository.save(entry2);
        assertEquals(3 ,repository.count("user"));
        repository.deleteAllById("user");
        assertEquals(0 ,repository.count("user"));
    }

    @Test
    public void testToCreateThreeEntriesAndCheckIfSecondEntryExist(){
        Entry entry = new Entry();
        entry.setTitle("title1");
        entry.setContent("content");
        entry.setDate();
        entry.setUserName("user");
        repository.save(entry);
        Entry entry1 = new Entry();
        entry1.setTitle("title2");
        entry1.setContent("content");
        entry1.setDate();
        entry1.setId(1);
        entry1.setUserName("user");
        repository.save(entry1);
        Entry entry2 = new Entry();
        entry2.setTitle("title3");
        entry2.setContent("content");
        entry2.setDate();
        entry2.setUserName("user");
        repository.save(entry2);
        assertEquals(3 ,repository.count("user"));
        assertTrue(repository.exists("user", 1));
    }

    @Test
    public void testToCreateThreeThreeEntriesAndReturnFalseIfIdPassedDoesNotExist(){
        Entry entry = new Entry();
        entry.setTitle("title1");
        entry.setContent("content");
        entry.setDate();
        entry.setUserName("user");
        repository.save(entry);
        Entry entry1 = new Entry();
        entry1.setTitle("title2");
        entry1.setContent("content");
        entry1.setDate();
        entry.setId(2);
        entry1.setUserName("user");
        repository.save(entry1);
        Entry entry2 = new Entry();
        entry2.setTitle("title3");
        entry2.setContent("content");
        entry2.setDate();
        entry2.setUserName("user");
        repository.save(entry2);
        assertEquals(3 ,repository.count("user"));
        assertFalse(repository.exists("user", 12));
    }

    @Test
    public void testToCreateThreeEntriesAndFindTheSecondEntryById(){
        Entry entry = new Entry();
        entry.setTitle("title1");
        entry.setContent("content");
        entry.setDate();
        entry.setUserName("user");
        repository.save(entry);
        Entry entry1 = new Entry();
        entry1.setTitle("title2");
        entry1.setContent("content");
        entry1.setDate();
        entry1.setId(2);
        entry1.setUserName("user");
        repository.save(entry1);
        Entry entry2 = new Entry();
        entry2.setTitle("title3");
        entry2.setContent("content");
        entry2.setDate();
        entry2.setUserName("user");
        repository.save(entry2);
        assertEquals(3 ,repository.count("user"));
        Entry result = repository.findById("user", 2);
        assertEquals(entry1.getTitle(), result.getTitle());
    }


    @Test
    public void testCreateThreeEntriesAndReturnNullWhenWrongIdIsPassedToFindEntry(){
        Entry entry = new Entry();
        entry.setTitle("title1");
        entry.setContent("content");
        entry.setDate();
        entry.setUserName("user");
        repository.save(entry);
        Entry entry1 = new Entry();
        entry1.setTitle("title2");
        entry1.setContent("content");
        entry1.setDate();
        entry1.setUserName("user");
        repository.save(entry1);
        Entry entry2 = new Entry();
        entry2.setTitle("title3");
        entry2.setContent("content");
        entry2.setDate();
        entry2.setUserName("user");
        repository.save(entry2);
        assertEquals(3 ,repository.count("user"));
        assertNull(repository.findById("user", 20));
    }

    @Test
    public void testToCreateThreeEntriesForXAndReturnAllEntriesBelongingToX(){
        Entry entry = new Entry();
        entry.setTitle("title1");
        entry.setContent("content");
        entry.setDate();
        entry.setUserName("user");
        repository.save(entry);
        Entry entry1 = new Entry();
        entry1.setTitle("title2");
        entry1.setContent("content");
        entry1.setDate();
        entry1.setUserName("user");
        repository.save(entry1);
        Entry entry2 = new Entry();
        entry2.setTitle("title3");
        entry2.setContent("content");
        entry2.setDate();
        entry2.setUserName("user");
        repository.save(entry2);
        assertEquals(3 ,repository.count("user"));
        List<Entry> result = repository.findAllByUserName("user");
        assertEquals(3, result.size());
    }

}