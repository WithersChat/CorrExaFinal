import java.util.AbstractList;
import java.util.List;
import java.util.random.RandomGenerator;

/**
 * @author Alessandro Lombardini
 */
public class FirstExercice {


    public static List<Integer> intArrayAsList(int[] array){
        return new AbstractList<Integer>() {


            @Override
            public Integer get (int i) {
                if(i<0||i>=size()) throw new IndexOutOfBoundsException();
                return array[i];
            }

            @Override
            public int size () {
                return array.length;
            }

            //il fallait mettre un override pour l'examen
            public int set(int i, int v){
                if(i<0||i>=size()) throw new IndexOutOfBoundsException();
                int temp = get(i);
                array[i] = v;
                return temp;
            }
        };
    }
    public static void shuffle(int[] array, RandomGenerator rnd){
        for(int i=0; i<array.length-1;++i){
            int nextRand = rnd.nextInt(i,array.length);
            int temp = array[i];
            array[i] = array[nextRand];
            array[nextRand] = temp;
        }
    }

}
