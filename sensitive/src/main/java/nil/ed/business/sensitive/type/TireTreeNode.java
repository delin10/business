package nil.ed.business.sensitive.type;

import java.util.HashMap;
import java.util.Map;

/**
 * @author delin10
 * @since 2019/10/23
 **/

public class TireTreeNode {
    private char character;
    private int frequency;
    private boolean canEnd;
    private Map<String, TireTreeNode> children;

    public TireTreeNode(int initCapacity){
        this.children = new HashMap<>(initCapacity, 1);
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public boolean isCanEnd() {
        return canEnd;
    }

    public void setCanEnd(boolean canEnd) {
        this.canEnd = canEnd;
    }

    public Map<String, TireTreeNode> getChildren() {
        return children;
    }
}
