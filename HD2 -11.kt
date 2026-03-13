fun main(args: Array<String>) {
    print("Digite o primeiro número inteiro: ")
    val n1 = readln().toInt()

    print("Digite o segundo número inteiro: ")
    val n2 = readln().toInt()

    println("\nEscolha a operação:")
    println("1. Adição (+)")
    println("2. Subtração (-)")
    println("3. Divisão (/)")
    println("4. Multiplicação (*)")
    print("Operação (1-4): ")
    val operacao = readln().toInt()

    when (operacao) {
        1 -> {
            val resultado = n1 + n2
            println("\nResultado: $n1 + $n2 = $resultado")
        }
        2 -> {
            val resultado = n1 - n2
            println("\nResultado: $n1 - $n2 = $resultado")
        }
        3 -> {
            if (n2 != 0) {
                val resultado = n1.toDouble() / n2.toDouble()
                println("\nResultado: $n1 / $n2 = $resultado")
            } else {
                println("\nErro: Não é possível dividir por zero!")
            }
        }
        4 -> {
            val resultado = n1 * n2
            println("\nResultado: $n1 * $n2 = $resultado")
        }
        else -> println("\nOpção inválida!")
    }
}