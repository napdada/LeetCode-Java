package exam.wanmeishijie;

import java.util.Scanner;

/**
 * 完美世界笔试第四题：每个道具有价值和重量，玩家背包有最大容纳重量，求可拾取到背包的最大价值（AC）
 */
public class Exam4 {
    public int knapsackCapacity, max = 0;
    public int[] weights, values;
    public int method(int diamondCount, int knapsackCapacity, int[] weights, int[] values) {
        this.knapsackCapacity = knapsackCapacity;
        this.weights = weights;
        this.values = values;
        recur(0, true, 0, 0);
        recur(0, false, 0, 0);
        return max;
    }

    public void recur(int index, boolean flag, int weight, int value) {
        if (index >= weights.length || weight > knapsackCapacity) {
            max = Math.max(max, value);
            return;
        }
        if (weight + weights[index] <= knapsackCapacity && flag) {
            weight += weights[index];
            value += values[index];
        }
        recur(index + 1, true, weight, value);
        recur(index + 1, false, weight, value);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int diamondCount = Integer.parseInt(input.nextLine().trim());
        int knapsackCapacity = Integer.parseInt(input.nextLine().trim());
        String[] weightsStr = input.nextLine().split(" ");
        int[] weights = new int[weightsStr.length];
        for (int i = 0; i < weightsStr.length; i++) {
            weights[i] = Integer.parseInt(weightsStr[i].trim());
        }
        String[] valuesStr = input.nextLine().split(" ");
        int[] values = new int[valuesStr.length];
        for (int i = 0; i < valuesStr.length; i++) {
            values[i] = Integer.parseInt(valuesStr[i].trim());
        }
        System.out.println(new Exam4().method(diamondCount, knapsackCapacity, weights, values));
        input.close();
    }
}
