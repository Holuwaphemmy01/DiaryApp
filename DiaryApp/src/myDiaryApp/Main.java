package myDiaryApp;

import myDiaryApp.Dtos.request.CreateDiaryRequest;
import myDiaryApp.Dtos.request.CreateEntryRequest;
import myDiaryApp.Dtos.response.EntryResponse;
import myDiaryApp.controllers.diary.DiaryController;
import myDiaryApp.controllers.entry.EntryController;

import java.util.List;
import java.util.Scanner;

public class Main {
    private final static DiaryController diaryController = new DiaryController();
    private final static EntryController entryController = new EntryController();
    private final static CreateDiaryRequest createDiaryRequest = new CreateDiaryRequest();
    private final static CreateEntryRequest createEntryRequest = new CreateEntryRequest();




    public static void main(String[] args) {
        goToMainMenu();
    }

    private static void goToMainMenu() {
    String welcome= """
                Welcome to My Diary App!
                1. Create a new diary
                2. Login.
                3. Exist
                """;
        int option = 0;
    try {
         option = Integer.parseInt(input(welcome));
    }
    catch (Exception e) {
        print("Error: "+e.getMessage());
    }

    switch (option) {
        case 1: createDiary();
        case 2: login();
        case 3: exist();
        default: goToMainMenu();
        }
    }

    private static void exist() {
        print("Thank you for using My Diary App!");

    }

    private static void createDiary() {
       createDiaryRequest.setUserName(input("Create yah user name"));
       createDiaryRequest.setPassword(input("Create yah  password"));
       try {
           diaryController.Register(createDiaryRequest);
           print("Registration successful!");
       }
       catch (Exception e) {
           print("Error: " + e.getMessage());
       }

       goToMainMenu();
    }

    private static void login() {
        createDiaryRequest.setUserName(input("Enter your user name"));
        createDiaryRequest.setPassword(input("Enter your password"));
        try {
            diaryController.login(createDiaryRequest);
            print("Login successful!");
            insideLogin(createDiaryRequest.getUserName());
        }
        catch (Exception e) {
            print("Error: " + e.getMessage());
        }
        goToMainMenu();

    }

    private static void insideLogin(String currentUser) {
        String message = """ 
                1. Create a new entry
                2. Find entry by id
                3. findEntryByTitle
                4. Find existing entry by id
                5. Count all Entries
                6. Get all entries
                7. Delete entry by id
                8. Delete All entries.
                9. Log out
                """;

            int option = 0;
        try {
             option = Integer.parseInt(input("welcome "+ currentUser + "\n" +message));
        }
        catch (Exception e) {
            print("Kindly enter the instruction below");
            insideLogin(currentUser);
        }

        switch (option) {
            case 1: createEntry(currentUser);
            case 2: findEntryById(currentUser);
            case 3: findEntryByTitle(currentUser);
            case 4: existingEntry(currentUser);
            case 5: countAllEntries(currentUser);
            case 6: getAllEntries(currentUser);
            case 7: deleteEntry(currentUser);
            case 8: deleteAllEntries(currentUser);
            case 9: logOut(currentUser);
            default: insideLogin(currentUser);

        }
    }

    private static void getAllEntries(String currentUser) {
        List<EntryResponse> entry = entryController.getAllEntriesFor(currentUser);
        if (entry.isEmpty()) {
            print("No entries found");
        }
        for(EntryResponse entryResponse : entry) {
            print(entryResponse.toString());
        }
        insideLogin(currentUser);
    }

    private static void countAllEntries(String currentUser) {
            print("Your total number of entries is "+entryController.countEntries(currentUser));
            insideLogin(currentUser);
    }

    private static void deleteAllEntries(String currentUser) {
       String deleteAll =  input("Enter yes to continue or any key to exist");
       if(deleteAll.equalsIgnoreCase("yes")) {
           entryController.deleteAllEntries(currentUser);
           print("All entries deleted!");
       }
        insideLogin(currentUser);

    }

    private static void deleteEntry(String currentUser) {
        entryController.deleteById(currentUser, Integer.parseInt(input("Enter your entry id")));
        insideLogin(currentUser);
    }

    private static void logOut(String currentUser) {
        diaryController.logOut(currentUser);
        print("Log out successful!\nThanks for using My Diary App!");
        goToMainMenu();
    }

    private static void existingEntry(String currentUser) {
        try {
            boolean result = entryController.findExistingEntryById(currentUser, Integer.parseInt(input("Enter your entry id")));
            if (result) System.out.println("Entry exist");
            else System.out.println("Entry does not exist");
        }
        catch (Exception e) {
            print("Kindly input entry id");
        }
        insideLogin(currentUser);


    }

    private static void createEntry(String userName) {
        CreateEntryRequest createEntryRequest = new CreateEntryRequest();
        createEntryRequest.setUserName(userName);
        createEntryRequest.setTitle(input("Enter your title"));
        createEntryRequest.setContent(input("Enter your content"));
        print("This is your entry id "+ diaryController.createEntry(createEntryRequest));
        insideLogin(userName);
    }

    private static void findEntryById(String userName) {
        int userEntryId = Integer.parseInt(input(userName +" enter yah Entry id\n"));
        try {
            EntryResponse response = entryController.getEntryById(userName, userEntryId);
            print(response.toString());

        }
        catch (Exception e) {
            print("Error: " + e.getMessage());
            insideLogin(userName);
        }

        insideLogin(userName);
    }

    private static void findEntryByTitle(String userName) {
        createEntryRequest.setUserName(userName);
        createEntryRequest.setTitle(input("Enter yah entry title"));
        try {
            EntryResponse response = entryController.getEntryByTitle(createEntryRequest);
            print(response.toString());
            insideLogin(userName);
        }
        catch (Exception e) {
            print("Error: " + e.getMessage());
        }
        insideLogin(userName);
    }


    private static void print(String message) {
        System.out.println(message);
    }

    private static String input (String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextLine();
    }




}
