import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println("BENCHMARKING TRAVERSAL and SORT \n -------------------------");
        ArrayList<Long>arr = new ArrayList<>();
        for(long i=0; i<10000000; i++)
            arr.add(i);

        System.out.println("Traversal Operation : SUM of elements in Array");
        //Reduce operation
        long st = System.currentTimeMillis();
        Long sum = arr.stream().reduce(0L, (acc, a)-> acc + a);
        long ed = System.currentTimeMillis();
        System.out.println("Reduce - " + (ed - st) + "ms");
`
        //Parallel Reduce
        st = System.currentTimeMillis();
        sum = arr.parallelStream().reduce(0L, (acc, a)-> acc + a);
        ed = System.currentTimeMillis();
        System.out.println("Parallel Reduce - " + (ed - st) + "ms");

        //Sum operation
        st = System.currentTimeMillis();
        sum = arr.stream().mapToLong(x->x.longValue()).sum();
        ed = System.currentTimeMillis();
        System.out.println("Long stream - " + (ed - st) + "ms");

        //Parallel Sum operation
        st = System.currentTimeMillis();
        sum = arr.stream().mapToLong(x->x.longValue()).sum();
        ed = System.currentTimeMillis();
        System.out.println("Parallel Long stream - " + (ed - st) + "ms");

        //For Loop
        st = System.currentTimeMillis();
        sum = 0L;
        for(Long x : arr)
            sum += x;
        ed = System.currentTimeMillis();
        System.out.println("For loop - " + (ed - st) + "ms");

        //-----------------SORTING----------------------------//

        System.out.println("-------------------------------------------");
        st = System.currentTimeMillis();
        List<Long> sortedList  = arr.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        ed = System.currentTimeMillis();
        System.out.println("Stream Sort - " + (ed - st) + "ms");

        st = System.currentTimeMillis();
        sortedList  = arr.parallelStream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        ed = System.currentTimeMillis();
        System.out.println("Parallel Stream Sort - " + (ed - st) + "ms");

        List<Long>arr1 = new ArrayList<>(arr);
        st = System.currentTimeMillis();
        arr1.sort(Comparator.reverseOrder());
        ed = System.currentTimeMillis();
        System.out.println("Normal Sort - " + (ed - st) + "ms");

        st = System.currentTimeMillis();
        Collections.sort(arr, Comparator.reverseOrder());
        arr1.sort(Comparator.reverseOrder());
        ed = System.currentTimeMillis();
        System.out.println("Collections Sort - " + (ed - st) + "ms");
    }
}