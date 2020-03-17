package hibernate.exeptions;

public class BookExistsExceprion extends RuntimeException  {
    public BookExistsExceprion(String title) {
        super("book '"+ title+"' already exists");
    }
}
