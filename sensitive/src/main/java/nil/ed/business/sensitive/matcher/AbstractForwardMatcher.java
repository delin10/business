package nil.ed.business.sensitive.matcher;

/**
 * @author delin10
 * @since 2019/10/31
 **/
public abstract class AbstractForwardMatcher extends AbstractLevelMatcher {
    private MatchText text;

    private String[] afterTranslate;

    public AbstractForwardMatcher(MatchLevel level) {
        super(level);
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
                text.consume(matchEnd);
                matchCount = matchEnd;
                break;
            }
            text.consume(1);
        }

        return new MatchResult(text.getSourceTextCursor() - matchCount, text.getSourceTextCursor());
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
            Boolean canEnd = canEnd(tryForwardResult);
            if (canEnd == null) {
                break;
            } else if (canEnd) {
                return j + 1;
            }
        }

        return -1;
    }

    /**
     * 判断当前字符串是否为敏感词
     *
     * @param builder 字符串
     * @return null - 不存在以该字符串为起始的敏感词；false -- 继续添加词；true -- 成功匹配
     */
    protected abstract Boolean canEnd(StringBuilder builder);
}
