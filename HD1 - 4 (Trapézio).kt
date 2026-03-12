fun main() {
    print("Digite a base maior: ")
    val baseMaior = readln().toDouble()

    print("Digite a base menor: ")
    val baseMenor = readln().toDouble()

    print("Digite o valor da altura (h): ")
    val h = readln().toDouble()

    val area = ((baseMaior + baseMenor) * h) / 2

    println("A área do Trapézio é: $area")
}