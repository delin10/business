package nil.ed.business.sensitive.hash;

import nil.ed.business.sensitive.hash.impl.GuavaBloomFilterImpl;
import nil.ed.business.sensitive.hash.impl.RedisBloomFilterBuilder;
import org.junit.Test;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * 最低错误率在0.03左右
 */
public class IBloomFilterTest {

    @Test
    public void testGuavaFilter(){
        IBloomFilter<String> bloomFilter = new GuavaBloomFilterImpl<>(GuavaBloomFilterImpl.STRING_FUNNEL, 10000);
        test(bloomFilter);
    }

    @Test
    public void testRedisFilter(){
        IBloomFilter<String> bloomFilter = new RedisBloomFilterBuilder().setBloomKey("testKey2")
                .setHost("127.0.0.1")
                .setPort(6379)
                .setErrorRate(0.001)
                .setInitialCapacity(10000)
                .build();
        test(bloomFilter);
    }

    public void test(IBloomFilter<String> filter){
        String prefix = "test";
        int count = 10000;
        int error = 0;
        IntStream.range(0, 10000)
                .forEach(i -> filter.insert(prefix + i));

        Random random = new Random();
        for (int i = 0; i < count ; ++i){
            int j = random.nextInt();
            if (filter.exist("test" + j) && (j >= count || j < 0)){
                System.out.println("error:" + j);
                error ++;
            }
        }

        System.out.println("错误数：" + error + ",错误率：" + (1.0 * error / count));
    }

}