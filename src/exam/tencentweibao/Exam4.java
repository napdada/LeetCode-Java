package exam.tencentweibao;

import java.util.Arrays;

/**
 * 腾讯微保笔试第二题：找两个出现一次的数，其他都出现两次（80%）。LeetCode上 跑了一模一样 AC 了，怀疑测试用例有问题。
 */
public class Exam4 {
    public static void main(String[] args) {
        int[] a = {1,1,3,4,4,7,5,7};
        System.out.println(Arrays.toString(new Exam4().getNumsAppearOnceTwo2(a)));
    }

    public int[] getNumsAppearOnceTwo (int[] arr) {
        int num = arr[0];
        for (int i = 1; i < arr.length; i++) {
            num = num ^ arr[i];
        }
        return null;
    }

    public int[] getNumsAppearOnceTwo2 (int[] arr) {
        int[] a = new int[2];
        int j = 0;
        boolean flag = false;
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 1; ) {
            if (i + 1 < arr.length && arr[i] != arr[i + 1]) {
                a[j] = arr[i];
                i++;
                j++;
                flag = true;
            } else i += 2;
        }
        if (j != 2 && flag) a[1] = arr[arr.length - 1];
        if (!flag) return null;
        return a;
    }
}
