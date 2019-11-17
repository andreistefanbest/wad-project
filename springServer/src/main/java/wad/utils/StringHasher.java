
package wad.utils;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
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
        md.update(s.getBytes("UTF-8"));

        return new BigInteger(md.digest()).toString();
    }
}
