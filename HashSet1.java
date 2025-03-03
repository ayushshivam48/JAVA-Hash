package Hash;
import java.util.*;

public class HashSet1 {

    public static void main(String[] args) {
        //Creation
        HashSet<Integer> set = new HashSet<>();

        //Insert
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(1);

        //Size
        System.out.println("Size os set is "+set.size());

        //Print all element 
        System.out.println(set);

        //Search
        if(set.contains(1)){
            System.out.println("Set contain 1.");
        }
        if(!set.contains(6)){
            System.out.println("Set doesn't contain 6.");
        }

        //Delete
        set.remove(1);
        if(!set.contains(1)){
            System.out.println("Set doesn't contain 1 because we delete it.");
        }

        //Iterator
        @SuppressWarnings("rawtypes")
        Iterator it = set.iterator();

        while(it.hasNext()){
            System.out.println(it.next());
        }

    }
}