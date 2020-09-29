package Lesson3;

import java.util.*;

// Write a simple class PhoneBook, which stores a list of names and phone numbers.
// You can add entries to this phone book using the add() method.
// Use the get() method to search for a phone number by surname.
// It should be noted that under one surname there can be several phones (in the case of namesakes),
// then all phones should be displayed when requesting such a surname.

public class PhoneBook {
    private final String phoneBookName;
    private int countOfSurnames = 0;

    HashMap<String,List<String>> phoneBook = new LinkedHashMap<>();             // HashMap for store pairs of values: {surname, [phone1, phone2,...phoneN]}
    HashMap<String,Integer> phoneBookSurnamePosition = new LinkedHashMap<>();   // HashMap for store pairs of values: {surname, surname_entry_number}
    ArrayList<ArrayList<String>> phonesBase = new ArrayList<>();                // ArrayList for store Lists of phone numbers for each surname

    public PhoneBook(String phoneBookName) {
        this.phoneBookName = phoneBookName;
    }

    public void add(String surname, String phoneNumber){
        if(!phoneBook.containsKey(surname)){                            // if it is new surname for this phone book, then
            countOfSurnames += 1;                                       // increment count of elements in phone book;
            phoneBookSurnamePosition.put(surname, countOfSurnames - 1); // remember the surname_entry_number for new surname
            phonesBase.add(new ArrayList<>());                          // create new ArrayList of phones, for new surname
            phonesBase.get(countOfSurnames - 1).add(phoneNumber);       // add phone number to phoneBase arrayList for new surname
            phoneBook.put(surname,phonesBase.get(countOfSurnames-1));   // put new surname and it's phones arrayList to phoneBook hashMap
        }
        else {                                                          // if surname is already present in phoneBook
            int position = phoneBookSurnamePosition.get(surname);       // get the surname's arraylist position in phoneBase arrayList
            phonesBase.get(position).add(phoneNumber);                  // add new phone number to List of phone numbers for current surname
        }
    }

    public void get(String surname){
        if(!phoneBook.containsKey(surname)){
            System.out.println(surname + " phone book entry not found.");
        }
        else {
            Collection<String> phoneNumbers = phoneBook.get(surname);
            Iterator<String> phoneNumbersIterator = phoneNumbers.iterator();
            System.out.println("Found next phone phone book entries for surname " + surname +": ");
            while(phoneNumbersIterator.hasNext()){
                System.out.println(phoneNumbersIterator.next());
            }
        }
    }
}
