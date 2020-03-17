package hibernate.exeptions;

import java.util.function.Supplier;

public class MyAppException extends RuntimeException {
    public MyAppException() {
    }

    public MyAppException(String message) {
        super(message);
    }
}

