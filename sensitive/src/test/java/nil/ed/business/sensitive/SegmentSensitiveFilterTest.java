package nil.ed.business.sensitive;

import nil.ed.business.sensitive.matcher.AbstractLevelMatcher;
import nil.ed.business.sensitive.matcher.AnotherHashMapMatcher;
import nil.ed.business.sensitive.matcher.TierTreeMatcher;
import nil.ed.business.sensitive.seg.impl.WordSegmenterImpl;
import nil.ed.business.sensitive.util.Arrayx;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class SegmentSensitiveFilterTest {

    @Test
    public void test()  {
        Function<List<String>, SensitiveFilter> constructor = lib ->
                new SegmentSensitiveFilter(new AnotherHashMapMatcher(AbstractLevelMatcher.MatchLevel.NORMAL, lib), new WordSegmenterImpl());
        testFramework(constructor);
    }

    public void testFramework(Function<List<String>, SensitiveFilter> constructor){
        List<String> words = Arrays.asList("鸡掰","鸡巴","鸡巴鬼", "鸡巴真小","傻逼", "沙雕", "煞笔", "草", "操", "你妈", "尼玛");
        SensitiveFilter filter = constructor.apply(words);
        /*
        bug：重复字符导致状态机循环
         */
        String text = Arrayx.repeat("鸡掰你这个鸡巴鬼，鸡鸡鸡鸡鸡鸡鸡巴真小,真是个傻逼，操你大爷，操尼玛,机掰，及掰，泥马", 1);
        long start = System.currentTimeMillis();
        System.out.println(filter.filter(text));
        System.out.println("匹配实际耗时:" + (System.currentTimeMillis() - start));
    }
}