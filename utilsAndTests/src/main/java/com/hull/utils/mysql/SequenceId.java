package com.hull.utils.mysql;

/**
 * Created by hull on 2017/4/5.
 *
 * 高并发下 mysql sequence 解决方案
 * https://my.oschina.net/sub/blog/115758
 */
public class SequenceId {
    public static final long NOT_FOUND = 0;
    private static final long STEP = 100;
    private final SequenceIdProvider provider;
    private final String name;
    private final long beginValue;
    private long value;

    protected SequenceId(SequenceIdProvider provider, String name, long beginValue) {
        this.provider = provider;
        this.name = name;
        this.beginValue = beginValue;
        this.value = -1;

        if (beginValue <= 0) {
            throw new IllegalArgumentException("begin value must be great than zero.");
        }
    }

    public String getName() {
        return name;
    }

    public synchronized long nextVal() {
        if (value < 0) {
            value = provider.load(name);
            if (value <= NOT_FOUND) {
                value = beginValue - 1;
            }
            provider.store(name, value + STEP);
        }

        value++;

        if (value % STEP == 0) {
            provider.store(name, value + STEP);
        }

        return value;
    }
}
