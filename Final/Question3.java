import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Question3 {
    public static void main(String[] args) {

    }

    public List<List<String>> groupAnagrams(String[] strs) {
        //TODO: Write your code here
        HashMap<String, List<String>> map = new HashMap<>();

        // scan the string array
        for (String s : strs) {
            // sort every single string in alphabet order
            String sorted = sortString(s);

            // find the sorted one in map.keySet();
            // key is the sorted one, value list is the unsorted one
            if (map.containsKey(sorted)) {
                List<String> list = map.get(sorted);
                list.add(s);
                map.put(sorted, list);
            } else {
                List<String> list = new ArrayList<>();
                list.add(s);
                map.put(sorted, list);
            }
        }

        // scan and copy the value list of map
        List<List<String>> result = new ArrayList<>();
        for (String s : map.keySet()) {
            result.add(map.get(s));
        }

        return result;
    }


    public String sortString(String inputString) {
        // convert string to char array
        char tempArray[] = inputString.toCharArray();

        // sort array
        Arrays.sort(tempArray);

        // return sorted string
        return new String(tempArray);
    }
}
