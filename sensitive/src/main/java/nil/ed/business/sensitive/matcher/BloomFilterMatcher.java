package nil.ed.business.sensitive.matcher;

import nil.ed.business.sensitive.hash.IBloomFilter;

import java.util.List;

/**
 * @author delin10
 * @since 2019/10/31
 **/
public class BloomFilterMatcher extends AbstractForwardMatcher {

    private IBloomFilter<String> bloomFilter;

    public BloomFilterMatcher(MatchLevel level, List<String> lib, IBloomFilter<String> filter) {
        super(level);
        this.bloomFilter = filter;
        initLibrary(lib);
    }

    @Override
    protected Boolean canEnd(StringBuilder builder) {
        return bloomFilter.exist(builder.toString());
    }

    private void initLibrary(List<String> words){
        words.forEach(bloomFilter::insert);
    }
}
