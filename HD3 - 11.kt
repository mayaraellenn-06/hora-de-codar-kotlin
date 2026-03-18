fun main(args: Array<String>) {
    print("Deseja ver as tabuadas de 1 até quanto? ")
    val n = readln().toInt()

    for (i in 1..n) {
        println("\n--- TABUADA DO $i ---")

        for (j in 1..10) {
            val resultado = i * j
            println("$i x $j = $resultado")
        }
    }

    println("\nFim do processamento.")
}