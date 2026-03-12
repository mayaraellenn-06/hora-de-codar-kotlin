fun main() {
    val numeros = mutableListOf<Double>()

    for (i in 1..4) {
        print("Digite o ${i}º valor: ")
        val valor = readln().toDouble()
        numeros.add(valor)
    }
    val primeiro = numeros.first()
    val ultimo = numeros.last()
    val maior = numeros.maxOrNull()

    println("\n--- RESULTADOS ---")
    println("Primeiro valor informado: $primeiro")
    println("Último valor informado: $ultimo")
    println("O maior valor de todos: $maior")
}