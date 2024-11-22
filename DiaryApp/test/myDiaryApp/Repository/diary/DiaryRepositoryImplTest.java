package myDiaryApp.Repository.diary;

import myDiaryApp.model.Diary;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class DiaryRepositoryImplTest {

    private final DiaryRepository diaryRepository = new DiaryRepositoryImpl();
    private final Diary diary = new Diary();

    @Test
    public void testToCreateDiaryAndCountShouldBeOne(){
        diary.setUserName("user");
        diary.setPassword("password");
        diaryRepository.save(diary);
        assertEquals(1, diaryRepository.count());
    }

    @Test
    public void testNotToSaveEntryWhenUserNameIsNull(){
        diary.setPassword("password");
        assertThrows(IllegalArgumentException.class, () -> diaryRepository.save(diary));
    }

    @Test
    public void testNotToSaveEntryWhenPasswordIsNull(){
        diary.setUserName("user");
        assertThrows(IllegalArgumentException.class, () -> diaryRepository.save(diary));
    }

    @Test
    public void testToCreateDiaryForXAndYAndCountIsTwo(){
        diary.setUserName("user");
        diary.setPassword("password");
        diaryRepository.save(diary);
        Diary diary1 = new Diary();
        diary1.setUserName("user1");
        diary1.setPassword("password1");
        diaryRepository.save(diary1);
        assertEquals(2, diaryRepository.count());
    }

    @Test
    public void testNotToCreateUserNameWithExistingUserName(){
        diary.setUserName("user");
        diary.setPassword("password");
        diaryRepository.save(diary);
        assertEquals(1, diaryRepository.count());
        diary.setUserName("user");
        diary.setPassword("password");
        assertThrows(IllegalArgumentException.class, () -> diaryRepository.save(diary));
    }

    @Test
    public void testToCreateDiaryForXAndYAndDeleteXAndCountIsOne(){
        diary.setUserName("user");
        diary.setPassword("password");
        diaryRepository.save(diary);
        Diary diary1 = new Diary();
        diary1.setUserName("user2");
        diary1.setPassword("password2");
        diaryRepository.save(diary1);
        assertEquals(2, diaryRepository.count());
        diaryRepository.delete(diary);
        assertEquals(1, diaryRepository.count());
    }

    @Test
    public void testToCreateDiaryForXAndYAndDeleteXByIdAndCountIsOne(){
        diary.setUserName("user");
        diary.setPassword("password");
        diaryRepository.save(diary);
        Diary diary1 = new Diary();
        diary1.setUserName("user2");
        diary1.setPassword("password2");
        diaryRepository.save(diary1);
        assertEquals(2, diaryRepository.count());
        diaryRepository.deleteById("user");
        assertEquals(1, diaryRepository.count());
    }

    @Test
    public void testToCreateDiaryForXAndYAndFindIfYExist(){
        diary.setUserName("user");
        diary.setPassword("password");
        diaryRepository.save(diary);
        Diary diary1 = new Diary();
        diary1.setUserName("user2");
        diary1.setPassword("password2");
        diaryRepository.save(diary1);
        assertEquals(2, diaryRepository.count());
        assertTrue(diaryRepository.existsById("user2"));
    }

    @Test
    public void testToCreateDiaryForXAndYAndFindNonExistingValue(){
        diary.setUserName("user");
        diary.setPassword("password");
        diaryRepository.save(diary);
        Diary diary1 = new Diary();
        diary1.setUserName("user2");
        diary1.setPassword("password2");
        diaryRepository.save(diary1);
        assertEquals(2, diaryRepository.count());
        diaryRepository.deleteById("user");
        assertEquals(1, diaryRepository.count());
        assertFalse(diaryRepository.existsById("user21"));
    }



}