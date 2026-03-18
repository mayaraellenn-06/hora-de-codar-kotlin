fun main(args: Array<String>) {
    var dentroDoIntervalo = 0
    var foraDoIntervalo = 0

    println("--- Analisador de Intervalo (24 a 42) ---")

    for (i in 1..10) {
        print("Digite o ${i}º valor: ")
        val valor = readln().toInt()

        if (valor in 24..42) {
            dentroDoIntervalo++
        } else {
            foraDoIntervalo++
        }
    }

    println("\n--- RESULTADO FINAL ---")
    println("Valores entre 24 e 42: $dentroDoIntervalo")
    println("Valores fora do intervalo: $foraDoIntervalo")
}