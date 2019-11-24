
package sw.utils;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 *
 * @author Andrei Stefan
 * @since Mar 31, 2019
 */
@Service
public class StringHasher {

    public String getHash(String s) throws Exception {
        var md = MessageDigest.getInstance("SHA-256");
        md.update(s.getBytes(StandardCharsets.UTF_8));

        return new BigInteger(md.digest()).toString();
    }
}
