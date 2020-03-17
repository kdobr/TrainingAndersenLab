package hibernate.exeptions;

import java.util.function.Supplier;

public class MyAppExceptionNoBook extends RuntimeException implements Supplier<MyAppExceptionNoBook> {
    public MyAppExceptionNoBook() {
    }

    public MyAppExceptionNoBook(String message) {
        super(message);
    }

    @Override
    public MyAppExceptionNoBook get() {
        return new MyAppExceptionNoBook("no book with such title");
    }
}
