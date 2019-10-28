package nil.ed.business.sensitive;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.stream.IntStream;

/**
 * @author delin10
 * @since 2019/10/24
 **/
public class Pinyin4jTranslater extends AbstractPinyinTranslater {
    public static final AbstractPinyinTranslater WITHOUT_TONE_TRANSLATER = new Pinyin4jTranslater(PinyinToneMode.WITHOUT_TONE);
    public static final AbstractPinyinTranslater WITH_TONE_NUMBER = new Pinyin4jTranslater(PinyinToneMode.WITH_TONE_NUMBER);
    public static final AbstractPinyinTranslater WITH_TONE = new Pinyin4jTranslater(PinyinToneMode.WITH_TONE);

    public Pinyin4jTranslater(PinyinToneMode mode) {
        super(mode);
    }

    @Override
    public String translateToString(CharSequence text) {
        try {
            HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
            defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
            defaultFormat.setToneType(map(mode));
            StringBuilder builder = new StringBuilder(text.length() * 3);
            for (int i = 0; i < text.length(); ++i){
                String pinyin = PinyinHelper.toHanYuPinyinString(String.valueOf(text.charAt(i)), defaultFormat, "", false);
                builder.append(pinyin);
            }
            return builder.toString();
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            System.err.println(badHanyuPinyinOutputFormatCombination);
            return text.toString();
        }
    }

    @Override
    public String[] translateToArray(CharSequence text) {
        try {
            HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
            defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
            defaultFormat.setToneType(map(mode));
            String[] pingyins = new String[text.length()];
            for (int i = 0; i < text.length(); ++i){
                String pinyin = PinyinHelper.toHanYuPinyinString(String.valueOf(text.charAt(i)), defaultFormat, "", false);
                pingyins[i] = pinyin;
            }
            return pingyins;
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            System.err.println(badHanyuPinyinOutputFormatCombination);
            return IntStream.range(0, text.length())
                    .mapToObj(i -> String.valueOf(text.charAt(i)))
                    .toArray(String[]::new);
        }
    }

    private HanyuPinyinToneType map(PinyinToneMode mode){
        switch (mode){
            case WITH_TONE:
                return HanyuPinyinToneType.WITH_TONE_MARK;
            case WITHOUT_TONE:
                return HanyuPinyinToneType.WITHOUT_TONE;
            case WITH_TONE_NUMBER:
                return HanyuPinyinToneType.WITH_TONE_NUMBER;
            default:
                return null;
        }
    }
}
