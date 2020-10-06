package Lesson3;

public class TestPhoneBook {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook("testBook");
        phoneBook.add("Ivanov","999-515-02-47");
        phoneBook.add("Ivanov","8-880-5535-65");
        phoneBook.add("Ivanov","367-95-47");
        phoneBook.add("Petrov","245-17-05");
        phoneBook.add("Sedov","8-800-11-47");
        phoneBook.add("Sedov","8-912-513-78-46");

        phoneBook.get("Drozdov");
        phoneBook.get("Ivanov");
    }
}
