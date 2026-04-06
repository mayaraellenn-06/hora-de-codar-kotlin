import kotlin.system.exitProcess
import java.util.*

// --- 1. MODELOS DE DADOS (POO) ---
data class ItemMenu(val nome: String, val preco: Double)

data class Hospede(
    val nome: String,
    val cpf: String,
    val quarto: Int,
    val valorDiaria: Double,
    val qtdDias: Int,
    var totalConsumo: Double = 0.0
) {
    fun calcularContaTotal(): Double {
        return (valorDiaria * qtdDias) + totalConsumo
    }
}

// --- 2. VARIÁVEIS GLOBAIS E BANCO DE DATA ---
val sc = Scanner(System.`in`)
var nomeFuncionario = ""
var faturamentoTotalHotel = 0.0
val quartosOcupados = Array(21) { false }

// Lista inicial baseada no material do professor
val listaHospedes = mutableListOf(
    Hospede("Carlos Villagran", "001", 1, 100.0, 1),
    Hospede("Maria Antonieta de las Nieves", "002", 2, 100.0, 1),
    Hospede("Roberto Gómez Bolaños", "003", 3, 100.0, 1),
    Hospede("Florinda Meza", "004", 4, 100.0, 1),
    Hospede("Ramón Valdés", "005", 5, 100.0, 1)
)

// Cardápio A La Carte
val cardapio = mapOf(
    "🍽️ Pratos" to listOf(ItemMenu("Risoto", 65.0), ItemMenu("Filé Mignon", 85.0)),
    "🥤 Bebidas" to listOf(ItemMenu("Suco Natural", 15.0), ItemMenu("Vinho Reserva", 120.0))
)

fun main() {
    // Sincroniza quartos ocupados com a lista inicial
    listaHospedes.forEach { quartosOcupados[it.quarto] = true }

    println("=========================================")
    println("       ☀️ HOTEL SUNSHINE - PREMIUM       ")
    println("=========================================")

    autenticacao()
    inicio()
}

// --- 3. SISTEMA DE AUTENTICAÇÃO ---
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
        println("Senha incorreta! ($tentativas/3)")
    }
    println("Acesso bloqueado.")
    exitProcess(0)
}

// --- 4. MENU PRINCIPAL (MODULAR) ---
fun inicio() {
    while (true) {
        println("\n--- 🧭 MENU PRINCIPAL ---")
        println("1. Reservas de Quartos")
        println("2. Cadastro de Hóspedes")
        println("3. Serviço de Quarto")
        println("4. Check-out (Fechar Conta)")
        println("5. Relatórios e Faturamento")
        println("6. Sair")
        print("Opção: ")

        when (sc.nextLine()) {
            "1" -> reservaDeQuartos()
            "2" -> menuHospedes()
            "3" -> servicoDeQuarto()
            "4" -> fazerCheckOut()
            "5" -> relatorios()
            "6" -> sairDoHotel()
            else -> println("Erro: Informe um número entre 1 e 6.")
        }
    }
}

// --- 5. MÓDULO: RESERVAS ---
fun reservaDeQuartos() {
    println("\n[RESERVA DE QUARTOS]")
    print("Valor da diária: R$ ")
    val valor = sc.nextLine().toDoubleOrNull() ?: 0.0
    print("Quantidade de diárias (1-30): ")
    val dias = sc.nextLine().toIntOrNull() ?: 0

    if (valor <= 0 || dias !in 1..30) {
        println("⚠️ Valor inválido, $nomeFuncionario."); return
    }

    exibirMapaDeQuartos()
    print("Escolha um quarto (1-20): ")
    val q = sc.nextLine().toIntOrNull() ?: 0

    if (q !in 1..20 || quartosOcupados[q]) {
        println("❌ Quarto indisponível."); return
    }

    print("Nome do hóspede: ")
    val nome = sc.nextLine()
    print("CPF: ")
    val cpf = sc.nextLine()

    val total = (valor * dias) * 1.10 // Incluindo 10% de taxa
    println("Total com taxas: R$ %.2f. Confirmar? (S/N)".format(total))

    if (sc.nextLine().uppercase() == "S") {
        listaHospedes.add(Hospede(nome, cpf, q, valor, dias))
        quartosOcupados[q] = true
        println("✅ Reserva confirmada!")
    }
}

// --- 6. MÓDULO: GESTÃO DE HÓSPEDES ---
fun menuHospedes() {
    while (true) {
        println("\n--- CADASTRO DE HÓSPEDES ---")
        println("1. Pesquisar | 2. Listar | 3. Voltar")
        when (sc.nextLine()) {
            "1" -> {
                print("Digite o nome: ")
                val busca = sc.nextLine()
                val encontrados = listaHospedes.filter { it.nome.contains(busca, true) }
                if (encontrados.isNotEmpty()) encontrados.forEach { println("- ${it.nome} (Quarto ${it.quarto})") }
                else println("Hóspede não encontrado.")
            }
            "2" -> listaHospedes.forEach { println("Quarto ${it.quarto}: ${it.nome}") }
            "3" -> return
        }
    }
}

// --- 7. MÓDULO: SERVIÇO DE QUARTO ---
fun servicoDeQuarto() {
    print("Número do Quarto: ")
    val q = sc.nextLine().toIntOrNull() ?: 0
    val h = listaHospedes.find { it.quarto == q } ?: return println("Quarto vazio.")

    println("\n--- MENU A LA CARTE ---")
    val itens = mutableListOf<ItemMenu>()
    cardapio.forEach { (_, lista) -> lista.forEach { itens.add(it); println("${itens.size}. ${it.nome} - R$ ${it.preco}") } }

    print("Escolha o item (0 para sair): ")
    val escolha = sc.nextLine().toIntOrNull() ?: 0
    if (escolha in 1..itens.size) {
        h.totalConsumo += itens[escolha - 1].preco
        println("✅ Pedido entregue!")
    }
}

// --- 8. MÓDULO: CHECK-OUT ---
fun fazerCheckOut() {
    print("Quarto para Check-out: ")
    val q = sc.nextLine().toIntOrNull() ?: 0
    val h = listaHospedes.find { it.quarto == q } ?: return println("Não encontrado.")

    val conta = h.calcularContaTotal()
    println("\nExtrato Final de ${h.nome}: R$ %.2f".format(conta))
    print("Confirmar pagamento? (S/N): ")
    if (sc.nextLine().uppercase() == "S") {
        faturamentoTotalHotel += conta
        quartosOcupados[q] = false
        listaHospedes.remove(h)
        println("✅ Quarto $q liberado!")
    }
}

// --- UTILITÁRIOS ---
fun exibirMapaDeQuartos() {
    for (i in 1..20) {
        val s = if (quartosOcupados[i]) "O" else "L"
        print("[$i:$s] ".padEnd(7))
        if (i % 5 == 0) println()
    }
}

fun relatorios() {
    println("\n--- 📊 RELATÓRIOS ---")
    println("Ocupação: ${quartosOcupados.count { it }} / 20")
    println("Faturamento Total: R$ %.2f".format(faturamentoTotalHotel))
}

fun sairDoHotel() {
    println("Até logo, $nomeFuncionario!")
    exitProcess(0)
}