package nil.ed.business.sensitive.matcher;

import java.util.ArrayList;
import java.util.List;

/**
 * @author delin10
 * @since 2019/10/28
 **/
public class SegmentMatchText implements MatchText {
    private List<String> words;
    public SegmentMatchText(List<String> words, boolean share) {
        if (share){
            this.words = words;
        }else{
            this.words = new ArrayList<>(words.size());
            this.words.addAll(words);
        }
    }

    @Override
    public CharSequence wordAt(int i) {
        return this.words.get(i);
    }

    @Override
    public int count() {
        return this.words.size();
    }

    @Override
    public CharSequence regionWordString(int start, int end) {
        throw new UnsupportedOperationException();
    }
}
