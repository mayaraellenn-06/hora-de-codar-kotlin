fun main() {
    print("Digite a base do triângulo: ")
    val base = readln().toDouble()

    print("Digite a altura do triângulo: ")
    val altura = readln().toDouble()

    val area = (base * altura) / 2

    println("A área do triângulo é: $area")
}