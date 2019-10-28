package nil.ed.business.sensitive;

/**
 * @author delin10
 * @since 2019/10/24
 **/
public abstract class AbstractPinyinTranslater {
    /**
     * 拼音注音模式
     */
    enum PinyinToneMode{
        /**
         * 不带注音
         */
        WITHOUT_TONE,
        /**
         * 带注音序号
         */
        WITH_TONE_NUMBER,
        /**
         * 带注音
         */
        WITH_TONE
    }
    protected PinyinToneMode mode;

    public AbstractPinyinTranslater(PinyinToneMode mode) {
        this.mode = mode;
    }

    /**
     * 将text文本转换成中文
     * @param text 原文本
     * @return 拼音字符串
     */
    public abstract String translateToString(CharSequence text);

    public abstract String[] translateToArray(CharSequence text);
}
