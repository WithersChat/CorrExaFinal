import java.io.IOException;
import java.io.InputStream;

/**
 * @author Alessandro Lombardini
 */
public final class VarInputStream implements AutoCloseable{
    private final InputStream stream;

    public VarInputStream(InputStream inputStream){
        this.stream = inputStream;

    }
    public int readVarInt() throws IOException{
        boolean first = true;
        int remaining = -1;
        int value = 0;

        while(remaining!=0){
            int v = stream.read();
            if(v==-1) return -1;
            if(first){
                remaining = Integer.numberOfTrailingZeros(v);
                first = false;
                int x = remaining >> (remaining + 1);
                int mask = 0b1111_1111;
                if(remaining != 0 && x == 0) throw new IOException();
                if(remaining == 0 && v >> 7==0 && x!=0) throw new IOException();
                if(remaining == 3 ){
                    if (v!=0b1000)
                        throw new IOException();
                    mask = 0;
                } else if(remaining != 0 && v >> 7 == 0) throw new IOException();

                value += (v>>(remaining+1))<<(remaining * 8);
            } else {
                if(remaining == 3 && v >> 7==0)  throw new IOException();
                value += v<<(--remaining * 8);
            }
        }
        stream.close();
        return value;
    }


    @Override
    public void close () throws Exception {
        stream.close();
    }

}
