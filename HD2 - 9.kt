fun main() {
    val anoAtual = 2026

    print("Digite o ano do seu nascimento: ")
    val anoNascimento = readln().toInt()

    val idade = anoAtual - anoNascimento

    println("Você tem (ou fará) $idade anos.")

    if (idade >= 16) {
        println("Você poderá votar este ano!")
    } else {
        println("Você ainda não pode votar este ano.")
    }
}