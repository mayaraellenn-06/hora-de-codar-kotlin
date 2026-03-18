fun main(args: Array<String>) {
    var soma = 0.0
    var quantidade = 0

    for (i in 15..100) {
        soma += i
        quantidade++
    }

    val media = soma / quantidade

    println("A soma dos números entre 15 e 100 é: $soma")
    println("A quantidade de números é: $quantidade")
    println("A média aritmética é: $media")
}