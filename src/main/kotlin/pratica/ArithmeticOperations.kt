package pratica

class ArithmeticOperations(val x: Int, val y: Int) {

    fun addition() = x + y

    fun subtraction() = x - y

    fun multiplication() = x * y

    fun division() = require(y != 0) { "Division by zero is not allowed." }.let { x / y }
}