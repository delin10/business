package nil.ed.business.sensitive.type;

/**
 * @author delin10
 * @since 2019/10/23
 **/
public class TireTree {
    private TireTreeNode root;

    public TireTree() {
        this.root = new TireTreeNode(16);
    }

    /**
     * 插入一个词汇
     *
     * @param word 词汇
     * @return 终点节点
     */
    public TireTreeNode insert(String word) {
        return helpInsert(root, word, 0);
    }

    /**
     * 搜索节点
     *
     * @param word 源词汇
     * @param start 词汇起始位置
     * @param end 词汇匹配终止位置
     * @return 终止节点
     */
    public TireTreeFindResult find(String word, int start, int end) {
        TireTreeFindResult findResult = new TireTreeFindResult();
        findResult.setMatchTextStart(start);
        findResult.setMatchTextEnd(start);
        helpFind(getChildNode(root, word.charAt(start)), word, start, end, findResult);
        return findResult;
    }

    /**
     * 帮助插入词汇
     *
     * @param node 当前操作节点
     * @param word 词汇
     * @param i 当前操作词汇字符位置
     * @return 结点
     */
    private TireTreeNode helpInsert(TireTreeNode node, String word, int i) {
        TireTreeNode childNode = getChildNode(node, word.charAt(i));
        if (childNode == null) {
            childNode = new TireTreeNode(16);
            childNode.setCharacter(word.charAt(i));
            node.getChildren().put(String.valueOf(word.charAt(i)), childNode);
        }
        childNode.setFrequency(node.getFrequency() + 1);

        if (i == word.length() - 1) {
            childNode.setCanEnd(true);
            return node;
        }
        return helpInsert(childNode, word, i + 1);
    }

    /**
     * 帮助查找
     *
     * @param node 结点
     * @param word 字词
     * @param start 词汇起始位置
     * @param end 结束位置
     * @param findResult 查找结果
     */
    private void helpFind(TireTreeNode node, String word, int start, int end, TireTreeFindResult findResult) {
        TireTreeNode curNode = node;
        int k = start;
        while (curNode != null) {
            if (curNode.isCanEnd()) {
                findResult.setMatchTextEnd(k + 1);
                findResult.setEndTireTreeNode(curNode);
            }

            if (k >= end - 1) {
                return;
            }

            k += 1;
            curNode = getChildNode(curNode, word.charAt(k));
        }

    }

    private TireTreeNode getChildNode(TireTreeNode parent, char ch){
        return parent.getChildren().get(String.valueOf(ch));
    }
}