fun main(args: Array<String>) {
    var somaDasNotas = 0.0

    println("--- Sistema de Notas (6 Avaliações) ---")

    for (i in 1..6) {
        var notaValida: Double

        while (true) {
            print("Digite a ${i}ª nota (0 a 10): ")
            val entrada = readln().toDouble()

            if (entrada >= 0 && entrada <= 10) {
                notaValida = entrada
                break
            } else {
                println("Erro! A nota deve estar entre 0 e 10. Tente novamente.")
            }
        }

        somaDasNotas += notaValida
    }
    
    val media = somaDasNotas / 6
    println("\n--- RESULTADO FINAL ---")
    println("Média das 6 avaliações: ${"%.2f".format(media)}")
}