fun main() {
    print("Digite a diagonal maior: ")
    val diagonalMaior = readln().toDouble()

    print("Digite a diagonal menor: ")
    val diagonalMenor = readln().toDouble()

    val area = (diagonalMaior * diagonalMenor) / 2

    println("A área do Losango é: $area")
}