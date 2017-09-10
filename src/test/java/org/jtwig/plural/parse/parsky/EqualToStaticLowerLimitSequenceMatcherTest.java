package org.jtwig.plural.parse.parsky;

import com.google.common.base.Predicate;
import org.jtwig.plural.parse.parsky.interval.EqualToLimitSequenceMatcher;
import org.junit.Test;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;
import org.parsky.sequence.model.tree.ContentNode;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class EqualToStaticLowerLimitSequenceMatcherTest {
    private final EqualToLimitSequenceMatcher underTest = new EqualToLimitSequenceMatcher();

    @Test
    public void test() throws Exception {
        Predicate<Integer> result = predicate(underTest.matches(request("{2}")));


        assertThat(result.apply(2), is(true));
        assertThat(result.apply(3), is(false));
    }

    private Predicate<Integer> predicate (SequenceMatcherResult result) {
        return (Predicate<Integer>) ((ContentNode) result.getMatchResult().getNode())
                .getContent();
    }

    private SequenceMatcherRequest request(String input) {
        return new SequenceMatcherRequest(input.toCharArray(), 0);
    }
}