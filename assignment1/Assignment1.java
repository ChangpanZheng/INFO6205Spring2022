import java.util.*;

public class Assignment1 {
    public static void main(String[] args) {

    }
    // 1. Sort Colors
    public void sortColors(int[] nums) {
        int left = 0, pivot = 0;
        int right = nums.length - 1;
        while (pivot <= right) {
            if (nums[pivot] == 0) {
                swap(nums, pivot, left);
                left++;
                pivot++;
            } else if (nums[pivot] == 2) {
                swap(nums, pivot, right);
                right--;
            } else {
                pivot++;
            }
        }
    }

    private void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }

    // 2. Majority Element II
    public List<Integer> majorityElement(int[] nums) {
        int count1 = 0;
        int count2 = 0;
        Integer candidate1 = null;
        Integer candidate2 = null;

        for (int num : nums) {
            if (candidate1 != null && candidate1 == num) {
                count1 ++;
            } else if (candidate2 != null && candidate2 == null) {
                count2 ++;
            } else if (candidate1 == null || count1 == 0) {
                candidate1 = num;
                count1 ++;
            } else if (candidate2 == null || count2 == 0) {
                candidate2 = num;
                count2 ++;
            } else {
                count1 --;
                count2 --;
            }
        }

        count1 = 0;
        count2 = 0;

        List<Integer> result = new ArrayList<>();

        for (int num : nums) {
            if (num == candidate1) {
                count1 ++;
            }
            if (num == candidate2) {
                count2 ++;
            }
        }

        if (count1 > nums.length / 3) {
            result.add(candidate1);
        }
        if (count2 > nums.length / 3) {
            result.add(candidate2);
        }

        return result;
    }

    // 3. H-Index
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int i = 0;
        while (i < citations.length && citations[citations.length - 1 - i] > i) {
            i ++;
        }
        return i;
    }

    // 4. Intersection of Two Arrays
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums1) {
            set.add(num);
        }

        int[] result = new int[Math.min(nums1.length, nums2.length)];
        int k = 0;

        for (int num : nums2) {
            if (set.contains(num)) {
                result[k] = num;
                k ++;
                set.remove(num);
            }
        }

        return Arrays.copyOf(result, k);
    }

    // 5. Find K Closest Elements
    public List<Integer> findClosestElements(int[] nums, int count, int x) {
        int left = 0;
        int right = nums.length - count;
        int mid = 0;
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            if (x - nums[mid] > nums[mid + count] - x) {
                left = mid;
            } else {
                right = mid;
            }
        }

        int begin = 0;
        if (left == right) {
            begin = left;
        } else if (x - nums[left] <= nums[left + count] - x) {
            begin = left;
        } else {
            begin = right;
        }

        List<Integer> list = new ArrayList<>();
        for (int i = begin; i < begin + count; i++) {
            list.add(nums[i]);
        }

        return list;
    }

    // 6. Reorganize String
    public String reorganizeString(String s) {
        // list the null or 1 item situation
        if (s == null || s.length() == 1) {
            return s;
        }

        // new a map and the max char
        Map<Character, Integer> counts = new HashMap<>();
        char maxChar = s.charAt(0);
        int l = s.length();

        // add the char and its counts into the map
        for (char c : s.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
            if (counts.get(c) > counts.get(maxChar)) {
                maxChar = c;
            }
        }

        // define the validity
        if (counts.get(maxChar) > (l + 1) / 2) {
            return "";
        }

        int index = 0;
        char[] result = new char[l];

        // put the max char into even positions
        // attention: there may be cannot reach the end
        while (index < l && counts.get(maxChar) > 0 ){
            result[index] = maxChar;
            counts.put(maxChar, counts.get(maxChar) - 1);
            index += 2;
        }

        // add the rest to the result list
        for (Character c : counts.keySet()) {
            while (counts.get(c) > 0) {
                // return to 1 when index reaches end for the first time
                if (index >= l) {
                    index = 1;
                }
                result[index] = c;
                counts.put(c, counts.get(c) - 1);
                index += 2;
            }
        }

        return new String(result);
    }

    // 7. Custom Sort String
    public String customSortString(String order, String candidate) {
        // count each char int candidate String
        int[] counts = new int[26];
        for (char c : candidate.toCharArray()) {
            counts[c - 'a']++;
        }

        // sort chars both in two Strings
        StringBuilder sb = new StringBuilder();
        for (char c : order.toCharArray()) {
            for (int i = 0; i < counts[c - 'a']; i++) {
                sb.append(c);
            }
            counts[c - 'a'] = 0;
        }

        // group chars only in candidate string not in order string
        for (char c = 'a'; c <= 'z'; c++) {
            for (int i = 0; i < counts[c - 'a']; i++) {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    // 8. Pancake Sorting
    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> result = new ArrayList<>();
        int n = arr.length, largest = n;
        int index = 0;

        for (int i = 0; i < n; i++) {
            index = find(arr, largest);
            flip(arr, index);
            flip(arr, largest - 1);
            result.add(index + 1);
            result.add(largest);
            largest--;
        }
        return result;
    }

    private int find(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    private void flip(int[] arr, int index) {
        int i = 0, j = index;
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }

    // 9. Sort Array by Increasing Frequency
    HashMap<Integer, Integer> map = new HashMap<>();

    public int[] frequencySort(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        Collections.sort(list, new Sorter());

        for (int i = 0; i < list.size(); i++) {
            nums[i] = list.get(i);
        }

        return nums;
    }

    class Sorter implements Comparator<Integer> {
        public int compare(Integer a, Integer b) {
            if (map.get(a) == map.get(b)) {

                // think why it is b - a here
                return b - a;
            } else {

                // think why it is a - b here
                return map.get(a) - map.get(b);
            }
        }
    }

    // 10. Top K Frequent Words
    public List<String> topKFrequent(String[] words, int k) {
        List<String> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(new PQcomparator());

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.offer(entry);
        }

        int i = 0;
        while (i < k) {
            result.add(pq.poll().getKey());
            i++;
        }

        return result;
    }

    class PQcomparator implements Comparator<Map.Entry<String, Integer>>{
        public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
            if (a.getValue() == b.getValue()) {
                return a.getKey().compareTo(b.getKey());
            } else {
                return b.getValue() - a.getValue();
            }
        }
    }
}
