fun main() {
    print("Digite o lado do quadrado: ")
    val lado = readln().toDouble()

    print("Digite o outro lado do quadrado: ")
    val altura = readln().toDouble()

    val area = lado * lado

    println("A área do quadrado é: $area")
}