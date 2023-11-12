package com.neu.dreambuilder.common.utils;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class TranCacheUtil {

    public static <A, B> A tryCache(
            Supplier<B> cacheGet,
            Consumer<B> cacheSet,
            Supplier<A> daoGet,
            Function<B, A> toA,
            Function<A, B> toB) {
        B b = cacheGet.get();
        if (b != null) return toA.apply(b);
        A a = daoGet.get();
        cacheSet.accept(toB.apply(a));
        return a;
    }

    public static <F, T> void update(
            Supplier<F> from,
            Consumer<T> to,
            Function<F, T> toB
    ) {
        to.accept(toB.apply(from.get()));
    }

}
