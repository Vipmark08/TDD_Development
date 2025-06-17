package com.example;

import java.util.Objects;

/** Ключ для HashMap курсов: пара валют (from, to). */
class Pair<A, B> {
    private final A first;
    private final B second;

    public Pair(A first, B second) {
        this.first  = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Pair)) return false;
        Pair<?,?> p = (Pair<?,?>) obj;
        return Objects.equals(first,  p.first)
                && Objects.equals(second, p.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
