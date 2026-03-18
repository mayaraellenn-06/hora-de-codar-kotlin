fun main(args: Array<String>) {
    val listaEstudantes = mutableListOf<String>()

    println("--- Cadastro de Estudantes ---")
    println("(Digite 'PARE' para encerrar e exibir a lista)\n")

    while (true) {
        print("Digite o nome do estudante: ")
        val entrada = readln()

        if (entrada.uppercase() == "PARE") {
            break // Sai do loop while
        }

        if (entrada.isNotBlank()) {
            listaEstudantes.add(entrada)
        } else {
            println("Nome inválido! Tente novamente.")
        }
    }

    println("\n--- RELATÓRIO FINAL ---")
    println("Quantidade de estudantes cadastrados: ${listaEstudantes.size}")

    println("Lista de Estudantes:")
    listaEstudantes.forEach { estudante ->
        println("- $estudante")
    }
}