package org.jtwig.plural.model.limits;


import com.google.common.base.Predicate;

public class StaticUpperLimit implements Predicate<Integer> {
    private final boolean inclusive;
    private final int limit;

    public StaticUpperLimit(boolean inclusive, int limit) {
        this.inclusive = inclusive;
        this.limit = limit;
    }

    @Override
    public boolean apply(Integer input) {
        return input < limit || inclusive && input == limit;
    }

    public int getLimit() {
        return limit;
    }
}
