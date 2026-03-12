fun main() {
    val numeros = mutableListOf<Double>()
    var soma = 0.0

    for (i in 1..6) {
        print("Digite o ${i}º valor: ")
        val valor = readln().toDouble()

        numeros.add(valor)
        soma += valor
    }

    val media = soma / 6

    println("\n--- RESULTADOS ---")
    println("Números informados: $numeros") // Exibe a lista completa
    println("Média aritmética: $media")
}