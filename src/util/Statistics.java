package src.util;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Statistics {
    public class Pair {
        private String key;
        private Integer value;
        
       

        public void setKey(String key) {
            this.key = key;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public Pair(String key, Integer value) {
            this.key = key;
            this.value = value;
        }
    
        public String getKey() {
            return key;
        }
    
        public Integer getValue() {
            return value;
        }
    }

    public PriorityQueue<Pair> emailsCounter = new PriorityQueue<>(Comparator.comparingInt(Pair::getValue));
    
    public PriorityQueue<Pair> phonesCounter = new PriorityQueue<>(Comparator.comparingInt(Pair::getValue));

    public int placeTempCounter;
    
    public int shipTempCounter;
    
    public int cancelTempCounter;
        
}
