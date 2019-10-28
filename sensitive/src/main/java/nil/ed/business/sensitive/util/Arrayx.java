package nil.ed.business.sensitive.util;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author delin10
 * @since 2019/10/23
 **/
public class Arrayx {
    public static String repeat(String target, int count){
        return IntStream.range(0, count).mapToObj(i -> target).collect(Collectors.joining());
    }
}
