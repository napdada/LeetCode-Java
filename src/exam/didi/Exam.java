package exam.didi;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 滴滴一面二选一：
 * 1、写一个随机红包算法，入参为红包总金额，和红包总个数，出参为金额列表。
 * 要求：每个人至少分到一分钱。给出每个用户能分到金额的期望值（语言说明即可）
 * 可选内容：尽可能使得每个人收到的红包金额期望均等（必须为随机算法）
 *
 * 2、设计一个支持在平均时间复杂度O(1) 下，执行以下操作的数据结构。
 * insert(val)：当元素 val 不存在时，向集合中插入该项。
 * remove(val)：元素 val 存在时，从集合中移除该项。
 * getRandom：随机返回现有集合中的一项。每个元素应该有相同的概率被返回。
 */
public class Exam {
    private HashMap<Integer, Integer> map = new HashMap<>();
    private ArrayList<Integer> list = new ArrayList<>();

    public boolean insert(int val) {
        if (!map.containsKey(val)) {
            list.add(val);
            map.put(val, list.size() - 1);
            return true;
        }
        return false;
    }

    public boolean remove(int val) {
        if (map.containsKey(val)) {
            int index = map.get(val), tmp = list.get(list.size() - 1);
            map.remove(val);
            list.set(index, tmp);
            list.remove(list.size() - 1);
            return true;
        }
        return false;
    }

    public int getRandom() {
        int len = list.size(), randomNum = (int) (Math.random() * len);
        return list.get(randomNum);
    }
}
