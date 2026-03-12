fun main() {
    print("Digite o primeiro número: ")
    val num1 = readln().toDouble()

    print("Digite o segundo número: ")
    val num2 = readln().toDouble()

    if (num1 > num2) {
        println("O maior número é: $num1")
    } else if (num2 > num1) {
        println("O maior número é: $num2")
    } else {
        println("Os números são iguais.")
    }
}