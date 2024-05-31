public final class SStringBuilder {
    private char[] buffer = new char[16];
    private int gapPos = 0;
    private int gapSize = buffer.length;

    public int length() {
        return buffer.length - gapSize;
    }

    public SStringBuilder insert(int i, char c) {
        if (i < 0 || i > length())
            throw new IndexOutOfBoundsException();
        if (gapSize == 0) {
            char[] newBuffer = new char[length() * 2];
            gapSize = buffer.length;
            System.arraycopy(buffer, 0, newBuffer, 0, buffer.length);
            buffer = newBuffer;
        }
        if (gapPos != i) {
            if (i < gapPos) {
                System.arraycopy(buffer, i, buffer, i + gapSize, gapPos - i);
            } else  {
                System.arraycopy(buffer, gapPos + gapSize, buffer, gapPos, i - gapPos);
            }
        }
        buffer[i] = c;
        --gapSize;
        gapPos = i + 1;

        return this;
    }

    public String toString() {
        return String.valueOf(buffer, 0, gapPos)
                + String.valueOf(buffer, gapPos + gapSize, length() - gapPos);
    }
}
