package collection;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> contact1Phones = new ArrayList<>();
        contact1Phones.add("123-456-7890");
        contact1Phones.add("123-456-7891");

        Contact contact1 = new Contact("John Doe", contact1Phones);

        List<String> contact2Phones = new ArrayList<>();
        contact2Phones.add("123-456-7880");

        Contact contact2 = new Contact("John Ive", contact2Phones);

        List<Contact> contacts = new ArrayList<>();
        contacts.add(contact1);
        contacts.add(contact2);

        Map<String, List<String>> contactMap;
        contactMap = contacts.stream().collect(Collectors.toMap(Contact::getName, Contact::getPhone));

        System.out.println(findPhoneByName(contactMap, "John"));
        System.out.println(findPhoneByName(contactMap, "John Doe"));


        // set
        Set<String> distinctName = new HashSet<>();
        distinctName.add(contact1.getName());
        distinctName.add(contact2.getName());
        distinctName.add("John Doe");

        System.out.println(distinctName);
    }

    private static List<String> findPhoneByName(Map<String, List<String>> contacts, String name) {
        return contacts.entrySet().stream().filter(entry -> entry.getKey().equalsIgnoreCase(name))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(Collections.emptyList());
    }
}
