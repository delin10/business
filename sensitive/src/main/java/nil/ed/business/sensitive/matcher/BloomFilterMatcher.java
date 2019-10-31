package nil.ed.business.sensitive.matcher;

import com.google.common.base.Strings;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;

import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author delin10
 * @since 2019/10/31
 **/
public class BloomFilterMatcher extends AbstractForwardMatcher {
    private BloomFilter<StringBuilder> bloomFilter = BloomFilter.create(StringFunnel.INSTANCE, 1024*1024*32);

    public BloomFilterMatcher(MatchLevel level, List<String> lib) {
        super(level);
        initLibrary(lib);
    }

    @Override
    protected Boolean canEnd(StringBuilder builder) {
        return bloomFilter.mightContain(builder);
    }

    private void initLibrary(List<String> words){
        words.stream().map(StringBuilder::new).forEach(bloomFilter::put);
    }

    /**
     * 用于布隆过滤器
     */
    private enum StringFunnel implements Funnel<StringBuilder>{
        /**
         * 单例模式
         */
        INSTANCE;

        @Override
        public void funnel(StringBuilder from, PrimitiveSink into) {
            into.putString(from, StandardCharsets.UTF_8);
        }
    }
}
