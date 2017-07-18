package aavn.dating.utility;

import aavn.dating.entity.User;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by lnhien on 6/26/2017.
 */
public interface MatchingService {
    LinkedHashMap<Long, Integer> match(User user);
}
