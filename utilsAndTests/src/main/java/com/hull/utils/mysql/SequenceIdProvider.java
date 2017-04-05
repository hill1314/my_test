package com.hull.utils.mysql;

/**
 * Created by hull on 2017/4/5.
 */
public interface SequenceIdProvider {
    public SequenceId create(String name);

    public SequenceId create(String name, long begin);

    public long load(String name);

    public void store(String name, long value);
}
