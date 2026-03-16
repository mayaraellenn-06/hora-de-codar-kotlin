fun main(args: Array<String>) {
    print("Digite o primeiro valor: ")
    val n1 = readln().toDouble()


    print("Digite o segundo valor (deve ser maior que zero): ")
    var n2 = readln().toDouble()

    while (n2 <= 0) {
        println("Valor inválido! O segundo número não pode ser zero ou negativo.")
        print("Por favor, informe um novo valor para o segundo número: ")
        n2 = readln().toDouble()
    }

    val resultado = n1 / n2
    println("\nResultado da divisão: $n1 / $n2 = $resultado")
}