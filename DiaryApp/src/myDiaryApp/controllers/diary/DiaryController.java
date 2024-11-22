package myDiaryApp.controllers.diary;

import myDiaryApp.Dtos.request.CreateDiaryRequest;
import myDiaryApp.Dtos.request.CreateEntryRequest;
import myDiaryApp.Dtos.response.DiaryLoginResponse;
import myDiaryApp.service.diary.DiaryService;
import myDiaryApp.service.diary.DiaryServiceImpl;

public class DiaryController {
 private final DiaryService diaryService = new DiaryServiceImpl();

    public void Register(CreateDiaryRequest createDiaryRequest) {
     diaryService.Register(createDiaryRequest);
    }

    public DiaryLoginResponse login(CreateDiaryRequest createDiaryRequest) {
     return diaryService.login(createDiaryRequest);
    }

    public long createEntry(CreateEntryRequest createEntryRequest) {
     return diaryService.createEntry(createEntryRequest);
    }
    public void logOut(String userId) {
     diaryService.logOut(userId);
    }
}
