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

    private String[] afterTranslate;

    public AnotherHashMapMatcher(MatchLevel level, List<String> words) {
        super(level);
        initSensitiveLibrary(words);
    }

    @Override
    public MatchResult next() {
        int matchCount = 0;

        while (text.hasNext()) {
            CharSequence sequence = text.next();
            /*
             当前子串在原文本中的起始位置
             */
            int baseCharIndex = text.getLastSourceTextCursor();
            int matchEnd = match(sequence, baseCharIndex);
            if (matchEnd > 0){
                matchCount = matchEnd;
                break;
            }
        }

        return new MatchResult(text.getLastSourceTextCursor(), text.getLastSourceTextCursor() + matchCount);
    }

    @Override
    public boolean continuable() {
        return text.hasNext();
    }

    @Override
    public void setMatchText(MatchText text) {
        this.text = text;
        afterTranslate = translateToArray(text.getSourceText());
    }

    private int match(CharSequence sequence, int baseCharIndex){
        StringBuilder tryForwardResult = new StringBuilder();
        for (int j = 0; j < sequence.length(); ++j) {
            tryForwardResult.append(afterTranslate[baseCharIndex + j]);
            Boolean canEnd = stateMachine.get(tryForwardResult.toString());
            if (canEnd == null) {
                break;
            } else if (canEnd) {
                return j + 1;
            }
        }

        return -1;
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
                stateMachine.putIfAbsent(translate(builder.toString()), false);
            }
            stateMachine.put(translate(word), true);
        }
    }
}
