fun main() {
    print("Digite sua altura (ex: 1.70): ")
    val h = readln().toDouble()

    println("Informe o gênero:")
    println("1: Feminino")
    println("2: Masculino")
    print("Escolha (1 ou 2): ")
    val genero = readln().toInt()

    var pesoIdeal = 0.0

    if (genero == 1) {
        pesoIdeal = (62.1 * h) - 44.7
        println("\nO peso ideal para uma mulher de $h m é: ${"%.2f".format(pesoIdeal)} kg")
    } else if (genero == 2) {
        pesoIdeal = (72.7 * h) - 58
        println("\nO peso ideal para um homem de $h m é: ${"%.2f".format(pesoIdeal)} kg")
    } else {
        println("\nOpção de gênero inválida!")
    }
}