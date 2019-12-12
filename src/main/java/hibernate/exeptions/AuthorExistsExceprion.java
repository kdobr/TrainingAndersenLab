package hibernate.exeptions;

import java.util.function.Consumer;

public class AuthorExistsExceprion extends RuntimeException  {

    public AuthorExistsExceprion(String name) {
        super("author '"+name+"' already exists");
    }
}
