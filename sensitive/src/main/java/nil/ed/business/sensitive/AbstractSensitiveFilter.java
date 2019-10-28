package nil.ed.business.sensitive;

import nil.ed.business.sensitive.matcher.MatchResult;
import nil.ed.business.sensitive.matcher.MatchText;
import nil.ed.business.sensitive.matcher.TextMatcher;

/**
 * @author delin10
 * @since 2019/10/28
 **/
public abstract class AbstractSensitiveFilter implements SensitiveFilter {

    private TextMatcher matcher;

    protected String replaceMask;

    public AbstractSensitiveFilter(TextMatcher matcher) {
        this("*", matcher);
    }

    public AbstractSensitiveFilter(String replaceMask, TextMatcher matcher) {
        this.replaceMask = replaceMask;
        this.matcher = matcher;
    }

    @Override
    public FilterResult filter(String sourceText) {
        FilterResult filterResult = new FilterResult(sourceText);

        if (sourceText == null || sourceText.isEmpty()){
            filterResult.setMaskText("");
            filterResult.setMaskCharPercent(0D);
            filterResult.setMaskCount(0);
            return filterResult;
        }
        MatchText text = getMatchText(sourceText);
        matcher.setMatchText(text);
        filterResult.setSourceText(sourceText);
        StringBuilder maskResult = new StringBuilder();
        int maskWordCount = 0, maskCharCount = 0;
        int cursor = 0;
        while (matcher.continuable()){
            MatchResult result = matcher.next();

            if (!result.hasMatch()){
                continue;
            }

            maskCharCount += handleMatchResult(sourceText, text, cursor, maskResult, result);
            maskWordCount++;
            cursor = result.getEnd();
        }
        handleMatchResult(sourceText, text, cursor, maskResult, null);
        filterResult.setMaskText(maskResult.toString());
        filterResult.setMaskCount(maskWordCount);
        filterResult.setMaskCharPercent((1.0 * maskCharCount) / sourceText.length());

        return filterResult;
    }

    /**
     * 返回封装后的文本
     * @param sourceText 原文本
     * @return MatchText文本
     */
    protected abstract MatchText getMatchText(String sourceText);

    /**
     * 处理匹配结果
     * @param sourceText 原文本
     * @param text 处理后的文本
     * @param cursor 当前处理指针
     * @param maskText 脱敏文本
     * @param matchResult 匹配结果
     * @return 敏感字符个数
     */
    protected abstract int handleMatchResult(String sourceText, MatchText text, int cursor, StringBuilder maskText, MatchResult matchResult);
}
