package org.jtwig.plural.parse.parsky;

import org.hamcrest.core.Is;
import org.jtwig.plural.PluralOptions;
import org.junit.Test;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;
import org.parsky.sequence.model.tree.ContentNode;

import static org.junit.Assert.assertThat;

public class PluralOptionsSequenceMatcherTest {
    private PluralOptionsSequenceMatcher underTest = new PluralOptionsSequenceMatcher();

    @Test
    public void name() throws Exception {
        PluralOptions result = resultOf(underTest.matches(request("{0} test | ]Inf, 0[ test negative | ]0, Inf[ test positive")));

        assertThat(result.select(-1).get(), Is.is("test negative"));
        assertThat(result.select(0).get(), Is.is("test"));
        assertThat(result.select(1).get(), Is.is("test positive"));
    }



    private PluralOptions resultOf(SequenceMatcherResult matcherResult) {
        return (PluralOptions) ((ContentNode) matcherResult.getMatchResult().getNode()).getContent();
    }

    private SequenceMatcherRequest request(String input) {
        return new SequenceMatcherRequest(input.toCharArray(), 0);
    }
}