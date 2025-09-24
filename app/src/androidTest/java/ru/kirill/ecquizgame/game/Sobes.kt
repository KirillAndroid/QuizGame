package ru.kirill.ecquizgame.game

import android.R

open class O {
    private val a = 3
    protected open val b = 5
    internal open val c = 33
    val d = 4

    protected class Nested {
        public val e: Int = 5
    }

}

class Subclass : O() {
    // a is not visible
    // b is visible
    // c is visible
    // d is visible

    override val b = 10
    override val c = 44
    // e is visible

}

class Unrelated(o: O) {
    val x = o.c
    val y = o.d
}

data class Person( val firstName: String,  val lastName: String)
private val string: String = "first"
fun main() {
    val subclass = Subclass()
    subclass.c
    subclass.d
    subclass
    val unrelated = Unrelated(subclass)
    val s = "first"
    when(string) {
        "first" -> print("asf")
        "second" -> print("asf")
//        else -> IllegalStateException()
    }
    println()
    val student = Student("test", 1)
    print(student)
    print(Person("kirill", "karnaukhov").fullName())

}

fun Person.fullName() = "${this.firstName} + ${this.lastName}"

class Student(var name: String, var id: Int) {

    init {
        println("Student initialized with name = $name")
    }

    constructor(name: String, id: Int, third: Boolean) : this(name, id)
}
