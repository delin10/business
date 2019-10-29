package nil.ed.business.sensitive;


import nil.ed.business.sensitive.matcher.MatchResult;
import nil.ed.business.sensitive.matcher.MatchText;
import nil.ed.business.sensitive.matcher.SegmentMatchText;
import nil.ed.business.sensitive.matcher.TextMatcher;
import nil.ed.business.sensitive.seg.Segmenter;
import nil.ed.business.sensitive.util.Arrayx;

import java.util.List;

/**
 * @author delin10
 * @since 2019/10/28
 **/
public class SegmentSensitiveFilter extends AbstractSensitiveFilter {
    private Segmenter segmenter;

    public SegmentSensitiveFilter(TextMatcher matcher, Segmenter segmenter) {
        super(matcher);
        this.segmenter = segmenter;
    }

    public SegmentSensitiveFilter(String replaceMask, TextMatcher matcher, Segmenter segmenter) {
        super(replaceMask, matcher);
        this.segmenter = segmenter;
    }

    @Override
    protected MatchText getMatchText(String sourceText) {
        List<String> words = segmenter.seg(sourceText);
        System.out.println(words);
        return new SegmentMatchText(sourceText, words, true);
    }
}
