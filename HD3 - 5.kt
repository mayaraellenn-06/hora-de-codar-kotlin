fun main(args: Array<String>) {
    print("Digite o primeiro número (menor): ")
    val inicio = readln().toInt()

    print("Digite o segundo número (maior): ")
    val fim = readln().toInt()

    val intervalo = inicio..fim

    val soma = intervalo.sum().toDouble()
    val quantidade = intervalo.count()
    val media = soma / quantidade

    println("\n--- RESULTADOS ---")
    println("Números no intervalo: $intervalo")
    println("Soma total: $soma")
    println("Quantidade de números: $quantidade")
    println("Média aritmética: $media")
}