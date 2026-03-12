fun main() {
    print("Digite um número: ")
    val numero = readln().toDouble()

    if (numero > 0) {
        println("O valor informado é positivo.")
    } else if (numero < 0) {
        println("O valor informado é negativo.")
    } else {
        println("O valor informado é zero.")
    }
}0