package nil.ed.business.sensitive.hash.impl;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;
import nil.ed.business.sensitive.hash.IBloomFilter;

import java.nio.charset.StandardCharsets;

/**
 * @author delin10
 * @since 2019/11/5
 **/
@SuppressWarnings("UnstableApiUsage")
public class GuavaBloomFilterImpl<T> implements IBloomFilter<T> {
    public static final Funnel<String> STRING_FUNNEL = new StringFunnel();

    private BloomFilter<T> bloomFilter;

    public GuavaBloomFilterImpl(Funnel<T> funnel, int expectedInsertions) {
        this.bloomFilter = BloomFilter.create(funnel, expectedInsertions);
    }

    @Override
    public boolean exist(T e) {
        return bloomFilter.mightContain(e);
    }

    @Override
    public boolean insert(T e) {
        return bloomFilter.put(e);
    }

    private static class StringFunnel implements Funnel<String>{
        @Override
        public void funnel(String from, PrimitiveSink into) {
            into.putString(from, StandardCharsets.UTF_8);
        }
    }
}
