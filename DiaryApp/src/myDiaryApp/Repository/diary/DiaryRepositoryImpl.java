package myDiaryApp.Repository.diary;

import myDiaryApp.model.Diary;

import java.util.ArrayList;
import java.util.List;

public class DiaryRepositoryImpl implements DiaryRepository {
    private List<Diary> diaryList = new ArrayList<>();

    @Override
    public long count() {
        return diaryList.size();
    }

    @Override
    public void delete(Diary diary) {
        diaryList.remove(diary);
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void deleteById(String id) {
        diaryList.remove(findById(id));
    }

    @Override
    public boolean existsById(String id) {
        Diary result = findById(id);
        return result != null;
    }

    @Override
    public Diary findById(String id) {
        for(Diary diary : diaryList) {
            if(diary.getUserName().equalsIgnoreCase(id)) return diary;
        }
        return null;
    }

    @Override
    public void save(Diary diary) {
        for (Diary value : diaryList) {
            if (value.getUserName().equalsIgnoreCase(diary.getUserName())) {
                throw new IllegalArgumentException("Diary already exists");
            }
        }
        if(diary.getUserName()!=null && diary.getPassword() != null) diaryList.add(diary);
        else throw new IllegalArgumentException("Username and password are required");
    }


}
