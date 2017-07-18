package aavn.dating.utility;

import aavn.dating.entity.User;
import aavn.dating.entity.User_Answer;
import aavn.dating.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by lnhien on 6/26/2017.
 */
@Service
public class MatchingServiceImp implements MatchingService {

    @Autowired
    private UserService userService;

    public LinkedHashMap<Long, Integer> match(User user) {
        List<User_Answer> user_answers = (List<User_Answer>) user.getList_ans();
        List<User> allUsers = (List<User>) userService.findAll();
        HashMap<Long, Integer> hashRes = new HashMap<>();

        for (User eachUser : allUsers) {
            int distance = 0;
            List<User_Answer> answers = (List<User_Answer>) eachUser.getList_ans();
            int i = 0;

            if (answers.size() > 0 && eachUser.getUserId() != user.getUserId()) {
                for (User_Answer answer : answers) {
                    distance += (int) Math.pow((answer.getAns() - user_answers.get(i).getAns()), 2);
                    i++;
                }
                int percentage = 100 - distance * 100 / (20 * 4 * 4);
                hashRes.put(eachUser.getUserId(), percentage);
            }
        }


        LinkedHashMap<Long, Integer> sortedMap = sortTopFiveHashMapByValues(hashRes);
        List<Integer> mapValues = new ArrayList<>(sortedMap.values());
        return sortedMap;
    }

    public LinkedHashMap<Long, Integer> sortTopFiveHashMapByValues(
            HashMap<Long, Integer> passedMap) {
        List<Long> mapKeys = new ArrayList<>(passedMap.keySet());
        List<Integer> mapValues = new ArrayList<>(passedMap.values());
        Collections.sort(mapValues, (o1, o2) -> {return o2 - o1;});
        System.out.println("Vy here");
        for ( Integer i : mapValues)
            System.out.println(i);
//        Collections.sort(mapKeys);

        LinkedHashMap<Long, Integer> sortedMap =
                new LinkedHashMap<>();

        Iterator<Integer> valueIt = mapValues.iterator();
        int i = 0;
        while (valueIt.hasNext()) {
            Integer val = valueIt.next();
            Iterator<Long> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                Long key = keyIt.next();
                Integer comp1 = passedMap.get(key);
                Integer comp2 = val;

                if (comp1.equals(comp2) && i < 5) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    i++;
                    break;
                }
            }
        }
        return sortedMap;
    }
}

//class ValueComparator implements Comparator {
//    Map map;
//
//    public ValueComparator(Map map) {
//        this.map = map;
//    }
//
//    public int compare(Object keyA, Object keyB) {
//        Comparable valueA = (Comparable) map.get(keyA);
//        Comparable valueB = (Comparable) map.get(keyB);
//        return valueB.compareTo(valueA);
//    }
//}
