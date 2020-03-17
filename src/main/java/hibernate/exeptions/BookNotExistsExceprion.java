package hibernate.exeptions;

public class BookNotExistsExceprion extends RuntimeException  {
    public BookNotExistsExceprion(String title) {
        super("book '"+ title+"' not exists");
    }
}
