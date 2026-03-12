fun main() {
    print("Digite o primeiro valor: ")
    val n1 = readln().toDouble()

    print("Digite o segundo valor: ")
    val n2 = readln().toDouble()

    print("Digite o terceiro valor: ")
    val n3 = readln().toDouble()

    val somaDosMaiores: Double

    if (n1 < n2 && n1 < n3) {

        somaDosMaiores = n2 + n3
    } else if (n2 < n1 && n2 < n3) {
        somaDosMaiores = n1 + n3
    } else {
        somaDosMaiores = n1 + n2
    }

    println("A soma dos dois maiores valores é: $somaDosMaiores")
}
