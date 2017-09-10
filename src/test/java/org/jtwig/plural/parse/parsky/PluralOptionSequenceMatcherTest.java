package org.jtwig.plural.parse.parsky;

import org.jtwig.plural.model.PluralOption;
import org.junit.Test;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;
import org.parsky.sequence.model.tree.ContentNode;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PluralOptionSequenceMatcherTest {
    private PluralOptionSequenceMatcher underTest = new PluralOptionSequenceMatcher();

    @Test
    public void noInterval() throws Exception {
        PluralOption pluralOption = resultOf(underTest.matches(request("test")));

        boolean result = pluralOption.apply(1);

        assertThat(result, is(true));
        assertThat(pluralOption.getMessage(), is("test"));
    }

    @Test
    public void withInterval() throws Exception {
        PluralOption pluralOption = resultOf(underTest.matches(request("[1, 3[ test")));

        boolean result = pluralOption.apply(1);

        assertThat(result, is(true));
        assertThat(pluralOption.getMessage(), is("test"));
    }

    @Test
    public void withIntervalOutside() throws Exception {
        PluralOption pluralOption = resultOf(underTest.matches(request("[1, 3[ test")));

        boolean result = pluralOption.apply(0);

        assertThat(result, is(false));
        assertThat(pluralOption.getMessage(), is("test"));
    }

    @Test
    public void withSpecificValue() throws Exception {
        PluralOption pluralOption = resultOf(underTest.matches(request("{0} test")));

        boolean result = pluralOption.apply(0);

        assertThat(result, is(true));
        assertThat(pluralOption.getMessage(), is("test"));
    }

    private PluralOption resultOf(SequenceMatcherResult matcherResult) {
        return (PluralOption) ((ContentNode) matcherResult.getMatchResult().getNode()).getContent();
    }

    private SequenceMatcherRequest request(String input) {
        return new SequenceMatcherRequest(input.toCharArray(), 0);
    }
}