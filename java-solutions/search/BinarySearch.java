package search;

public class BinarySearch {
    // Pred: args.length > 0 && (foreach s : args) {s - > int} &&
    // (foreach i in 2..args.length-1) {Integer.parseInt(args[i]) <= Integer.parseInt(args[i-1])}
    // Post: (exists int i > 0) {args[i] <= args[0]} => Integer.parseInt(args[R + 1]) <= Integer.parseInt(args[0]) &&
    // R > 0 => Integer.parseInt(args[R]) > Integer.parseInt(args[0])
    public static void main(String[] args) {
        // args.length > 0 && (foreach s : args) {s - > int} &&
        // (foreach i in 2..args.length-1) {Integer.parseInt(args[i]) <= Integer.parseInt(args[i-1])}
        int x = Integer.parseInt(args[0]);
        int s = x;
        // args.length > 0 && (foreach s : args) {s - > int} && x = Integer.parseInt(args[0]) &&
        // (foreach i in 2..args.length-1) {Integer.parseInt(args[i]) <= Integer.parseInt(args[i-1])}
        int[] a = new int[args.length - 1];
        // args.length > 0 && (foreach s : args) {s - > int} && x = Integer.parseInt(args[0]) &&
        // (foreach i in 2..args.length-1) {Integer.parseInt(args[i]) <= Integer.parseInt(args[i-1])}
        int i = 0;
        // args.length > 0 && (foreach s : args) {s - > int} && x = Integer.parseInt(args[0]) &&
        // (foreach i in 2..args.length-1) {Integer.parseInt(args[i]) <= Integer.parseInt(args[i-1])} &&
        // (foreach i in 1..i) {a[i-1] = Integer.parseInt(args[i])}
        while (i < args.length - 1) {
            a[i] = Integer.parseInt(args[i + 1]);
            s += a[i];
            // args.length > 0 && (foreach s : args) {s - > int} && x = Integer.parseInt(args[0]) &&
            // (foreach i in 1..i+1) {a[i-1] = Integer.parseInt(args[i]) &&
            // (foreach i in 2..args.length-1) {Integer.parseInt(args[i]) <= Integer.parseInt(args[i-1])}
            i++;
            // args.length > 0 && (foreach s : args) {s - > int} && x = Integer.parseInt(args[0]) &&
            // (foreach i in 1..i) {a[i-1] = Integer.parseInt(args[i]) &&
            // (foreach i in 2..args.length-1) {Integer.parseInt(args[i]) <= Integer.parseInt(args[i-1])}
        }
        // args.length > 0 && (foreach s : args) {s - > int} && x = Integer.parseInt(args[0]) &&
        // (foreach i in 1..args.length-1) {a[i-1] = Integer.parseInt(args[i]) &&
        // (foreach i in 1..a.length-1) {a[i] <= a[i-1]}
        int R;
        // args.length > 0 && (foreach s : args) {s - > int} && x = Integer.parseInt(args[0]) &&
        // (foreach i in 1..args.length-1) {a[i-1] = Integer.parseInt(args[i]) &&
        // (foreach i in 1..a.length-1) {a[i] <= a[i-1]}
        if (args.length == 1) {
            // args.length == 1
            R = 0;
            // (exists int i > 0) {args[i] <= args[0]} => Integer.parseInt(args[R + 1]) <= Integer.parseInt(args[0]) &&
            // R > 0 => Integer.parseInt(args[R]) > Integer.parseInt(args[0])
        } else {
            // (foreach s : args) {s - > int} && x = Integer.parseInt(args[0]) &&
            // (foreach i in 1..args.length-1) {a[i-1] = Integer.parseInt(args[i]) && args.length > 1 && a.length > 0 &&
            // (foreach i in 1..a.length-1) {a[i] <= a[i-1]}
            if (s % 2 == 0) {
                // (foreach s : args) {s - > int} && x = Integer.parseInt(args[0]) &&
                // (foreach i in 1..args.length-1) {a[i-1] = Integer.parseInt(args[i]) && args.length > 1 && a.length > 0 &&
                // (foreach i in 1..a.length-1) {a[i] <= a[i-1]}
                R = recursiveSearch(a, x, 0, a.length);
                // (foreach s : args) {s - > int} && x = Integer.parseInt(args[0]) &&
                // (foreach i in 1..args.length-1) {a[i-1] = Integer.parseInt(args[i]) && args.length > 1 &&
                // (exists int i >= 0) {a[i] <= x} => a[R] <= x && R > 0 => a[R - 1] > x
                // (exists int i > 0) {args[i] <= args[0]} => Integer.parseInt(args[R + 1]) <= Integer.parseInt(args[0]) &&
                // R > 0 => Integer.parseInt(args[R]) > Integer.parseInt(args[0])
            } else {
                // (foreach s : args) {s - > int} && x = Integer.parseInt(args[0]) &&
                // (foreach i in 1..args.length-1) {a[i-1] = Integer.parseInt(args[i]) && args.length > 1 && a.length > 0 &&
                // (foreach i in 1..a.length-1) {a[i] <= a[i-1]}
                R = iterativeSearch(a, x);
                // (foreach s : args) {s - > int} && x = Integer.parseInt(args[0]) &&
                // (foreach i in 1..args.length-1) {a[i-1] = Integer.parseInt(args[i]) && args.length > 1 &&
                // (exists int i >= 0) {a[i] <= x} => a[R] <= x && R > 0 => a[R - 1] > x
                // (exists int i > 0) {args[i] <= args[0]} => Integer.parseInt(args[R + 1]) <= Integer.parseInt(args[0]) &&
                // R > 0 => Integer.parseInt(args[R]) > Integer.parseInt(args[0])
            }
            // (foreach s : args) {s - > int} && x = Integer.parseInt(args[0]) &&
            // (foreach i in 1..args.length-1) {a[i-1] = Integer.parseInt(args[i]) && args.length > 1 &&
            // (exists int i >= 0) {a[i] <= x} => a[R] <= x && R > 0 => a[R - 1] > x
            // (exists int i > 0) {args[i] <= args[0]} => Integer.parseInt(args[R + 1]) <= Integer.parseInt(args[0]) &&
            // R > 0 => Integer.parseInt(args[R]) > Integer.parseInt(args[0])
        }
        System.out.println(R);
    }
    // Pred: a.length > 0 && (foreach i in 1..a.length-1) {a[i] <= a[i-1]}
    // Post: (exists int i >= 0) {a[i] <= key} => a[R] <= key && R > 0 => a[R - 1] > key && a' == a
    public static int iterativeSearch(int[] a, int key) {
        // a.length > 0 && (foreach i in 1..a.length-1) {a[i] <= a[i-1]} && a' == a
        int l = 0;
        // a.length > 0 && (foreach i in 1..a.length-1) {a[i] <= a[i-1]} && (foreach i in 0..l-1) {a[i] > key} && l >= 0
        // && a' == a
        int r = a.length;
        // a.length > 0 && (foreach i in 1..a.length-1) {a[i] <= a[i-1]} && (foreach i in 0..l-1) {a[i] > key} &&
        // (foreach i in r..a.length-1) {a[i] <= key} && l < r && l >= 0 && r <= a.length && a' == a
        while (l < r - 1) {
            // a.length > 0 && (foreach i in 1..a.length-1) {a[i] <= a[i-1]} && (foreach i in 0..l-1) {a[i] > key} &&
            // (foreach i in r..a.length-1) {a[i] <= key} && l < r - 1 && l >= 0 && r <= a.length && a' == a
            int m = (l + r) / 2;
            // a.length > 0 && (foreach i in 1..a.length-1) {a[i] <= a[i-1]} && (foreach i in 0..l-1) {a[i] > key} &&
            // (foreach i in r..a.length-1) {a[i] <= key} && l < r - 1 && l < m && m < r  && l >= 0 && r <= a.length
            // && a' == a
            if (a[m] <= key) {
                // a.length > 0 && (foreach i in 1..a.length-1) {a[i] <= a[i-1]} && (foreach i in 0..l-1) {a[i] > key} &&
                // (foreach i in r..a.length-1) {a[i] <= key} && l < r - 1 && l < m && m < r && a[m] <= key  && l >= 0
                // && r <= a.length && a' == a
                r = m;
                // a.length > 0 && (foreach i in 1..a.length-1) {a[i] <= a[i-1]} && (foreach i in 0..l-1) {a[i] > key} &&
                // (foreach i in r'..a.length-1) {a[i] <= key} && l < r - 1 && l < m && m < r && r' = m && l' >= 0
                // && r' <= a.length && a' == a
            } else {
                // a.length > 0 && (foreach i in 1..a.length-1) {a[i] <= a[i-1]} && (foreach i in 0..l-1) {a[i] > key} &&
                // (foreach i in r..a.length-1) {a[i] <= key} && l < r - 1 && l < m && m < r && a[m] > key
                // && l >= 0 && r <= a.length && a' == a
                l = m;
                // a.length > 0 && (foreach i in 1..a.length-1) {a[i] <= a[i-1]} && (foreach i in 0..l'-1) {a[i] > key} &&
                // (foreach i in r..a.length-1) {a[i] <= key} && l < r - 1 && l < m && m < r && l' = m && l' >= 0
                // && r' <= a.length && a' == a
            }
            // a.length > 0 && (foreach i in 1..a.length-1) {a[i] <= a[i-1]} && (foreach i in 0..l'-1) {a[i] > key} &&
            // (foreach i in r'..a.length-1) {a[i] <= key} && r' - l' < r - l && l' >= 0 && r' <= a.length && a' == a
        }
        // (foreach i in 0..l-1) {a[i] > key} &&
        // (foreach i in r'..a.length-1) {a[i] <= key} && r' - l' == 1 && l' >= 0 && r' <= a.length && a' == a
        if (a[l] <= key) {
            // (foreach i in 0..l'-1) {a[i] > key} && a[l'] <= key && a' == a
            return l;
            // (exists int i >= 0) {a[i] <= key} => a[R] <= key && R > 0 => a[R - 1] > key && a' == a
        }
        // (foreach i in r'..a.length-1) {a[i] <= key} && a[l'] > key && a' == a
        return r;
        // (exists int i >= 0) {a[i] <= key} => a[R] <= key && R > 0 => a[R - 1] > key && a' == a
    }
    // Pred: a.length > 0 && l < r && 0 <= l && r <= a.length && (foreach i in 1..a.length-1) {a[i] <= a[i-1]}
    // Post: (exists int i in l..r) {a[i] <= key} => l <= R && R <= r && a[R] <= key && R > l => a[R - 1] > key &&
    // a' == a
    public static int recursiveSearch(int[] a, int key, int l, int r) {
        // a.length > 0 && l < r && 0 <= l && r <= a.length && (foreach i in 1..a.length-1) {a[i] <= a[i-1]} && a' == a
        if (r - l == 1) {
            // a.length > 0 && l < r && 0 <= l && r <= a.length && (foreach i in 1..a.length-1) {a[i] <= a[i-1]} &&
            // r - l == 1 && a' == a
            if (a[l] <= key) {
                // a.length > 0 && 0 <= l && (foreach i in 1..a.length-1) {a[i] <= a[i-1]} &&
                // r - l == 1 && a' == a && a[l] <= key
                return l;
                // (exists int i in l..r) {a[i] <= key} => l <= R && R <= r && a[R] <= key && R > l => a[R - 1] > key &&
                // a' == a
            } else {
                // a.length > 0 && 0 <= l && r <= a.length && (foreach i in 1..a.length-1) {a[i] <= a[i-1]} &&
                // r - l == 1 && a' == a && a[l] > key
                return r;
                // (exists int i in l..r) {a[i] <= key} => l <= R && R <= r && a[R] <= key && R > l => a[R - 1] > key &&
                // a' == a
            }
        }
        // a.length > 0 && 0 <= l && r <= a.length && (foreach i in 1..a.length-1) {a[i] <= a[i-1]} &&
        // r - l > 1 && a' == a
        int m = (l + r) / 2;
        // a.length > 0 && l < r && 0 <= l && r <= a.length && (foreach i in 1..a.length-1) {a[i] <= a[i-1]} &&
        // r - l > 1 && a' == a && l < r && m < r
        if (a[m] <= key) {
            // a.length > 0 && 0 <= l && r <= a.length && (foreach i in 1..a.length-1) {a[i] <= a[i-1]} &&
            // r - l > 1 && a' == a && l < r && m < r && a[m] <= key && m - l < r - l
            return recursiveSearch(a, key, l, m);
            // (exists int i in l..r) {a[i] <= key} => l <= R && R <= r && a[R] <= key && R > l => a[R - 1] > key &&
            // a' == a
        } else {
            // a.length > 0 && 0 <= l && r <= a.length && (foreach i in 1..a.length-1) {a[i] <= a[i-1]} &&
            // r - l > 1 && a' == a && l < m && m < r && a[m] > key && r - m < r - l
            return recursiveSearch(a, key, m, r);
            // (exists int i in l..r) {a[i] <= key} => l <= R && R <= r && a[R] <= key && R > l => a[R - 1] > key &&
            // a' == a
        }
        // (exists int i in l..r) {a[i] <= key} => l <= R && R <= r && a[R] <= key && R > l => a[R - 1] > key &&
        // a' == a
    }
}
