package myDiaryApp.service.diary;

import myDiaryApp.Dtos.request.CreateDiaryRequest;
import myDiaryApp.Dtos.request.CreateEntryRequest;
import myDiaryApp.Dtos.response.DiaryLoginResponse;
import myDiaryApp.Repository.diary.DiaryRepository;
import myDiaryApp.Repository.diary.DiaryRepositoryImpl;
import myDiaryApp.model.Diary;
import myDiaryApp.service.entry.EntryService;
import myDiaryApp.service.entry.EntryServiceImpl;

public class DiaryServiceImpl implements DiaryService {

    private  final DiaryRepository diaryRepository = new DiaryRepositoryImpl();
    private final EntryService entryService = new EntryServiceImpl();


    @Override
    public void Register(CreateDiaryRequest createDiaryRequest) {
        Diary diary = new Diary();
        diary.setPassword(createDiaryRequest.getPassword());
        diary.setUserName(createDiaryRequest.getUserName());
        diaryRepository.save(diary);
    }



    @Override
    public DiaryLoginResponse login(CreateDiaryRequest createDiaryRequest) {
        Diary result = diaryRepository.findById(createDiaryRequest.getUserName());
        if (result == null) throw new IllegalArgumentException("userName or password not correct");
        if(!result.getPassword().equals(createDiaryRequest.getPassword())) throw new IllegalArgumentException("Password is not correct");
        result.setLocked(false);
        DiaryLoginResponse diaryLoginResponse = new DiaryLoginResponse();
        diaryLoginResponse.setUserName(result.getUserName());
        diaryLoginResponse.setLocked(result.isLocked());
        return diaryLoginResponse;
    }

    @Override
    public long createEntry(CreateEntryRequest createEntryRequest) {
        Diary resultFound = diaryRepository.findById(createEntryRequest.getUserName());
        if(resultFound.isLocked()) throw new IllegalArgumentException("userName is locked");
        return entryService.createEntry(createEntryRequest);
    }


    @Override
    public long diaryCount() {
        return diaryRepository.count();
    }

    @Override
    public long countEntry(String user) {
        return entryService.countEntries(user);
    }

    @Override
    public void logOut(String userId) {
        Diary findUser = diaryRepository.findById(userId);
        findUser.setLocked(true);
    }
}
