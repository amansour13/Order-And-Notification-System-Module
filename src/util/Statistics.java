package src.util;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Statistics {
    public class Pair<K, V> {
        private K key;
        private V value;
    
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    
        public K getKey() {
            return key;
        }
    
        public V getValue() {
            return value;
        }
    }

    public PriorityQueue<Pair<String, Integer>> emailsCounter = new PriorityQueue<>(Comparator.comparingInt(Pair::getValue));
    
    public PriorityQueue<Pair<String, Integer>> phonesCounter = new PriorityQueue<>(Comparator.comparingInt(Pair::getValue));

    public int placeTempCounter;
    
    public int shipTempCounter;
    
    public int cancelTempCounter;
        
}
