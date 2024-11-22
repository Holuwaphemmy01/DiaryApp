package myDiaryApp.service.diary;

import myDiaryApp.Dtos.request.CreateDiaryRequest;
import myDiaryApp.Dtos.request.CreateEntryRequest;
import myDiaryApp.Dtos.response.DiaryLoginResponse;
import myDiaryApp.model.Diary;
import myDiaryApp.model.Entry;

public interface DiaryService {
    void Register(CreateDiaryRequest createDiaryRequest);
    long createEntry(CreateEntryRequest createEntryRequest);
    DiaryLoginResponse login(CreateDiaryRequest createDiaryRequest);
    long diaryCount();
    long countEntry(String user);
    void logOut(String userId);

}
