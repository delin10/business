package nil.ed.business.sensitive.matcher;

/**
 * @author delin10
 * @since 2019/10/28
 **/
public class StringMatchText implements MatchText {
    private String text;

    public StringMatchText(String text) {
        this.text = text;
    }

    @Override
    public CharSequence wordAt(int i) {
        return new SubStringView(text, i, text.length());
    }

    @Override
    public int count() {
        return text.length();
    }

    @Override
    public CharSequence regionWordString(int start, int end) {
        return text.substring(start, end);
    }
}
