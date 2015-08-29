package org.jtwig.plural.model;

import org.jtwig.plural.PluralOptions;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PluralOptionsTest {
    @Test
    public void simpleExample() throws Exception {
        PluralOptions options = PluralOptions.parse("{0} No | {1} One | Many");

        assertThat(options.select(0).get(), is("No"));
        assertThat(options.select(1).get(), is("One"));
        assertThat(options.select(2).get(), is("Many"));
    }

    @Test
    public void intervalExample() throws Exception {
        PluralOptions options = PluralOptions.parse("]1, 3] Many");

        assertThat(options.select(1).isPresent(), is(false));
        assertThat(options.select(2).isPresent(), is(true));
        assertThat(options.select(3).isPresent(), is(true));
        assertThat(options.select(4).isPresent(), is(false));
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidInterval() throws Exception {
        PluralOptions.parse("[3, 1] A");
    }
}