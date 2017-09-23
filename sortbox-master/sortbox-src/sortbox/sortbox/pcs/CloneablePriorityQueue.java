package sortbox.pcs;


import sortbox.pcs.entity.SortKey;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * This class acts as a helper class. The reason of creating it is I want to clone a priority queue.
 * Tough creating a cloneable one there is still something different.
 * As for what differences, please check the method clone().
 * <p>
 * NOTE THAT: there is a limitation which the class with generic type E but E is the child type of {@link SortKey} tells only SortKey and its child can be used.
 * <p>
 * Created by Clever.Su on 9/14/2017.
 */
public class CloneablePriorityQueue<E extends SortKey> implements Cloneable {

    /**
     * The original list as source list.
     */
    private Queue<E> priorityQueue;

    // constructor.
    public CloneablePriorityQueue(Queue<E> priorityQueue) {
        this.priorityQueue = priorityQueue;
    }

    /**
     * Clone a list with input source list.
     *
     * @return a cloneable list
     * @throws CloneNotSupportedException
     */
    public PriorityQueue<SortKey> clone() throws CloneNotSupportedException {
        super.clone();

        // 1.create a new list
        PriorityQueue<SortKey> cloneOne = new PriorityQueue<>(this.priorityQueue.size());

        // 2.give up keyVal and keep priority value and keyName value merely.
        for (SortKey sk : this.priorityQueue) {
            SortKey cloneSk = new SortKey(sk.getPriority(), sk.getKeyName());
            cloneOne.add(cloneSk);
        }

        // 3. return it
        return cloneOne;
    }
}
