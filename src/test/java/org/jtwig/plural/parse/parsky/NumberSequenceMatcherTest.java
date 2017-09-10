package org.jtwig.plural.parse.parsky;

import org.jtwig.plural.parse.parsky.number.NumberSequenceMatcher;
import org.junit.Test;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;
import org.parsky.sequence.model.tree.ContentNode;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NumberSequenceMatcherTest {
    NumberSequenceMatcher underTest = new NumberSequenceMatcher();

    @Test
    public void numberMatcher() throws Exception {
        assertThat(resultOf(underTest.matches(request("-123"))), is(-123));
        assertThat(resultOf(underTest.matches(request("123"))), is(123));
        assertThat(resultOf(underTest.matches(request("-1"))), is(-1));
        assertThat(resultOf(underTest.matches(request("0"))), is(0));
    }

    private Integer resultOf(SequenceMatcherResult matcherResult) {
        return (Integer) ((ContentNode) matcherResult.getMatchResult().getNode()).getContent();
    }

    private SequenceMatcherRequest request(String input) {
        return new SequenceMatcherRequest(input.toCharArray(), 0);
    }
}