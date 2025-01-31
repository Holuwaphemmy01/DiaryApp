package DiaryApplication.data.repositories;



import DiaryApplication.data.models.Diary;

import java.util.ArrayList;
import java.util.List;

import static java.lang.StringTemplate.STR;

public class DiaryRepositoriesImply implements DiaryRepositories {
    List <Diary> diaries =  new ArrayList<>();
    int numberOfDiaries;

    @Override
    public void saveDiary(Diary diary) {
        diaries.add(diary);
        numberOfDiaries++;
    }

    @Override
    public List<Diary> findByTitle(String title) {
        return List.of();
    }

    @Override
    public void delete(Diary diary) {
        for(int index = 0; index < diaries.size(); index++) {
            if(diaries.get(index).equals(diary)) {
                diaries.remove(diary);
            }
        }

    }

    @Override
    public int count() {
        return diaries.size();
    }


    public Diary findById(int id) {
        for(Diary diary : diaries) {
            if(diary.getDiaryId()== id) {
                return diary;
            }
        }
        return null;
    }

    @Override
    public Diary findByUserName(String userName) {
        for(Diary diary : diaries) {
            if(diary.getUsername().equals(userName)) {
                return diary;
            }
        }
        return null;
    }





}
