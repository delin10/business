package nil.ed.business.sensitive.matcher;

import java.util.Iterator;

/**
 * @author delin10
 * @since 2019/10/28
 **/
public interface MatchText extends Iterator<CharSequence> {
    /**
     * 单词的数量
     *
     * @return 单词的数量
     */
    int count();

    /**
     * 获取原文本
     *
     * @return 原文本
     */
    CharSequence getSourceText();

    /**
     * 返回处理过的字符的数量
     *
     * @return 处理过字符数量
     */
    int completeCharCount();

    /**
     * 返回当前在原文本的指针
     *
     * @return 原文本中的指针
     */
    int getSourceTextCursor();

    /**
     * 获取上一次调用next时在原文本的下标
     *
     * @return 上一次调用next时在原文本的下标
     */
    int getLastSourceTextCursor();

    /**
     * 消耗的字符数量
     *
     * @param consumedCharCount 消耗的字符数量
     */
    void consume(int consumedCharCount);
}
