package com.thread.finalobj;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.math.BigInteger;
import java.util.Arrays;

@Immutable
public class OneValueCache {
    private final BigInteger lastNumber;
    private final BigInteger[] lastFactors;


    public OneValueCache(BigInteger i, BigInteger[] factors) {
        this.lastNumber = i;
        this.lastFactors = Arrays.copyOf(factors,factors.length);
    }

    public BigInteger[] getFactors(BigInteger integer) {
        if (lastNumber ==  null || !lastNumber.equals(integer)) {
            return null;
        } else {
            return Arrays.copyOf(lastFactors, lastFactors.length);
        }

    }

}
