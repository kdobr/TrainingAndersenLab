package hibernate.exeptions;

public class AuthorNotExistsExceprion extends RuntimeException  {

    public AuthorNotExistsExceprion(String name) {
        super("author '"+name+"' not exists");
    }
}
