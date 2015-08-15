package org.jtwig.plural.model.limits;

import com.google.common.base.Predicate;

public class InfiniteLimit implements Predicate<Integer> {
    @Override
    public boolean apply(Integer input) {
        return true;
    }
}
