
package wad.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 *
 * @author Andrei Stefan
 * @since Mar 31, 2019
 */
public class StringUtils {

    public static String getHash(String s) throws Exception {
        var md = MessageDigest.getInstance("SHA-256");
        md.update(s.getBytes("UTF-8"));
        return new BigInteger(md.digest()).toString();
        
    }
    
}
