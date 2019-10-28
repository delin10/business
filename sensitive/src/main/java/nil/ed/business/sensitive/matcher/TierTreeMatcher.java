package nil.ed.business.sensitive.matcher;

import nil.ed.business.sensitive.type.TireTree;
import nil.ed.business.sensitive.type.TireTreeFindResult;

import java.util.List;

/**
 * @author delin10
 * @since 2019/10/28
 **/
public class TierTreeMatcher extends AbstractLevelMatcher {
    private TireTree tireTree = new TireTree();

    private MatchText text;

    private int cursor;

    public TierTreeMatcher(MatchLevel level, List<String> words) {
        super(level);
        initWordLibrary(words);
    }

    private void initWordLibrary(List<String> words){
        for (String word : words){
            tireTree.insert(translate(word));
        }
    }

    @Override
    public MatchResult next() {
        TireTreeFindResult result = null;
        int i = cursor, j = -1;
        for(; i < text.count(); ++i) {
            /*
            todo 需要优化，太多次拼音转换
             */
            String translateStr = translate(text.wordAt(i));
            result = tireTree.find(translateStr, 0, translateStr.length());
            if (result.hasFind()) {
                if (text instanceof SegmentMatchText) {
                    j = i + 1;
                } else {
                    j = i + result.getMatchTextEnd();
                }
                break;
            }
        }
        cursor = j == -1 ? text.count() : j;
        return new MatchResult(i, j);
    }

    @Override
    public boolean continuable() {
        return cursor < text.count();
    }

    @Override
    public void setMatchText(MatchText text) {
        this.text = text;
        this.cursor = 0;
    }
}
