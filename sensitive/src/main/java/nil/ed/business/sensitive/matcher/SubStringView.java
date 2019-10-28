package nil.ed.business.sensitive.matcher;

/**
 * 使用视图减少substring的开销
 *
 * @author delin10
 * @since 2019/10/28
 **/
public class SubStringView implements CharSequence{
    private String sourceString;
    private int start;
    private int end;
    private int length;

    public SubStringView(String sourceString, int start, int end) {
        checkBounds(sourceString, start, end);
        this.sourceString = sourceString;
        this.start = start;
        this.end = end;
        this.length = end - start;
    }

    @Override
    public int length() {
        return this.length;
    }

    @Override
    public char charAt(int index) {
        if (start + index > end){
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
        return sourceString.charAt(start + index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return new SubStringView(sourceString, start, end);
    }

    @Override
    public String toString() {
        return sourceString.substring(start, end);
    }

    private void checkBounds(String string, int start, int end){
        if (start > end){
            throw new IllegalArgumentException("start > end");
        }

        if (end > string.length()){
            throw new IllegalArgumentException("end is greater than source string length");
        }
    }
}
