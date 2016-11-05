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

import java.lang.reflect.Type
import java.lang.reflect.TypeVariable

/**
 * This class represents a symbols type
 * @param T the Type that this info should represent
 */
class TypeInfo<T : Class<*>> : Type {

	/**
	 * The Type that this info should represent
	 */
	val classinfo : T

	/**
	 * The name of this type
	 */
	val name : String

	/**
	 * List of superclasses and superinterfaces of this type
	 */
	val superTypes : List<Class<out Any?>>?

	/**
	 * List of any enclosing types defined within this type
	 */
	val enclosingTypes : List<Class<out Any?>>?

	/**
	 * List of any generic type arguments passed to this type
	 */
	val typeArguemnts : List<TypeVariable<out Class<out Any?>?>?>?

	/**
	 * C
	 */
	constructor(classinfo: T){
		this.classinfo = classinfo
		this.name = classinfo.name
		this.superTypes = listOf(classinfo.superclass, *classinfo.interfaces)
		this.enclosingTypes = classinfo.declaredClasses.asList()
		this.typeArguemnts = classinfo.typeParameters.asList()

	}
}