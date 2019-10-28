package nil.ed.business.sensitive;

/**
 * @author delin10
 * @since 2019/10/23
 **/
public interface SensitiveFilter {
    /**
     * 过滤文本
     *
     * @param sourceText 源文本
     * @return 过滤的结果
     */
    FilterResult filter(String sourceText);
}
