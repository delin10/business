package nil.ed.business.sensitive.matcher;

/**
 * @author delin10
 * @since 2019/10/28
 **/
public interface MatchText {
    CharSequence wordAt(int i);

    int count();

    CharSequence regionWordString(int start, int end);
}
