package nil.ed.business.sensitive.matcher;

import java.util.ArrayList;
import java.util.List;

/**
 * @author delin10
 * @since 2019/10/28
 **/
public class SegmentMatchText implements MatchText {

    private List<String> words;

    private String sourceText;

    private int charCount;

    private int cursor;

    public SegmentMatchText(String sourceText, List<String> words, boolean share) {
        this.sourceText = sourceText;
        if (share){
            this.words = words;
        }else{
            this.words = new ArrayList<>(words.size());
            this.words.addAll(words);
        }
    }

    @Override
    public int count() {
        return this.words.size();
    }

    @Override
    public CharSequence getSourceText() {
        return this.sourceText;
    }

    @Override
    public int completeCharCount() {
        return this.charCount;
    }

    @Override
    public boolean hasNext() {
        return cursor < count();
    }

    @Override
    public CharSequence next() {
        CharSequence word = words.get(cursor++);
        charCount += word.length();
        return word;
    }

    @Override
    public int getSourceTextCursor() {
        return charCount;
    }

    @Override
    public int getLastSourceTextCursor() {
        if (cursor == 0){
            return -1;
        }

        return charCount - words.get(cursor - 1).length();
    }
}
