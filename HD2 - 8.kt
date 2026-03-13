fun main() {
    var soma = 0.0
    var contadorValidos = 0

    for (i in 1..4) {
        print("Digite o ${i}º número (entre 0 e 10): ")
        val num = readln().toDouble()

        if (num > 0 && num < 10) {
            soma += num
            contadorValidos++
        } else {
            println("Número ignorado (fora do intervalo 0-10).")
        }
    }

    if (contadorValidos > 0) {
        val media = soma / contadorValidos
        println("\nMédia dos números válidos: $media")

        if (media > 5) {
            println("Você passou no teste")
        } else {
            println("tente novamente")
        }
    } else {
        println("Nenhum número válido foi informado. tente novamente")
    }
}