package org.multilinguals.example.infrastructure.data;


import java.io.Serializable;
import java.util.Objects;

public class Tuple2<T1, T2> implements Serializable {
    private final T1 t1;

    private final T2 t2;

    public Tuple2(T1 t1, T2 t2) {
        this.t1 = Objects.requireNonNull(t1, "t1");
        this.t2 = Objects.requireNonNull(t2, "t2");
    }

    public T1 getT1() {
        return t1;
    }

    public T2 getT2() {
        return t2;
    }
}
