import java.util.*;

public class Assignment2 {
    public static void main(String[] args) {

    }

    // Question 1 : Search Insert Position
    public int searchInsert(int[] nums, int target) {
        // Binary Search
        int left = 0, mid = 0, right = nums.length - 1;

        // can also use left - 1 < right and eliminate the special cases.
        while (left <= right) {
            mid = left + (right - left) / 2;

            // select mid as an exit
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    //  Question 2 : Single Element in a Sorted Array
    public int singleNonDuplicate(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            // get the half on each side of mid, mid not included
            boolean halvesAreEven = (hi - mid) % 2 == 0;

            // [mid + 1] == [mid]
            if (nums[mid + 1] == nums[mid]) {
                if (halvesAreEven) {
                    lo = mid + 2;
                } else {
                    hi = mid - 1;
                }

                // [mid] == [mid - 1]
            } else if (nums[mid - 1] == nums[mid]) {
                if (halvesAreEven) {
                    hi = mid - 2;
                } else {
                    lo = mid + 1;
                }
            } else {
                return nums[mid];
            }
        }
        return nums[lo];
    }

    //  Question 3 : Find Minimum in Rotated Sorted Array
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        int mid;

        if (nums[left] < nums[right]) {
            return nums[left];
        }

        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            if (nums[mid] > nums[left]) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return nums[left] > nums[right] ? nums[right] : nums[left];
    }

    //  Question 4 : Meeting Rooms II
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));

        for (int[] i : intervals) {
            if (pq.isEmpty() || i[0] < pq.peek()[1]) {
                pq.add(i);
            } else if (i[0] >= pq.peek()[1]) {
                pq.poll();
                pq.add(i);
            }
        }
        return pq.size();
    }

    //  Question 5 : Top K Frequent Elements
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> (a.getValue() - b.getValue()));

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.add(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        int[] result = new int[k];
        for(int i = 0; i < k; i++) {
            result[i] = pq.poll().getKey();
        }

        return result;
    }

    //  Question 6 : 3Sum Closest
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int diff = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2 && diff != 0; i++) {
            int lo = i + 1, hi = nums.length - 1;
            while (lo < hi) {
                int sum = nums[lo] + nums[hi] + nums[i];
                if (Math.abs(target - sum) < Math.abs(diff)) {
                    diff = target - sum;
                }
                if (sum > target) {
                    hi--;
                } else if (sum < target) {
                    lo++;
                } else {
                    return target;
                }
            }
        }
        return target - diff;
    }

    //  Question 7 : Insert Interval
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>();
        int i = 0;

        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            list.add(intervals[i]);
            i++;
        }

        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        list.add(newInterval);

        while (i < intervals.length) {
            list.add(intervals[i]);
            i++;
        }

        return list.toArray(new int[list.size()][]);
    }

    //  Question 8 : Non-overlapping Intervals
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 1) {
            return 0;
        }

        Arrays.sort(intervals, (a, b) -> (a[1] - b[1]));

        int index = 0, count = 1;
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= end) {
                count++;
                end = intervals[i][1];
            }

        }
        return intervals.length - count;
    }

    //  Question 9 : Interval List Intersections
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> list = new ArrayList<>();
        int i = 0, j = 0;

        while (i < firstList.length && j < secondList.length) {
            int lo = Math.max(firstList[i][0], secondList[j][0]);
            int hi = Math.min(firstList[i][1], secondList[j][1]);

            if (lo <= hi) {
                list.add(new int[]{lo, hi});
            }

            if (firstList[i][1] < secondList[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        return list.toArray(new int[list.size()][]);
    }

    //  Question 10 : 4Sum
    public List<List<Integer>> fourSum(int[] nums, int target) {

        // Sorting the array
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList();
        int n = nums.length;

        // 1st level loop
        for (int i=0; i<n-3; i++) {
            // checking duplicity
            if (i>0 && nums[i-1] == nums[i]) {
                continue;
            }

            // 2nd level loop
            for (int j = i + 1; j < n - 2; j++) {
                // checking duplicity
                if (j > i + 1 && nums[j - 1] == nums[j]) {
                    continue;
                }

                // define variable for twoSum
                int start = j + 1;
                int end = n -1;
                int temp = target - nums[i] - nums[j];

                // temp -> candidate
                while (start < end) {
                    // check if equal
                    if (temp == nums[start] + nums[end]) {
                        // adding to the result
                        result.add(Arrays.asList(nums[i], nums[j], nums[start], nums[end]));

                        // checking if any duplicates present
                        while (start < end && nums[start] == nums[start + 1]) {
                            start++;
                        }

                        while (start < end && nums[end] == nums[end - 1]) {
                            end--;
                        }

                        start++;
                        end--;
                    } else if (temp < nums[start] + nums[end]) {
                        end--;
                    } else {
                        start++;
                    }
                }
            }
        }
        return result;
    }

}
