package hibernate.exeptions;

import java.util.function.Supplier;

public class MyAppExceptionNoAuthor extends RuntimeException implements Supplier<MyAppExceptionNoAuthor> {
    public MyAppExceptionNoAuthor() {
    }

    public MyAppExceptionNoAuthor(String message) {
        super(message);
    }

    @Override
    public MyAppExceptionNoAuthor get() {
        return new MyAppExceptionNoAuthor("no author with such name");
    }
}
