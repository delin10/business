package nil.ed.business.sensitive.matcher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author delin10
 * @since 2019/10/28
 **/
public class AnotherHashMapMatcher extends AbstractLevelMatcher {
    private Map<String, Boolean> stateMachine = new HashMap<>();

    private MatchText text;

    private int cursor;

    public AnotherHashMapMatcher(MatchLevel level, List<String> words) {
        super(level);
        initSensitiveLibrary(words);
    }

    @Override
    public MatchResult next() {
        int i = cursor, j = 0;
        String[] afterTranslate = translateToArray(text.wordAt(cursor));
        ok:
        for(; i < text.count(); ++i) {
            StringBuilder tryForwardResult = new StringBuilder();
            CharSequence sequence = text.wordAt(i);
            for (j = 0; j < sequence.length(); ++j) {
                tryForwardResult.append(afterTranslate[i + j]);
                Boolean canEnd = stateMachine.get(tryForwardResult.toString());
                if (canEnd == null) {
                    break;
                } else if (canEnd) {
                    break ok;
                }
            }
        }
        cursor = j + 1;
        return new MatchResult(i, j + 1);
    }

    @Override
    public boolean continuable() {
        return cursor < text.count();
    }

    @Override
    public void setMatchText(MatchText text) {
        this.text = text;
        cursor = 0;
    }

    /**
     * 初始化敏感字词库
     *
     * @param words 字词列表
     */
    private void initSensitiveLibrary(List<String> words){
        for (String word : words){
            StringBuilder builder = new StringBuilder(word.length());
            for (int i = 0; i < word.length(); ++i){
                builder.append(word.charAt(i));
                stateMachine.putIfAbsent(translate(builder.toString()), false);;
            }
            stateMachine.put(translate(word), true);
        }
    }
}
