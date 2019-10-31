package nil.ed.business.sensitive.matcher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author delin10
 * @since 2019/10/28
 **/
public class AnotherHashMapMatcher extends AbstractForwardMatcher {
    private Map<String, Boolean> stateMachine = new HashMap<>();

    public AnotherHashMapMatcher(MatchLevel level, List<String> words) {
        super(level);
        initSensitiveLibrary(words);
    }

    @Override
    protected Boolean canEnd(StringBuilder builder) {
        return stateMachine.get(builder.toString());
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
