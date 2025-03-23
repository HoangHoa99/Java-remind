package oop;

public class HorrorBook extends Book {

    public HorrorBook() {
        setBookCategory("Horror");
    }

    @Override
    String displayInfo() {

        return "Title: " + getTitle() + "\n" +
                "Author: " + "\n" + getAuthor().displayInfo() + "\n" +
                "Category: " + "\n" + getBookCategory() + "\n" +
                "Publication Year: " + getPublicationYear() + "\n";
    }
}

