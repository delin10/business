package nil.ed.business.sensitive.matcher;

import nil.ed.business.sensitive.AbstractPinyinTranslater;
import nil.ed.business.sensitive.Pinyin4jTranslater;

/**
 * @author delin10
 * @since 2019/10/28
 **/
public abstract class AbstractLevelMatcher implements TextMatcher {
    /**
     * 过滤的等级
     */
    public enum MatchLevel{
        /**
         * 普通等级，根据词汇进行过滤
         */
        NORMAL,
        /**
         * 严格等级，根据拼音包含音调进行过滤
         */
        STRICT,
        /**
         * 超级严格,根据拼音不包含音调进行过滤
         */
        SUPER_STRICT
    }

    private MatchLevel level;

    public AbstractLevelMatcher(MatchLevel level) {
        this.level = level;
    }

    /**
     * 返回str的拼音
     * @param str 原文本
     * @return 拼音字符串
     */
    protected String translate(CharSequence str){
        AbstractPinyinTranslater translater = getTranslater();

        return translater == null ? str.toString() : translater.translateToString(str);
    }

    /**
     * 返回str的拼音
     * @param str 原文本
     * @return 拼音字符串
     */
    protected String[] translateToArray(CharSequence str){
        AbstractPinyinTranslater translater = getTranslater();
        return translater == null ? toStringArray(str) : translater.translateToArray(str);
    }

    private String[] toStringArray(CharSequence str){
        String[] strArray = new String[str.length()];
        for (int i = 0; i < str.length(); ++i){
            strArray[i] = String.valueOf(str.charAt(i));
        }
        return strArray;
    }

    private AbstractPinyinTranslater getTranslater(){
        switch (level){
            case STRICT:
                return Pinyin4jTranslater.WITH_TONE_NUMBER;
            case SUPER_STRICT:
                return Pinyin4jTranslater.WITHOUT_TONE_TRANSLATER;
            default:
        }
        return null;
    }
}
