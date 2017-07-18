package aavn.dating.utility;

/**
 * Created by hoang_000 on 6/10/2017.
 */
public interface PasswordAuthentication {
    String hash(char[] password);

    boolean authenticate(char[] password, String token);
}
