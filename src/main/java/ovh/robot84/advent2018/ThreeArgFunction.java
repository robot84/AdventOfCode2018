/*
 * Copyright (c) 2010, 2013, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package ovh.robot84.advent2018;


@FunctionalInterface
public interface ThreeArgFunction<T, U, V, R> {

/**
 * Applies this function to the given argument.
 *
 * @param t the function argument
 * @return the function result
 */
R apply(T t, U u, V v);


}
