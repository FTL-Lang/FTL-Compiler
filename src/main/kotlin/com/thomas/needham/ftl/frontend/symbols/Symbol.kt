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
package com.thomas.needham.ftl.frontend.symbols

import java.util.*

/**
 * Base Class for all Symbol types
 * @author Thomas Needham
 */
abstract class Symbol<Type> {
	/**
	 * Unique identifier to represent this symbol
	 */
	abstract val id : UUID

	/**
	 * The name of this symbol
	 */
	abstract val name : String

	/**
	 * the value of this symbol
	 */
	abstract var value : Type?

	/**
	 * The scope in which this symbol is defined
	 */

	abstract val scope : Scope

	/**
	 * Whether this symbol is read only
	 */
	abstract val readOnly : Boolean

	/**
	 * Whether this symbol is initialised
	 */
	abstract var initialised : Boolean

	/**
	 * Function to generate unique symbol id
	 * @param table the symbol table that this symbol is in
	 * @return A unique ID to represent a symbol
	 */
	abstract fun generateID(table: SymbolTable) : UUID

	/**
	 * Function to determine whether this symbol is already defined
	 * @param table the symbol table to search
	 * @param symbol the symbol to find
	 * @return whether the symbol was found
	 */
	fun isAlreadyDefined(table: SymbolTable, symbol: Symbol<*>) : Boolean
	{
		var s : Scope? = scope
		while(s != null){
			val symbolsinScope : List<Symbol<*>> = table.symbols.filter { e -> e.scope == scope }
			if(symbolsinScope.any { e -> e.name == symbol.name })
				return true
			s = s.parent
		}
		return false
	}
	/**
	 * Function to compare if two symbols are equal
	 * @param other the symbol to compare this symbol with
	 * @return Whether both symbols are equal
	 */
	override fun equals(other: Any?): Boolean {
		if(other !is Symbol<*>?)
			return false
		if (other === null)
			return true
		return this.id == other.id && this.name == other.name &&
			this.value == other.value && this.scope == other.scope &&
			this.readOnly == other.readOnly && this.initialised == other.initialised

	}

	/**
	 * Generates a hashCode for this symbol
	 * @return the generated hash code
	 */
	override fun hashCode(): Int{
		var result = id.hashCode()
		result = 31 * result + name.hashCode()
		result = 31 * result + (value?.hashCode() ?: 0)
		result = 31 * result + scope.hashCode()
		result = 31 * result + readOnly.hashCode()
		result = 31 * result + initialised.hashCode()
		return result
	}
}