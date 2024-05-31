import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;

/**
 * @author Alessandro Lombardini
 */
public final class Iterators {

    private Iterators(){}
    public static<T> Iterator<T> concat(Iterator<T> it1, Iterator<T> it2){
        return new Iterator<T>() {
            @Override
            public boolean hasNext () {
                return it1.hasNext() || it2.hasNext();
            }

            @Override
            public T next () {
                if(it1.hasNext()){
                    return it1.next();
                }
                return it2.next(); // Will throw a NoSuchElementException if there is no next element so no need to throw one ourselves
            }
        };
    }

    public static <T,R> Iterator<R> map(Iterator<T> it, Function<T,R> fnc){
        return new Iterator<>() {
            @Override
            public boolean hasNext () {
                return it.hasNext();
            }

            @Override
            public R next () {
                return fnc.apply(it.next()); //Same as above
            }
        };
    }

    static <T extends Comparable<T>> T max(Iterator<T> it){
        T max = it.next(); //Will throw a NoSuchElementException if there is no next element
        while (it.hasNext()){
            T next = it.next();
            if(max.compareTo(next) < 0) max = next;
        }
        return max;
    }
}
