/*
    The MIT License (MIT)

    FTL-Lang Copyright (c) 2018-2021 Tom Needham

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


plugins {
    java
    kotlin("jvm") version "1.4.32"
    id("com.github.johnrengelman.shadow") version "6.1.0"
    id("com.github.kt3k.coveralls") version "2.12.0"
    id("net.saliman.cobertura") version "4.0.0"

}

group = "com.thomas.needham.ftl"
version = "0.1"


repositories {
    mavenCentral()
}

tasks.coveralls {
    dependsOn.add("check")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation(kotlin("stdlib"))
    compileOnly(group="com.hackoeur", name="jglm", version="1.0.0")
    testCompileOnly(group="io.kotest", name="kotest-runner-junit5", version= "4.4.3")
    testRuntimeOnly(group="org.slf4j", name="slf4j-nop", version="1.7.12")
}
