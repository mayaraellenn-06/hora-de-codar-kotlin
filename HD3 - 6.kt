fun main(args: Array<String>) {
    var aprovados = 0
    var resposta: String

    do {
        print("\nDigite a 1ª nota: ")
        val nota1 = readln().toDouble()

        print("Digite a 2ª nota: ")
        val nota2 = readln().toDouble()

        val media = (nota1 + nota2) / 2
        println("Média final: $media")

        if (media >= 9.5) {
            println("Aluno APROVADO!")
            aprovados++ // Soma 1 ao contador de aprovados
        } else {
            println("Aluno REPROVADO.")
        }

        print("\nCalcular a média de outro aluno Sim/Não (S/N)? ")
        resposta = readln().uppercase() // .uppercase() converte para maiúsculo para aceitar 's' ou 'S'

    } while (resposta == "S") // Repete ENQUANTO a resposta for "S"

    println("\n--- SISTEMA ENCERRADO ---")
    println("Quantidade de alunos aprovados: $aprovados")
}