package oop;

public class Library {

    public static void main(String[] args) {
        Author author = new Author();
        author.setName("Stephen King");
        author.setGender("male");
        author.setNationality("American");
        author.setYearOfBirth(1947);

        Book it = new HorrorBook();
        it.setTitle("IT");
        it.setAuthor(author);
        it.setPublicationYear(1986);

        System.out.println(it.displayInfo());
    }
}
