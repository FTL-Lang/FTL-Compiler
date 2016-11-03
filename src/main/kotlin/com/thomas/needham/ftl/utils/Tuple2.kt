/*
The MIT License (MIT)

Copyright (c) 2016 Tom Needham

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package com.thomas.needham.ftl.utils

/**
 * This class represents a Tuple with two items
 * @param X The Type of the first item
 * @param Y The Type of the second item
 */
open class Tuple2<X, Y> {
	/**
	 * The First Item
	 */
	var valueX: X? = null

	/**
	 * The Second Item
	 */
	var valueY: Y? = null

	/**
	 * Constructor for Tuple2
	 * @param x the first item
	 * @param y the second item
	 */
	constructor(x: X?, y: Y?) {
		this.valueX = x
		this.valueY = y
	}

	/**
	 * Conpanion object to hold static data
	 */
	companion object Blank {
		/**
		 * A Blank Tuple2
		 */
		@JvmStatic val BLANK = Tuple2<Any?, Any?>(null, null)
	}
}