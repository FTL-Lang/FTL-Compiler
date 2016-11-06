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

/**
 * This class represents the compilers symbol table
 * @author Thomas Needham
 */
class SymbolTable {
	/**
	 * MutableList of symbols defined in the symbol table
	 */
	val symbols : MutableList<Symbol<*>>

	/**
	 * Default Constructor for SymbolTable
	 */
	constructor(){
		this.symbols = mutableListOf()
	}

	/**
	 * Constructor for symbol table to initialise with symbols
	 * @param symbols the symbols to add to the symbol table
	 */
	constructor(symbols: MutableList<Symbol<*>>){
		this.symbols = symbols
	}

	/**
	 * Function to get all symbols in the passed scope
	 * @param scope Scope to get all symbols from
	 * @return A MutableList of all symbols defined in the passed scope
	 */
	fun getAllSymbolsInScope(scope: Scope) : MutableList<Symbol<*>>{
		return symbols.filter { e -> e.scope == scope }.toMutableList()
	}

	/**
	 * Function to return whether a symbol is defined in the passed scope
	 * @param symbol Symbol to look for in the passed scope
	 * @param scope The scope to search for the current symbol
	 * @return Whether the passed symbol was defined in the passed scope
	 */
	fun isDefinedInScope(symbol: Symbol<*>, scope: Scope) : Boolean {
		var s : Scope? = scope
		while (s != null){
			val definedSymbols : MutableList<Symbol<*>> = getAllSymbolsInScope(s)
			if(definedSymbols.any { e -> e == symbol && e.scope == scope })
				return true
			s = s.parent
		}
		return false
	}
}