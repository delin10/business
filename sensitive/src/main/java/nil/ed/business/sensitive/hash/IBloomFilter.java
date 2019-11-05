package nil.ed.business.sensitive.hash;

/**
 * @author delin10
 * @since 2019/11/5
 **/
public interface IBloomFilter<T> {
    /**
     * 判断是否存在
     *
     * @param e 文本
     * @return 是否存在
     */
    boolean exist(T e);

    /**
     * 插入元素
     *
     * @param e 元素
     * @return 是否插入成功
     */
    boolean insert(T e);
}
