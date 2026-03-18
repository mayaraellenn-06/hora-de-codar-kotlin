fun main(args: Array<String>) {
    print("Digite um valor para N (maior que zero): ")
    val n = readln().toInt()

    println("\nImprimindo de 1 até $n:")

    for (i in 1..n) {
        print("$i ")
    }

    println()
}