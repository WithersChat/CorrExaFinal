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
            boolean switched = false;
            Iterator<T> it = it1;
            @Override
            public boolean hasNext () {
                return it.hasNext();
            }

            @Override
            public T next () {
                if(!switched)
                    if(!hasNext()){
                        it = it2;
                        switched = true;
                    }

                if(it.hasNext()) return it.next();

                throw new UnsupportedOperationException();
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
                if(it.hasNext())
                    return fnc.apply(it.next());

                throw new UnsupportedOperationException();
            }
        };
    }

    static <T extends Comparable<T>> T max(Iterator<T> it){

        if(!it.hasNext()) throw new NoSuchElementException();
        T max = it.next();
        while (it.hasNext()){
            T next = it.next();
            if(max.compareTo(next) < 0) max = next;
        }
        return max;
    }

}
