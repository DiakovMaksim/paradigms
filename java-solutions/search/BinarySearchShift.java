package search;

public class BinarySearchShift {
    // Pred: args.length > 0 && (foreach s : args) {s - > int} &&
    // (foreach i in 1..k-1) {Integer.parseInt(args[i]) < Integer.parseInt(args[i-1])} &&
    // (foreach i in k+1..args.length-1) {Integer.parseInt(args[i]) < Integer.parseInt(args[i-1])} &&
    // (k > 0) <=> (Integer.parseInt(args[0]) < Integer.parseInt(args[args.length - 1)
    // Post: R == k
    public static void main(String[] args) {
        // args.length > 0 && (foreach s : args) {s - > int} &&
        // (foreach i in 1..k-1) {Integer.parseInt(args[i]) < Integer.parseInt(args[i-1])} &&
        // (foreach i in k+1..args.length-1) {Integer.parseInt(args[i]) < Integer.parseInt(args[i-1])} &&
        // (k > 0) <=> (Integer.parseInt(args[0]) < Integer.parseInt(args[args.length - 1)
        int s = 0;
        // args.length > 0 && (foreach s : args) {s - > int} && x = Integer.parseInt(args[0]) &&
        // (foreach i in 1..k-1) {Integer.parseInt(args[i]) < Integer.parseInt(args[i-1])} &&
        // (foreach i in k+1..args.length-1) {Integer.parseInt(args[i]) < Integer.parseInt(args[i-1])} &&
        // (k > 0) <=> (Integer.parseInt(args[0]) < Integer.parseInt(args[args.length - 1)
        int[] a = new int[args.length];
        // args.length > 0 && (foreach s : args) {s - > int} && x = Integer.parseInt(args[0]) &&
        // (foreach i in 1..k-1) {Integer.parseInt(args[i]) < Integer.parseInt(args[i-1])} &&
        // (foreach i in k+1..args.length-1) {Integer.parseInt(args[i]) < Integer.parseInt(args[i-1])} &&
        // (k > 0) <=> (Integer.parseInt(args[0]) < Integer.parseInt(args[args.length - 1)
        int i = 0;
        // args.length > 0 && (foreach s : args) {s - > int} && x = Integer.parseInt(args[0]) &&
        // (foreach i in 1..k-1) {Integer.parseInt(args[i]) < Integer.parseInt(args[i-1])} &&
        // (foreach i in k+1..args.length-1) {Integer.parseInt(args[i]) < Integer.parseInt(args[i-1])} &&
        // (k > 0) <=> (Integer.parseInt(args[0]) < Integer.parseInt(args[args.length - 1) &&
        // (foreach i in 1..i) {a[i-1] = Integer.parseInt(args[i-1])}
        while (i < args.length) {
            a[i] = Integer.parseInt(args[i]);
            s += a[i];
            // args.length > 0 && (foreach s : args) {s - > int} && x = Integer.parseInt(args[0]) &&
            // (foreach i in 1..i+1) {a[i-1] = Integer.parseInt(args[i-1]) &&
            // (foreach i in 1..k-1) {Integer.parseInt(args[i]) < Integer.parseInt(args[i-1])} &&
            // (foreach i in k+1..args.length-1) {Integer.parseInt(args[i]) < Integer.parseInt(args[i-1])} &&
            // (k > 0) <=> (Integer.parseInt(args[0]) < Integer.parseInt(args[args.length - 1)
            i++;
            // args.length > 0 && (foreach s : args) {s - > int} && x = Integer.parseInt(args[0]) &&
            // (foreach i in 1..i) {a[i-1] = Integer.parseInt(args[i-1]) &&
            // (foreach i in 1..k-1) {Integer.parseInt(args[i]) < Integer.parseInt(args[i-1])} &&
            // (foreach i in k+1..args.length-1) {Integer.parseInt(args[i]) < Integer.parseInt(args[i-1])} &&
            // (k > 0) <=> (Integer.parseInt(args[0]) < Integer.parseInt(args[args.length - 1)
        }
        // args.length > 0 && (foreach s : args) {s - > int} && x = Integer.parseInt(args[0]) &&
        // (foreach i in 1..args.length) {a[i-1] = Integer.parseInt(args[i-1]) &&
        // (foreach i in 1..k-1) {a[i] < a[i-1]} &&
        // (foreach i in k+1..a.length-1) {a[i] < a[i-1]} &&
        // (k > 0) <=> (a[0] < a[a.length - 1])
        int R;
        // args.length > 0 && (foreach s : args) {s - > int} && x = Integer.parseInt(args[0]) &&
        // (foreach i in 1..args.length) {a[i-1] = Integer.parseInt(args[i-1]) &&
        // (foreach i in 1..k-1) {a[i] < a[i-1]} &&
        // (foreach i in k+1..a.length-1) {a[i] < a[i-1]} &&
        // (k > 0) <=> (a[0] < a[a.length - 1])
        if (a.length < 2 || a[0] > a[a.length - 1]) {
            // args.length < 2 || k == 0
            R = 0;
            // R == k
        } else {
            // args.length > 0 && (foreach s : args) {s - > int} && x = Integer.parseInt(args[0]) &&
            // (foreach i in 1..args.length) {a[i-1] = Integer.parseInt(args[i-1]) &&
            // (foreach i in 1..k-1) {a[i] < a[i-1]} &&
            // (foreach i in k+1..a.length-1) {a[i] < a[i-1]} && args.length > 1 && a.length > 0 && k > 0 && k < a.length
            if (s % 2 == 0) {
                // args.length > 0 && (foreach s : args) {s - > int} && x = Integer.parseInt(args[0]) &&
                // (foreach i in 1..args.length) {a[i-1] = Integer.parseInt(args[i-1]) &&
                // (foreach i in 1..k-1) {a[i] < a[i-1]} &&
                // (foreach i in k+1..a.length-1) {a[i] < a[i-1]} && args.length > 1 && a.length > 0 && k > 0 &&
                // k < a.length
                R = recursiveSearch(a, 0, a.length - 1);
                // R == k
            } else {
                // args.length > 0 && (foreach s : args) {s - > int} && x = Integer.parseInt(args[0]) &&
                // (foreach i in 1..args.length) {a[i-1] = Integer.parseInt(args[i-1]) &&
                // (foreach i in 1..k-1) {a[i] < a[i-1]} &&
                // (foreach i in k+1..a.length-1) {a[i] < a[i-1]} && args.length > 1 && a.length > 0 && k > 0 &&
                // k < a.length
                R = iterativeSearch(a);
                // R == k
            }
            // R == k
        }
        System.out.println(R);
    }
    // Pred: a.length > 0 && (foreach i in 1..k-1) {a[i] < a[i-1]} &&
    // (foreach i in k+1..a.length-1) {a[i] < a[i-1]} && k > 0
    // Post: R == k && a' == a
    public static int iterativeSearch(int[] a) {
        // a.length > 0 && (foreach i in 1..k-1) {a[i] < a[i-1]} &&
        // (foreach i in k+1..a.length-1) {a[i] < a[i-1]} && k > 0 && a' == a
        int l = 0;
        // a.length > 0 && (foreach i in 1..k-1) {a[i] < a[i-1]} &&
        // (foreach i in k+1..a.length-1) {a[i] < a[i-1]} && l >= 0 && k > l && a' == a
        int r = a.length - 1;
        // a.length > 0 && (foreach i in 1..k-1) {a[i] < a[i-1]} &&
        // (foreach i in k+1..a.length-1) {a[i] < a[i-1]} && l >= 0 && k > l && r < a.length && k < r && a' == a
        while (l < r - 1) {
            // a.length > 0 && (foreach i in 1..k-1) {a[i] < a[i-1]} &&
            // (foreach i in k+1..a.length-1) {a[i] < a[i-1]} && l >= 0 && k > l && r < a.length && k < r && a' == a
            int m = (l + r) / 2;
            // a.length > 0 && (foreach i in 1..k-1) {a[i] < a[i-1]} &&
            // (foreach i in k+1..a.length-1) {a[i] < a[i-1]} && l >= 0 && k > l && r < a.length && k < r &&
            // l < r - 1 && l < m && m < r && l >= 0 && r <= a.length && a' == a
            if (a[m] > a[a.length - 1]) {
                // a.length > 0 && (foreach i in 1..k-1) {a[i] < a[i-1]} &&
                // (foreach i in k+1..a.length-1) {a[i] < a[i-1]} && l >= 0 && k > l && r < a.length && k < r &&
                // l < r - 1 && l < m && m < r && l >= 0 && r <= a.length && a' == a && m > k
                r = m;
                // a.length > 0 && (foreach i in 1..k-1) {a[i] < a[i-1]} &&
                // (foreach i in k+1..a.length-1) {a[i] < a[i-1]} && l >= 0 && k > l && r < a.length && k < r &&
                // l < r - 1 && l < m && m < r && l >= 0 && r <= a.length && a' == a && m > k && r' == m && r' < a.length
            } else {
                // a.length > 0 && (foreach i in 1..k-1) {a[i] < a[i-1]} &&
                // (foreach i in k+1..a.length-1) {a[i] < a[i-1]} && l >= 0 && k > l && r < a.length && k < r &&
                // l < r - 1 && l < m && m < r && l >= 0 && r <= a.length && a' == a && m < k
                l = m;
                // a.length > 0 && (foreach i in 1..k-1) {a[i] < a[i-1]} &&
                // (foreach i in k+1..a.length-1) {a[i] < a[i-1]} && l >= 0 && k > l && r < a.length && k < r &&
                // l < r - 1 && l < m && m < r && l >= 0 && r <= a.length && a' == a && m < k && l' == m && l > 0
            }
            // a.length > 0 && (foreach i in 1..k-1) {a[i] < a[i-1]} &&
            // (foreach i in k+1..a.length-1) {a[i] < a[i-1]} && l >= 0 && k > l && r < a.length && k < r &&
            // l < r - 1 && l < m && m < r && l >= 0 && r <= a.length && a' == a && m < k && l' == m &&
            // r' - l' < r - l && l' >= 0 && r' <= a.length && a' == a
        }
        // a.length > 0 && (foreach i in 1..k-1) {a[i] < a[i-1]} &&
        // (foreach i in k+1..a.length-1) {a[i] < a[i-1]} && l >= 0 && k > l && r < a.length && k < r &&
        if (a[r] < a[a.length - 1]) {
            // r == k - 1 && a' == a
            return r + 1;
            // R == k
        }
        // l == k - 1 && a' == a
        return l + 1;
        // R == k
    }
    // Pred: a.length > 0 && (foreach i in 1..k-1) {a[i] < a[i-1]} && l >= 0 && r <= a.length && l < r &&
    // (foreach i in k+1..a.length-1) {a[i] < a[i-1]} && k > l && k <= r + 1
    // Post: R == k && a' == a
    public static int recursiveSearch(int[] a, int l, int r) {
        // a.length > 0 && (foreach i in 1..k-1) {a[i] < a[i-1]} && l >= 0 && r <= a.length && l < r &&
        // (foreach i in k+1..a.length-1) {a[i] < a[i-1]} && k > 0 && a' == a && k > l && k <= r + 1
        if (r - l == 1) {
            // a.length > 0 && (foreach i in 1..k-1) {a[i] < a[i-1]} && l >= 0 && r <= a.length && l < r &&
            // (foreach i in k+1..a.length-1) {a[i] < a[i-1]} && k > 0 && a' == a && r - l == 1 && k > l && k <= r + 1
            if (a[r] < a[a.length - 1]) {
                // k == r + 1 && a' == a
                return r + 1;
                // R == k
            } else {
                // k == r && a' == a
                return l + 1;
                // R == k
            }
        }
        // a.length > 0 && (foreach i in 1..k-1) {a[i] < a[i-1]} && l >= 0 && r <= a.length && l < r &&
        // (foreach i in k+1..a.length-1) {a[i] < a[i-1]} && k > 0 && a' == a && k > l && k <= r + 1 && r - l > 1
        int m = (l + r) / 2;
        // a.length > 0 && (foreach i in 1..k-1) {a[i] < a[i-1]} && l >= 0 && r <= a.length && l < r &&
        // (foreach i in k+1..a.length-1) {a[i] < a[i-1]} && k > 0 && a' == a && k > l && k <= r + 1 && r - l > 1 &&
        // l < m && m < r
        if (a[m] > a[a.length - 1]) {
            // a.length > 0 && (foreach i in 1..k-1) {a[i] < a[i-1]} && l >= 0 && r <= a.length && l < r &&
            // (foreach i in k+1..a.length-1) {a[i] < a[i-1]} && k > 0 && a' == a && k > l && k <= r + 1 && r - l > 1 &&
            // l < m && m < r && k < m && m - l < r - l
            return recursiveSearch(a, l, m);
            // R == k && a' == a
        } else {
            // a.length > 0 && (foreach i in 1..k-1) {a[i] < a[i-1]} && l >= 0 && r <= a.length && l < r &&
            // (foreach i in k+1..a.length-1) {a[i] < a[i-1]} && k > 0 && a' == a && k > l && k <= r + 1 && r - l > 1 &&
            // l < m && m < r && k > m && r - m < r - l
            return recursiveSearch(a, m, r);
            // R == k && a' == a
        }
        // R == k && a' == a
    }
}
