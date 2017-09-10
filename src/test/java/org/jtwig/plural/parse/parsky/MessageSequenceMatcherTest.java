package org.jtwig.plural.parse.parsky;

import org.junit.Test;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;
import org.parsky.sequence.model.tree.ContentNode;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MessageSequenceMatcherTest {
    MessageSequenceMatcher underTest = new MessageSequenceMatcher();

    @Test
    public void test() throws Exception {
        String result = result(underTest.matches(request("asd \\|asd asd | test")));

        assertThat(result, is("asd |asd asd"));
    }


    private <T> T result (SequenceMatcherResult result) {
        return (T) ((ContentNode) result.getMatchResult().getNode())
                .getContent();
    }

    private SequenceMatcherRequest request(String input) {
        return new SequenceMatcherRequest(input.toCharArray(), 0);
    }
}