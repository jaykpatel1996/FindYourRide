package com.FindYourRide.Routing.resources;

import java.util.Arrays;
import java.util.Scanner;

public class RoughWork {

    public static Scanner sc = new Scanner(System.in);

    public static void solve() {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; ++i)
        {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        int ans = 0;
        for(int i = 1; i < n - 1; ++i)
        {
            ans = Math.max(ans, Math.abs(arr[0] - arr[i]) + Math.abs(arr[i] - arr[n-1]) + Math.abs(arr[n - 1] - arr[0]));
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        int t = sc.nextInt();
        while(t-- > 0)
        {
            solve();
        }
    }
}
