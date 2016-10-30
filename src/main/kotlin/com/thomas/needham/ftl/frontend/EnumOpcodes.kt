/*
    The MIT License (MIT)

    FTL-Compiler Copyright (c) 2016 thoma

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
package com.thomas.needham.ftl.frontend

/**
 * Container class for Opcodes Enum
 */
public class EnumOpcodes {
    /**
     * Enum containing a list of JVM Opcodes
     */
    enum class Opcodes(val value: Int) {
        aaload(0x32),
        aastore(0x53),
        aconst_null(0x01),
        aload(0x19),
        aload_0(0x2a),
        aload_1(0x2b),
        aload_2(0x2c),
        aload_3(0x2d),
        anewarray(0xbd),
        areturn(0xb0),
        arraylength(0xbe),
        astore(0x3a),
        astore_0(0x4b),
        astore_1(0x4c),
        astore_2(0x4d),
        astore_3(0x4e),
        athrow(0xbf),
        baload(0x33),
        bastore(0x54),
        bipush(0x10),
        breakpoint(0xca),
        caload(0x34),
        castore(0x55),
        checkcast(0xc0),
        d2f(0x90),
        d2i(0x8e),
        d2l(0x8f),
        dadd(0x63),
        daload(0x31),
        dastore(0x52),
        dcmpg(0x98),
        dcmpl(0x97),
        dconst_0(0x0e),
        dconst_1(0x0f),
        ddiv(0x6f),
        dload(0x18),
        dload_0(0x26),
        dload_1(0x27),
        dload_2(0x28),
        dload_3(0x29),
        dmul(0x6b),
        dneg(0x77),
        drem(0x73),
        dreturn(0xaf),
        dstore(0x39),
        dstore_0(0x47),
        dstore_1(0x48),
        dstore_2(0x49),
        dstore_3(0x4a),
        dsub(0x67),
        dup(0x59),
        dup_x1(0x5a),
        dup_x2(0x5b),
        dup2(0x5c),
        dup2_x1(0x5d),
        dup2_x2(0x5e),
        f2d(0x8d),
        f2i(0x8b),
        f2l(0x8c),
        fadd(0x62),
        faload(0x30),
        fastore(0x51),
        fcmpg(0x96),
        fcmpl(0x95),
        fconst_0(0x0b),
        fconst_1(0x0c),
        fconst_2(0x0d),
        fdiv(0x6e),
        fload(0x17),
        fload_0(0x22),
        fload_1(0x23),
        fload_2(0x24),
        fload_3(0x25),
        fmul(0x6a),
        fneg(0x76),
        frem(0x72),
        freturn(0xae),
        fstore(0x38),
        fstore_0(0x43),
        fstore_1(0x44),
        fstore_2(0x45),
        fstore_3(0x46),
        fsub(0x66),
        getfield(0xb4),
        getstatic(0xb2),
        goto(0xa7),
        goto_w(0xc8),
        i2b(0x91),
        i2c(0x92),
        i2d(0x87),
        i2f(0x86),
        i2l(0x85),
        i2s(0x93),
        iadd(0x60),
        iaload(0x2e),
        iand(0x7e),
        iastore(0x4f),
        iconst_m1(0x02),
        iconst_0(0x03),
        iconst_1(0x04),
        iconst_2(0x05),
        iconst_3(0x06),
        iconst_4(0x07),
        iconst_5(0x08),
        idiv(0x6c),
        if_acmpeq(0xa5),
        if_acmpne(0xa6),
        if_icmpeq(0x9f),
        if_icmpge(0xa2),
        if_icmpgt(0xa3),
        if_icmple(0xa4),
        if_icmplt(0xa1),
        if_icmpne(0xa0),
        ifeq(0x99),
        ifge(0x9c),
        ifgt(0x9d),
        ifle(0x9e),
        iflt(0x9b),
        ifne(0x9a),
        ifnonnull(0xc7),
        ifnull(0xc6),
        iinc(0x84),
        iload(0x15),
        iload_0(0x1a),
        iload_1(0x1b),
        iload_2(0x1c),
        iload_3(0x1d),
        impdep1(0xfe),
        impdep2(0xff),
        imul(0x68),
        ineg(0x74),
        instanceof(0xc1),
        invokedynamic(0xba),
        invokeinterface(0xb9),
        invokespecial(0xb7),
        invokestatic(0xb8),
        invokevirtual(0xb6),
        ior(0x80),
        irem(0x70),
        ireturn(0xac),
        ishl(0x78),
        ishr(0x7a),
        istore(0x36),
        istore_0(0x3b),
        istore_1(0x3c),
        istore_2(0x3d),
        istore_3(0x3e),
        isub(0x64),
        iushr(0x7c),
        ixor(0x82),
        jsr(0xa8),
        jsr_w(0xc9),
        l2d(0x8a),
        l2f(0x89),
        l2i(0x88),
        ladd(0x61),
        laload(0x2f),
        land(0x7f),
        lastore(0x50),
        lcmp(0x94),
        lconst_0(0x09),
        lconst_1(0x0a),
        ldc(0x12),
        ldc_w(0x13),
        ldc2_w(0x14),
        ldiv(0x6d),
        lload(0x16),
        lload_0(0x1e),
        lload_1(0x1f),
        lload_2(0x20),
        lload_3(0x21),
        lmul(0x69),
        lneg(0x75),
        lookupswitch(0xab),
        lor(0x81),
        lrem(0x71),
        lreturn(0xad),
        lshl(0x79),
        lshr(0x7b),
        lstore(0x37),
        lstore_0(0x3f),
        lstore_1(0x40),
        lstore_2(0x41),
        lstore_3(0x42),
        lsub(0x65),
        lushr(0x7d),
        lxor(0x83),
        monitorenter(0xc2),
        monitorexit(0xc3),
        multianewarray(0xc5),
        new(0xbb),
        newarray(0xbc),
        nop(0x00),
        pop(0x57),
        pop2(0x58),
        putfield(0xb5),
        putstatic(0xb3),
        ret(0xa9),
        _return(0xb1),
        saload(0x35),
        sastore(0x56),
        sipush(0x11),
        swap(0x5f),
        tableswitch(0xaa),
        wide(0xc4)
    }
}