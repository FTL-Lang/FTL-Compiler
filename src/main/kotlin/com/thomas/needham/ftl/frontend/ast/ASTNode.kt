/*
    The MIT License (MIT)

    FTL-Lang Copyright (c) 2016 thoma

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
package com.thomas.needham.ftl.frontend.ast

import com.thomas.needham.ftl.utils.IReleasable

/**
 * Abstract class to represent a node of an AST (Abstract Syntax Tree)
 * @param Type the type of data associated with the AST Node
 */
abstract class ASTNode<Type> : Comparable<Type>, IReleasable<ASTNode<Type>> {
	/**
	 * The data associated with this AST Node
	 */
	abstract val data : Type?
	/**
	 * The left Child of this AST Node
	 */
	abstract val left : ASTNode<*>?
	/**
	 * The right Child of this AST Node
	 */
	abstract val right : ASTNode<*>?

	/**
	 * Function to visit this AST Node
	 * @param T the type of data associated with the AST Node
	 */
	abstract fun<T : ASTNode<*>> visit() : Unit

	/**
	 * Function to acceot this AST node as a child of another AST node
	 * @param T the type of data associated with the AST Node
	 * @param node the node of which this node will become a child
	 */
	abstract fun<T> accept(node: ASTNode<T>)
}
