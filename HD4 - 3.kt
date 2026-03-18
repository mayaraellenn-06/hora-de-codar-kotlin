fun main() {
    val listaCompras = mutableListOf("Maçã", "Banana", "Morango", "Uva", "Laranja", "Abacaxi")

    println("=========================================")
    println("🍎 BEM-VINDO AO HORTIFRUTI MAYS 🍇")
    println("Sua missão: Retirar todos os itens da lista.")
    println("=========================================")

    while (listaCompras.isNotEmpty()) {

        println("\n🛒 Itens restantes no carrinho (${listaCompras.size}):")
        println("👉 ${listaCompras.joinToString(" | ")}") // Mostra as frutas separadas por uma barra

        print("\nQual fruta você deseja retirar agora? ")
        val frutaDesejada = readln().trim()

        val foiRemovida = listaCompras.removeIf { it.equals(frutaDesejada, ignoreCase = true) }

        if (foiRemovida) {
            println("✅ Boa! '$frutaDesejada' foi retirada com sucesso.")

            if (listaCompras.size > 0) {
                println("Faltam apenas ${listaCompras.size} itens para encerrar!")
            }
        } else {
            println("❌ Ops! '$frutaDesejada' não está na lista ou já foi retirada.")
            println("Dica: Verifique a ortografia e tente novamente.")
        }
    }

    println("\n=========================================")
    println("🥳 PARABÉNS! Você retirou todos os itens.")
    println("🏁 Lista de compras finalizada com sucesso!")
    println("=========================================")
}