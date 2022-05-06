public class Question1 {
    public static void main(String[] args) {
        System.out.println(compressString("aabcccccaaa"));
        System.out.println(compressString("ab"));
        System.out.println(compressString(""));
    }

    public static String compressString(String str) {
        //TODO: Write your code here
        if (str == null || str.length() == 0) {
            return str;
        }

        int i = 0, j = 0;
        StringBuilder sb = new StringBuilder();

        // j is the fast index
        while (j < str.length()) {
            if (str.charAt(i) != str.charAt(j)) {
                sb.append(str.charAt(i));
                sb.append(j - i);
                // i point to next end index of the same-item-substring
                i = j;
            }
            // j point to the next unexplored char in str
            j++;
        }

        // put the last same-item-substring in str put in sb
        sb.append(str.charAt(i));
        sb.append(j - i);

        if (sb.length() >= str.length()) {
            return str;
        }
        return sb.toString();
    }
}
