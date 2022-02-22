package phonebook.domain;

public class PhoneBookEntry implements Comparable<PhoneBookEntry>{

    String entry;


    public PhoneBookEntry(String entry) {
        this.entry = entry;
    }


    public String getName() {
        return entry.split(" ", 2)[1];
    }

    public String getNumber() {
        return entry.split(" ", 2)[0];
    }

    public String getEntry() {
        return entry;
    }

    @Override
    public int compareTo(PhoneBookEntry otherEntry) {
        return this.getName().compareTo(otherEntry.getName());
    }

}
