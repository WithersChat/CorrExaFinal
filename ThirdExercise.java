import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * @author Alessandro Lombardini
 */
public class ThirdExercise {
    public static <T>Comparator<List<T>> lexicographic(Comparator<T> cmp){
        return new Comparator<List<T>>() {
            @Override
            public int compare (List<T> l1, List<T> l2) {
                Iterator<T> it1 = l1.iterator();
                Iterator<T> it2 = l2.iterator();
                int result = 0;
                while(it1.hasNext()&& it2.hasNext()){
                    if(result!=0) return result;
                    result = cmp.compare(it1.next(),it2.next());
                }
                return Integer.compare(l1.size(),l2.size());
            }
        };
    }
    public static <T>Comparator<List<T>> lexicographic2(Comparator<T> cmp){
        return (l1, l2) -> {
            Iterator<T> it1 = l1.iterator();
            Iterator<T> it2 = l2.iterator();
            int result = 0;
            while(it1.hasNext()&& it2.hasNext()){
                if(result!=0) return result;
                result = cmp.compare(it1.next(),it2.next());
            }
            return Integer.compare(l1.size(),l2.size());
        };
    }

}
