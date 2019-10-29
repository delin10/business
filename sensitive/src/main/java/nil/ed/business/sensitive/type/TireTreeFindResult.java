package nil.ed.business.sensitive.type;

/**
 * @author delin10
 * @since 2019/10/23
 **/
public class TireTreeFindResult {
    /**
     * 源文本
     */
    private String sourceText;
    /**
     * 最长匹配文本起始位置
     */
    private int matchTextStart;
    /**
     * 最长匹配文本结束位置
     */
    private int matchTextEnd;

    /**
     * 终止结点
     */
    private TireTreeNode endTireTreeNode;

    public String getSourceText() {
        return sourceText;
    }

    public void setSourceText(String sourceText) {
        this.sourceText = sourceText;
    }

    public int getMatchTextStart() {
        return matchTextStart;
    }

    public void setMatchTextStart(int matchTextStart) {
        this.matchTextStart = matchTextStart;
    }

    public int getMatchTextEnd() {
        return matchTextEnd;
    }

    public void setMatchTextEnd(int matchTextEnd) {
        this.matchTextEnd = matchTextEnd;
    }

    public TireTreeNode getEndTireTreeNode() {
        return endTireTreeNode;
    }

    public void setEndTireTreeNode(TireTreeNode endTireTreeNode) {
        this.endTireTreeNode = endTireTreeNode;
    }

    public boolean hasFind(){
        return matchCharCount() > 0;
    }

    public int matchCharCount(){
        return matchTextEnd - matchTextStart;
    }
}
