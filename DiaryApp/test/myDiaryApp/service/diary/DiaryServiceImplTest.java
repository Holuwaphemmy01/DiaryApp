package myDiaryApp.service.diary;

import myDiaryApp.Dtos.request.CreateDiaryRequest;
import myDiaryApp.Dtos.request.CreateEntryRequest;
import myDiaryApp.Dtos.response.DiaryLoginResponse;
import myDiaryApp.Repository.entry.EntryRepository;
import myDiaryApp.Repository.entry.EntryRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class DiaryServiceImplTest {
    private final DiaryService diaryService = new DiaryServiceImpl();

    @BeforeEach
    void setUp() {
        EntryRepository entryRepository = new EntryRepositoryImpl();
        entryRepository.deleteAll();
    }

    @Test
    public void testToRegisterUserXAndDiaryCountIsOne(){
        CreateDiaryRequest createDiaryRequest = new CreateDiaryRequest();
        createDiaryRequest.setUserName("user");
        createDiaryRequest.setPassword("correctPassword");
        diaryService.Register(createDiaryRequest);
        assertEquals(1, diaryService.diaryCount());
    }

    @Test
    public void testToRegisterUserXAndYAndDiaryCountIsTwo(){
        CreateDiaryRequest createDiaryRequest = new CreateDiaryRequest();
        createDiaryRequest.setUserName("user");
        createDiaryRequest.setPassword("correctPassword");
        diaryService.Register(createDiaryRequest);
        CreateDiaryRequest createDiaryRequest1 = new CreateDiaryRequest();
        createDiaryRequest1.setUserName("user1");
        createDiaryRequest1.setPassword("correctPassword");
        diaryService.Register(createDiaryRequest1);
        assertEquals(2, diaryService.diaryCount());
    }

    @Test
    public void testThatRegisteredUserXCanNotRegisterTwice(){
        CreateDiaryRequest createDiaryRequest = new CreateDiaryRequest();
        createDiaryRequest.setUserName("user");
        createDiaryRequest.setPassword("correctPassword");
        diaryService.Register(createDiaryRequest);
        CreateDiaryRequest createDiaryRequest1 = new CreateDiaryRequest();
        createDiaryRequest1.setUserName("user");
        createDiaryRequest1.setPassword("correctPassword");
        assertThrows(IllegalArgumentException.class, () -> diaryService.Register(createDiaryRequest1));
    }

    @Test
    public void testToRegisterUserXAndLoginSuccessfulAndDiaryIsNotLocked(){
        CreateDiaryRequest createDiaryRequest = new CreateDiaryRequest();
        createDiaryRequest.setUserName("user");
        createDiaryRequest.setPassword("correctPassword");
        diaryService.Register(createDiaryRequest);
        DiaryLoginResponse response = diaryService.login(createDiaryRequest);
        assertFalse(response.isLocked());
    }

    @Test
    public void testToRegisterUserXAndLoginUsernameIsNotCorrectAndDiaryShouldBeLocked(){
        CreateDiaryRequest createDiaryRequest = new CreateDiaryRequest();
        createDiaryRequest.setUserName("user");
        createDiaryRequest.setPassword("correctPassword");
        diaryService.Register(createDiaryRequest);
        CreateDiaryRequest loginRequest = new CreateDiaryRequest();
        loginRequest.setUserName("use");
        loginRequest.setPassword("correctPassword");
        assertThrows(IllegalArgumentException.class, () -> diaryService.login(loginRequest));
    }

    @Test
    public void testToRegisterUserXAndLoginPasswordIsNotCorrectAndDiaryShouldBeLocked(){
        CreateDiaryRequest createDiaryRequest = new CreateDiaryRequest();
        createDiaryRequest.setUserName("user");
        createDiaryRequest.setPassword("correctPassword");
        diaryService.Register(createDiaryRequest);
        createDiaryRequest.setPassword("wrongPassword");
        assertThrows(IllegalArgumentException.class, () -> diaryService.login(createDiaryRequest));

    }

    @Test
    public void testThatARegisterUserXCanCreateEntryAndEntryCountShouldBeOne(){
        CreateDiaryRequest createDiaryRequest = new CreateDiaryRequest();
        createDiaryRequest.setUserName("user");
        createDiaryRequest.setPassword("correctPassword");
        diaryService.Register(createDiaryRequest);
        DiaryLoginResponse result = diaryService.login(createDiaryRequest);
        assertFalse(result.isLocked());
        CreateEntryRequest createEntryRequest = new CreateEntryRequest();
        createEntryRequest.setUserName("user");
        createEntryRequest.setTitle("title");
        createEntryRequest.setContent("content");
        diaryService.createEntry(createEntryRequest);
        assertEquals(1, diaryService.countEntry(createEntryRequest.getUserName()));
    }

    @Test
    public void testThatRegisterUserXAndYAndTheyCanCreateEntryAndEntryCountShouldBeOneEach(){
        CreateDiaryRequest createDiaryRequest = new CreateDiaryRequest();
        createDiaryRequest.setUserName("user");
        createDiaryRequest.setPassword("correctPassword");
        diaryService.Register(createDiaryRequest);
        DiaryLoginResponse firstUser = diaryService.login(createDiaryRequest);
        CreateEntryRequest createEntryRequest = new CreateEntryRequest();
        createEntryRequest.setUserName("user");
        createEntryRequest.setTitle("title1");
        createEntryRequest.setContent("content1");
        diaryService.createEntry(createEntryRequest);
        assertEquals(1, diaryService.countEntry("user"));
        CreateDiaryRequest createDiaryRequest2 = new CreateDiaryRequest();
        createDiaryRequest2.setUserName("user2");
        createDiaryRequest2.setPassword("correctPassword2");
        diaryService.Register(createDiaryRequest2);
        diaryService.login(createDiaryRequest2);
        CreateEntryRequest createEntryRequest2 = new CreateEntryRequest();
        createEntryRequest2.setUserName("user2");
        createEntryRequest2.setTitle("title2");
        createEntryRequest2.setContent("content2");
        diaryService.createEntry(createEntryRequest2);
        assertEquals(1, diaryService.countEntry("user2"));
    }

    @Test
    public void getTestToRegisterUserXAndCreateTwoEntryAndEntryIdIsNotSame(){
        CreateDiaryRequest createDiaryRequest = new CreateDiaryRequest();
        createDiaryRequest.setUserName("user");
        createDiaryRequest.setPassword("correctPassword");
        diaryService.Register(createDiaryRequest);
        diaryService.login(createDiaryRequest);
        CreateEntryRequest createEntryRequest = new CreateEntryRequest();
        createEntryRequest.setUserName("user");
        createEntryRequest.setTitle("title");
        createEntryRequest.setContent("content");
        long idNumberOne = diaryService.createEntry(createEntryRequest);
        CreateEntryRequest createEntryRequest2 = new CreateEntryRequest();
        createEntryRequest2.setUserName("user");
        createEntryRequest2.setTitle("title2");
        createEntryRequest2.setContent("content2");
        long idNumberTwo = diaryService.createEntry(createEntryRequest2);
        assertNotEquals(idNumberOne, idNumberTwo);
    }

    @Test
    public void testToRegisterUserXAndWhenUserIsCreateEntryWhenNotLoginExceptionsThrows(){
        CreateDiaryRequest createDiaryRequest = new CreateDiaryRequest();
        createDiaryRequest.setUserName("user");
        createDiaryRequest.setPassword("password");
        diaryService.Register(createDiaryRequest);
        CreateEntryRequest createEntryRequest = new CreateEntryRequest();
        createEntryRequest.setUserName("user");
        createEntryRequest.setTitle("title");
        createEntryRequest.setContent("content");
        assertThrows(IllegalArgumentException.class, () -> diaryService.createEntry(createEntryRequest));
    }

    @Test
    public void testToRegisterUserXAndLoginOutUserX(){
        CreateDiaryRequest createDiaryRequest = new CreateDiaryRequest();
        createDiaryRequest.setUserName("user");
        createDiaryRequest.setPassword("correctPassword");
        diaryService.Register(createDiaryRequest);
        diaryService.login(createDiaryRequest);
        DiaryLoginResponse result = diaryService.login(createDiaryRequest);
        diaryService.logOut("user");
        CreateEntryRequest createEntryRequest = new CreateEntryRequest();
        createEntryRequest.setUserName("user");
        createEntryRequest.setTitle("title");
        createEntryRequest.setContent("content");
        assertThrows(IllegalArgumentException.class, () -> diaryService.createEntry(createEntryRequest));
    }

}