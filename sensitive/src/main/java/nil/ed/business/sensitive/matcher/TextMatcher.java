package nil.ed.business.sensitive.matcher;

/**
 * @author delin10
 * @since 2019/10/28
 **/
public interface TextMatcher {
    /**
     * 获取下一个匹配
     * @return 下一个匹配的结果
     */
    MatchResult next();

    /**
     * 是否可以继续匹配
     *
     * @return 是否可以继续匹配
     */
    boolean continuable();

    /**
     * 设置匹配文本
     *
     * @param text 文本
     */
    void setMatchText(MatchText text);
}
