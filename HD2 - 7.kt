fun main() {
    val todosOsNumeros = mutableListOf<Double>()
    var somaDosMenores = 0.0

    for (i in 1..6) {
        print("Digite o ${i}º número: ")
        val valor = readln().toDouble()

        todosOsNumeros.add(valor)

        if (valor < 72) {
            somaDosMenores += valor
        }
    }

    println("\n--- RELATÓRIO ---")
    println("Todos os valores informados: $todosOsNumeros")
    println("Soma dos valores inferiores a 72: $somaDosMenores")
}