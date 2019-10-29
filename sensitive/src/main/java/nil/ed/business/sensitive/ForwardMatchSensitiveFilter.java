package nil.ed.business.sensitive;

import nil.ed.business.sensitive.matcher.MatchResult;
import nil.ed.business.sensitive.matcher.MatchText;
import nil.ed.business.sensitive.matcher.StringMatchText;
import nil.ed.business.sensitive.matcher.TextMatcher;
import nil.ed.business.sensitive.util.Arrayx;

/**
 * @author delin10
 * @since 2019/10/28
 **/
public class ForwardMatchSensitiveFilter extends AbstractSensitiveFilter {

    public ForwardMatchSensitiveFilter(TextMatcher matcher) {
        this("*", matcher);
    }

    public ForwardMatchSensitiveFilter(String replaceMask, TextMatcher matcher) {
        super(replaceMask, matcher);
    }

    @Override
    protected MatchText getMatchText(String sourceText) {
        return new StringMatchText(sourceText);
    }
}
