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
 * This class represents a defined class within the compiler
 * @author Thomas Needham
 */
class ClassSymbol<Type> : Symbol<Type> {
	/**
	 * Unique identifier to represent this symbol
	 */
	override var id: UUID

	/**
	 * The name of this symbol
	 */
	override val name: String

	/**
	 * the value of this symbol
	 */
	override var value: Type?

	/**
	 * The scope in which this symbol is defined
	 */
	override val scope: Scope

	/**
	 * Whether this symbol is read only
	 */
	override val readOnly : Boolean

	/**
	 * Whether this symbol is initialised
	 */
	override var initialised: Boolean

	/**
	 * MutableList of inner classes defined within this class
	 */
	val innerClasses : MutableList<ClassSymbol<*>>

	/**
	 * MutableList of fields defined in this class
	 */
	val fields : MutableList<VariableSymbol<*>>

	/**
	 * MutableList of functions defined in this class
	 */
	val functions : MutableList<FunctionSymbol<*>>

	/**
	 * Constructor for class symbol
	 * @param id the unique identifier of the symbol
	 * @param name the name of this symbol
	 * @param value the value of this symbol
	 * @param readOnly whether this symbol is read only
	 * @param scope the scope tthat this symbol is defined in
	 * @param initialised whether this symbol has been initialised defaults to false
	 * @param innerClasses MutableList of inner classes declared within this class
	 * @param fields MutableList of global fields defined within this class
	 * @param functions MutableList of functions defined in this class
	 */
	constructor(id: UUID, name: String, value: Type?, readOnly: Boolean, scope: Scope, initialised: Boolean = false,
	            innerClasses: MutableList<ClassSymbol<*>> = mutableListOf(), fields: MutableList<VariableSymbol<*>> = mutableListOf(),
	            functions: MutableList<FunctionSymbol<*>> = mutableListOf()){
		this.id = id
		this.name = name
		this.value = value
		this.readOnly = readOnly
		this.scope = scope
		this.initialised = initialised
		this.innerClasses = innerClasses
		this.fields = fields
		this.functions = functions
	}

	/**
	 * Function to generate unique symbol id
	 * @param table the symbol table that this symbol is in
	 * @return A unique ID to represent a symbol
	 */
	override fun generateID(table: SymbolTable): UUID {
		val symbols : MutableList<Symbol<*>> = table.symbols.filter { e -> e is ClassSymbol<*> }.toMutableList()
		var found : Boolean = false
		var id : UUID = UUID.fromString("0".repeat(128))
		while (!found){
			id = UUID.randomUUID()
			for(sym: Symbol<*> in symbols){
				if(sym.id == id)
					found = true
			}
		}
		return id
	}

	/**
	 * Function to compare if two symbols are equal
	 * @param other the symbol to compare this symbol with
	 * @return Whether both symbols are equal
	 */
	override fun equals(other: Any?): Boolean {
		if(other !is ClassSymbol<*>?)
			return false
		if (other === null)
			return true
		return this.id == other.id && this.name == other.name &&
			this.value == other.value && this.scope == other.scope &&
			this.readOnly == other.readOnly && this.initialised == other.initialised &&
			this.innerClasses.containsAll(other.innerClasses) && this.fields.containsAll(other.fields) &&
			this.functions.containsAll(other.functions)

	}
	/**
	 * Generates a hashCode for this symbol
	 * @return the generated hash code
	 */
	override fun hashCode(): Int {
		var result = super.hashCode()
		result = 31 * result + id.hashCode()
		result = 31 * result + name.hashCode()
		result = 31 * result + (value?.hashCode() ?: 0)
		result = 31 * result + scope.hashCode()
		result = 31 * result + readOnly.hashCode()
		result = 31 * result + initialised.hashCode()
		result = 31 * result + innerClasses.hashCode()
		result = 31 * result + fields.hashCode()
		result = 31 * result + functions.hashCode()
		return result
	}
}