package org.jtwig.plural.parse.parsky;

import com.google.common.base.Predicate;
import org.jtwig.plural.parse.parsky.interval.IntervalSequenceMatcher;
import org.junit.Test;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;
import org.parsky.sequence.model.tree.ContentNode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IntervalSequenceMatcherTest {
    private IntervalSequenceMatcher underTest = new IntervalSequenceMatcher();

    @Test
    public void inclusiveBoth() throws Exception {
        Predicate<Integer> result = predicate(underTest.matches(request("[1, 2]")));

        assertFalse(result.apply(0));
        assertTrue(result.apply(1));
        assertTrue(result.apply(2));
        assertFalse(result.apply(3));
    }

    @Test
    public void inclusiveLeft() throws Exception {
        Predicate<Integer> result = predicate(underTest.matches(request("[1, 2[")));

        assertFalse(result.apply(0));
        assertTrue(result.apply(1));
        assertFalse(result.apply(2));
        assertFalse(result.apply(3));
    }

    @Test
    public void inclusiveRight() throws Exception {
        Predicate<Integer> result = predicate(underTest.matches(request("]1, 2]")));

        assertFalse(result.apply(0));
        assertFalse(result.apply(1));
        assertTrue(result.apply(2));
        assertFalse(result.apply(3));
    }

    @Test
    public void infiniteLeft() throws Exception {
        Predicate<Integer> result = predicate(underTest.matches(request("] Inf, 2]")));

        assertTrue(result.apply(-100));
        assertTrue(result.apply(0));
        assertTrue(result.apply(1));
        assertTrue(result.apply(2));
        assertFalse(result.apply(3));

    }

    @Test
    public void infiniteRight() throws Exception {
        Predicate<Integer> result = predicate(underTest.matches(request("] 1, Inf  ]")));

        assertFalse(result.apply(0));
        assertFalse(result.apply(1));
        assertTrue(result.apply(2));
        assertTrue(result.apply(300));

    }

    private Predicate<Integer> predicate (SequenceMatcherResult result) {
        return (Predicate<Integer>) ((ContentNode) result.getMatchResult().getNode())
                .getContent();
    }

    private SequenceMatcherRequest request(String input) {
        return new SequenceMatcherRequest(input.toCharArray(), 0);
    }
}