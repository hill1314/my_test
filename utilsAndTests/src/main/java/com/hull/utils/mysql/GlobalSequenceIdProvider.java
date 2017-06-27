package com.hull.utils.mysql;

import javax.sql.DataSource;

/**
 * Created by hull on 2017/4/5.
 */
public class GlobalSequenceIdProvider {
    private static final SequenceId global;
    private static final SequenceId table1;
    private static final SequenceId table2;

    static {
        SequenceIdProvider provider = new JdbcSequenceIdProvider(getDataSource());
        global = provider.create("global");
        table1 = provider.create("table1");
        table2 = provider.create("table2", 1000);
    }

    public static long nextVal() {
        return global.nextVal();
    }

    public static long nextVal_table1() {
        return table1.nextVal();
    }

    public static long nextVal_table2() {
        return table2.nextVal();
    }

    private static DataSource getDataSource() {
        // TODO:
        return null;
    }
}
