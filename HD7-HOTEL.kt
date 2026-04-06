import kotlin.system.exitProcess
import java.util.*


data class ItemMenu(val nome: String, val preco: Double)

data class Hospede(
    val nome: String,
    val cpf: String,
    val quarto: Int,
    val valorDiaria: Double,
    val qtdDias: Int,
    var totalConsumo: Double = 0.0
) {
    // Método que calcula o valor final da conta
    fun calcularContaTotal(): Double {
        return (valorDiaria * qtdDias) + totalConsumo
    }
}

val listaHospedes = mutableListOf<Hospede>()
val quartosOcupados = Array(21) { false } // Gerencia quartos 1 a 20
var nomeFuncionario = ""
var faturamentoTotalHotel = 0.0
val sc = Scanner(System.`in`)

val cardapio = mapOf(
    "🍽️ Pratos Principais" to listOf(
        ItemMenu("Filé Mignon ao Molho Madeira", 85.0),
        ItemMenu("Risoto de Cogumelos Selvagens", 65.0),
        ItemMenu("Salmão Grelhado com Ervas", 78.0)
    ),
    "🥤 Bebidas Premium" to listOf(
        ItemMenu("Vinho Tinto Reserva", 120.0),
        ItemMenu("Suco de Laranja Natural", 15.0),
        ItemMenu("Água Mineral Glacial", 7.0)
    )
)

fun main() {
    println("=========================================")
    println("       ☀️ HOTEL SUNSHINE      ")
    println("=========================================")

    autenticacao()
    menuPrincipal()
}

fun autenticacao() {
    print("Identificação do Colaborador: ")
    nomeFuncionario = sc.nextLine()
    var tentativas = 0
    while (tentativas < 3) {
        print("Senha de Acesso: ")
        if (sc.nextLine() == "2678") {
            println("\nBem-vindo ao Hotel SUNSHINE, $nomeFuncionario.")
            return
        }
        tentativas++
        println("Senha incorreta! Tentativas: $tentativas/3")
    }
    println("Acesso bloqueado.")
    exitProcess(0)
}

fun menuPrincipal() {
    while (true) {
        println("\n--- 🧭 SISTEMA OPERACIONAL SUNSHINE ---")
        println("1. Check-in (Nova Reserva)")
        println("2. Serviço de Quarto (A La Carte)")
        println("3. Check-out (Fechar Conta)")
        println("4. Status e Faturamento")
        println("5. Sair")
        print("Opção: ")

        when (sc.nextLine()) {
            "1" -> fazerCheckIn()
            "2" -> servicoDeQuarto()
            "3" -> fazerCheckOut()
            "4" -> statusHotel()
            "5" -> {
                println("Muito obrigado e até logo, $nomeFuncionario."); exitProcess(0)
            }
            else -> println("Opção inválida! Informe um número entre 1 a 5.")
        }
    }
}

fun fazerCheckIn() {
    if (listaHospedes.size >= 15) {
        println("⚠️ Limite de 15 hóspedes atingido."); return
    }

    print("Nome do Hóspede: ")
    val nome = sc.nextLine()
    if (listaHospedes.any { it.nome.equals(nome, true) }) {
        println("❌ Este hóspede já possui cadastro ativo."); return
    }

    print("CPF: ")
    val cpf = sc.nextLine()
    print("Valor da Diária: R$ ")
    val diaria = sc.nextLine().toDoubleOrNull() ?: 0.0
    print("Quantidade de dias (1-30): ")
    val dias = sc.nextLine().toIntOrNull() ?: 0

    if (diaria <= 0 || dias !in 1..30) {
        println("Valores inválidos para reserva."); return
    }

    print("Escolha um quarto (1-20): ")
    val q = sc.nextLine().toIntOrNull() ?: 0

    if (q in 1..20 && !quartosOcupados[q]) {
        listaHospedes.add(Hospede(nome, cpf, q, diaria, dias))
        quartosOcupados[q] = true
        println("✅ Check-in concluído! Quarto $q ocupado.")
    } else {
        println("❌ Quarto indisponível.")
    }
}

fun servicoDeQuarto() {
    print("Número do Quarto: ")
    val q = sc.nextLine().toIntOrNull() ?: 0
    val hospede = listaHospedes.find { it.quarto == q }

    if (hospede == null) {
        println("❌ Não há hóspedes no quarto $q."); return
    }

    println("\n--- 🍽️ MENU SUNSHINE ---")
    val menuCompleto = mutableListOf<ItemMenu>()
    cardapio.forEach { (cat, itens) ->
        println("\n[$cat]")
        itens.forEach { item ->
            menuCompleto.add(item)
            println("${menuCompleto.size}. ${item.nome} - R$ ${item.preco}")
        }
    }

    print("\nEscolha o item (0 para cancelar): ")
    val escolha = sc.nextLine().toIntOrNull() ?: 0

    if (escolha in 1..menuCompleto.size) {
        val pedido = menuCompleto[escolha - 1]
        hospede.totalConsumo += pedido.preco
        println("✅ ${pedido.nome} entregue ao quarto $q!")
    }
}

fun fazerCheckOut() {
    print("Número do Quarto para Check-out: ")
    val q = sc.nextLine().toIntOrNull() ?: 0
    val hospede = listaHospedes.find { it.quarto == q }

    if (hospede == null) {
        println("❌ Quarto vazio."); return
    }

    val totalEstadia = hospede.valorDiaria * hospede.qtdDias
    val totalFinal = hospede.calcularContaTotal()

    println("\n--- 🧾 FECHAMENTO DE CONTA ---")
    println("Hóspede: ${hospede.nome} | CPF: ${hospede.cpf}")
    println("Estadia (${hospede.qtdDias} dias): R$ %.2f".format(totalEstadia))
    println("Consumo Extra: R$ %.2f".format(hospede.totalConsumo))
    println("TOTAL FINAL: R$ %.2f".format(totalFinal))

    print("\nConfirmar pagamento e encerrar? (S/N): ")
    if (sc.nextLine().uppercase() == "S") {
        faturamentoTotalHotel += totalFinal
        quartosOcupados[q] = false
        listaHospedes.remove(hospede)
        println("✅ Check-out realizado! Quarto $q liberado.")
    }
}

fun statusHotel() {
    println("\n--- 📊 DASHBOARD SUNSHINE ---")
    println("Hóspedes Ativos: ${listaHospedes.size}")
    println("Quartos Ocupados: ${quartosOcupados.count { it }} / 20")
    println("Faturamento Bruto: R$ %.2f".format(faturamentoTotalHotel))

    if (listaHospedes.isNotEmpty()) {
        println("\nDetalhamento por Quarto:")
        listaHospedes.forEach { h ->
            println("Quarto ${h.quarto}: ${h.nome} | Parcial: R$ %.2f".format(h.calcularContaTotal()))
        }
    }
}