fun main() {
    print("Digite o primeiro valor: ")
    val a = readln().toDouble()

    print("Digite o segundo valor: ")
    val b = readln().toDouble()

    print("Digite o terceiro valor: ")
    val c = readln().toDouble()

    if (a > b && a > c) {
        println("O maior valor é: $a")
    } else if (b > a && b > c) {
        println("O maior valor é: $b")
    } else {
        println("O maior valor é: $c")
    }
}