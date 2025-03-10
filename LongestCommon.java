import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

/**
 * A solution for finding the longest sequence of consecutive words
 * that remains unchanged between two versions of a document.
 */
public class LongestCommon {

    /**
     * Finds the length of the longest common consecutive sequence between two arrays of strings.
     *
     * @param version1 First array of words (first document version)
     * @param version2 Second array of words (second document version)
     * @return Length of the longest common consecutive sequence
     */
    public static int findLongestCommonSequence(String[] version1, String[] version2) {
        // Handle edge cases
        if (version1 == null || version2 == null || version1.length == 0 || version2.length == 0) {
            return 0;
        }

        int maxLength = 0;

        // For each possible starting position in the first array
        for (int i = 0; i < version1.length; i++) {
            // For each possible starting position in the second array
            for (int j = 0; j < version2.length; j++) {
                // If we find a match at these positions
                if (version1[i].equals(version2[j])) {
                    // Count how many consecutive matches we have
                    int currentLength = 0;

                    while (i + currentLength < version1.length &&
                            j + currentLength < version2.length &&
                            version1[i + currentLength].equals(version2[j + currentLength])) {
                        currentLength++;
                    }

                    // Update the maximum length if needed
                    maxLength = Math.max(maxLength, currentLength);
                }
            }
        }

        return maxLength;
    }

    public static int findLongestCommonSequenceDP(String[] version1, String[] version2) {
        if (version1 == null || version2 == null || version1.length == 0 || version2.length == 0) {
            return 0;
        }

        int[][] dp = new int[version1.length + 1][version2.length + 1];
        int maxLength = 0;

        for (int i = 1; i <= version1.length; i++) {
            for (int j = 1; j <= version2.length; j++) {
                System.out.println(version1[i - 1] + " " + version2[j - 1] + " ");
                if (version1[i-1].equals(version2[j-1])) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    maxLength = Math.max(maxLength, dp[i][j]);
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));

        return maxLength;
    }

    /**
     * Main method to test the solution with the provided examples.
     */
    public static void main(String[] args) {
        // Example 1
        String[] doc1 = {"the", "quick", "brown", "fox", "jumps"};
        String[] doc2 = {"quick", "brown", "fox", "runs", "fast"};
        Instant start = Instant.now();
        System.out.println("Example 1: " + findLongestCommonSequenceDP(doc1, doc2));
        Instant end = Instant.now();
        System.out.println("Example 1 time: " + Duration.between(start, end).toNanos());

        Instant start2 = Instant.now();
        System.out.println("Example 2: " + findLongestCommonSequence(doc1, doc2));
        Instant end2 = Instant.now();
        System.out.println("Example 2 time: " + Duration.between(start2, end2).toNanos());
        // Expected output: 3 - "quick", "brown", "fox"

        // Example 2
//        String[] doc3 = {"hello", "world", "hello", "there"};
//        String[] doc4 = {"world", "hello", "there"};
//        System.out.println("Example 2: " + findLongestCommonSequenceDP(doc3, doc4));
//        // Expected output: 2 - "hello", "there"
//
//        // Edge case - no common sequence
//        String[] doc5 = {"apple", "banana"};
//        String[] doc6 = {"orange", "grape"};
//        System.out.println("No common sequence: " + findLongestCommonSequenceDP(doc5, doc6));
//        // Expected output: 0
//
//        // Edge case - empty arrays
//        String[] empty = {};
//        System.out.println("Empty arrays: " + findLongestCommonSequenceDP(empty, doc1));
        // Expected output: 0
    }
}