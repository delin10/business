package nil.ed.business.sensitive.matcher;

/**
 * @author delin10
 * @since 2019/10/28
 **/
public class StringMatchText implements MatchText {

    private String text;

    private int cursor;

    private int completeCharCount;

    public StringMatchText(String text) {
        this.text = text;
    }

    @Override
    public int count() {
        return text.length();
    }

    @Override
    public CharSequence getSourceText() {
        return text;
    }

    @Override
    public int completeCharCount() {
        return completeCharCount;
    }

    @Override
    public boolean hasNext() {
        return cursor < count();
    }

    @Override
    public CharSequence next() {
        CharSequence word = new SubStringView(text, cursor, count());
        return word;
    }

    @Override
    public int getSourceTextCursor() {
        return cursor;
    }

    @Override
    public int getLastSourceTextCursor() {
        return cursor;
    }

    @Override
    public void consume(int consumedCharCount) {
        completeCharCount += consumedCharCount;
        cursor += consumedCharCount;
    }
}
