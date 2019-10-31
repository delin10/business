package nil.ed.business.sensitive.matcher;

import nil.ed.business.sensitive.type.TireTree;
import nil.ed.business.sensitive.type.TireTreeFindResult;

import java.util.List;
import java.util.Optional;

/**
 * @author delin10
 * @since 2019/10/28
 **/
public class TierTreeMatcher extends AbstractLevelMatcher {
    private TireTree tireTree = new TireTree();

    private MatchText text;

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
        while (text.hasNext()) {
            /*
            todo 需要优化，太多次拼音转换
             */
            CharSequence next = text.next();
            String translateStr = translate(next);
            result = tireTree.find(translateStr, 0, translateStr.length());
            if (result.hasFind()) {
                text.consume(result.matchCharCount());
                break;
            }

            text.consume(1);
        }

        int matchCount = Optional.ofNullable(result).map(TireTreeFindResult::matchCharCount).orElse(0);
        return new MatchResult(text.getLastSourceTextCursor(), text.getLastSourceTextCursor() + matchCount);
    }

    @Override
    public boolean continuable() {
        return text.hasNext();
    }

    @Override
    public void setMatchText(MatchText text) {
        this.text = text;
    }
}
