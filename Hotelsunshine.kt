import kotlin.system.exitProcess
import java.util.*
import kotlin.math.*


var nomeUsuario = ""
var faturamentoTotal = 0.0
val quartosOcupados = Array(21) { false } // Quartos 1 a 20
val listaHospedes = mutableListOf<String>() // Máximo 15
val sc = Scanner(System.`in`)

fun main() {
    println("=========================================")
    println("       ☀️ BEM-VINDO AO HOTEL SUNSHINE     ")
    println("=========================================")

    if (autenticacao()) {
        menuPrincipal()
    }
}

fun autenticacao(): Boolean {
    print("Por favor, informe seu nome: ")
    nomeUsuario = sc.nextLine()

    var tentativas = 0
    val senhaCorreta = "2678"

    while (tentativas < 3) {
        print("Digite sua senha: ")
        val senhaDigitada = sc.nextLine()

        if (senhaDigitada == senhaCorreta) {
            println("\nBem-vindo ao Hotel SUNSHINE, $nomeUsuario. É um imenso prazer!")
            return true
        } else {
            tentativas++
            println("Senha incorreta! Tentativas restantes: ${3 - tentativas}")
        }
    }

    println("Sistema bloqueado por excesso de tentativas.")
    exitProcess(0)
}

fun menuPrincipal() {
    while (true) {
        println("\n--- 🧭 MENU PRINCIPAL - SUNSHINE ---")
        println("1. Reservas de Quartos")
        println("2. Cadastro de Hóspedes")
        println("3. Eventos")
        println("4. Ar-Condicionado")
        println("5. Abastecimento")
        println("6. Relatórios Operacionais")
        println("7. Sair")
        print("Escolha uma opção: ")

        when (sc.nextLine()) {
            "1" -> reservaDeQuartos()
            "2" -> menuHospedes()
            "3" -> subEventos()
            "4" -> subArCondicionado()
            "5" -> subAbastecimento()
            "6" -> relatorios()
            "7" -> {
                println("Muito obrigado e até logo, $nomeUsuario.")
                exitProcess(0)
            }
            else -> println("Opção inválida! Informe de 1 a 7.")
        }
    }
}

fun reservaDeQuartos() {
    println("\n[RESERVAS]")
    print("Valor da diária: ")
    val valor = sc.nextLine().toDoubleOrNull() ?: -1.0
    print("Quantidade de dias (1-30): ")
    val dias = sc.nextLine().toIntOrNull() ?: -1

    if (valor <= 0 || dias !in 1..30) {
        println("Valor inválido, $nomeUsuario")
        return
    }

    print("Nome do hóspede: ")
    val nomeH = sc.nextLine()
    println("Tipo: (S) Standard [1.0] | (E) Executivo [1.35] | (L) Luxo [1.65]")
    val fator = when (sc.nextLine().uppercase()) {
        "S" -> 1.00; "E" -> 1.35; "L" -> 1.65; else -> 1.00
    }

    exibirMapa()
    print("Escolha o quarto (1-20): ")
    val q = sc.nextLine().toIntOrNull() ?: 0

    if (q !in 1..20 || quartosOcupados[q]) {
        println("Quarto já ocupado ou inválido!")
        return
    }

    val total = (valor * dias * fator) * 1.10 // +10% taxa
    println("Total: R$ %.2f. Confirmar? (S/N)".format(total))
    if (sc.nextLine().uppercase() == "S") {
        quartosOcupados[q] = true
        faturamentoTotal += total
        println("Reserva efetuada!")
    }
}

fun menuHospedes() {
    println("\n[CADASTRO]")
    println("1. Cadastrar | 2. Pesquisar Prefixo | 3. Listar | 4. Voltar")
    when (sc.nextLine()) {
        "1" -> {
            if (listaHospedes.size >= 15) println("Máximo de cadastros atingido")
            else {
                print("Nome: "); val n = sc.nextLine()
                if (listaHospedes.contains(n)) println("Hóspede já cadastrado")
                else { listaHospedes.add(n); println("Sucesso!") }
            }
        }
        "2" -> {
            print("Prefixo: "); val p = sc.nextLine()
            listaHospedes.filter { it.startsWith(p, true) }.forEach { println("- $it") }
        }
        "3" -> listaHospedes.sorted().forEachIndexed { i, h -> println("[$i] $h") }
    }
}

fun subEventos() {
    print("Convidados: ")
    val conv = sc.nextLine().toIntOrNull() ?: 0
    if (conv !in 1..350) { println("Capacidade excedida!"); return }

    val aud = if (conv <= 220) "Laranja" else "Colorado"
    println("Auditório: $aud")
    if (aud == "Laranja" && conv > 150) println("Cadeiras extras: ${conv - 150}")

    print("Duração (horas): "); val dur = sc.nextLine().toInt()
    val garcons = ceil(conv / 12.0).toInt() + (dur / 2)
    val custoG = garcons * dur * 10.50
    val buffet = (conv * 0.2 * 0.8) + (conv * 0.5 * 0.4) + ((conv * 7.0 / 100) * 34.0)

    println("Total Evento: R$ %.2f. Confirmar? (S/N)".format(custoG + buffet))
    if (sc.nextLine().uppercase() == "S") println("Reservado!")
}

fun subArCondicionado() {
    var menor = Double.MAX_VALUE
    var melhor = ""
    do {
        print("Empresa: "); val emp = sc.nextLine()
        print("Valor/Aparelho: "); val v = sc.nextLine().toDouble()
        print("Qtd: "); val q = sc.nextLine().toInt()
        print("Desc (%): "); val d = sc.nextLine().toDouble()
        print("Min Desc: "); val m = sc.nextLine().toInt()

        var total = v * q
        if (q >= m) total -= total * (d / 100)
        println("Serviço de $emp: R$ %.2f".format(total))

        if (total < menor) { menor = total; melhor = emp }
        print("Novos dados? (S/N): ")
    } while (sc.nextLine().uppercase() == "S")
    println("Melhor: $melhor - R$ %.2f".format(menor))
}

fun subAbastecimento() {
    print("Álcool Wayne: "); val aw = sc.nextLine().toDouble()
    print("Gasolina Wayne: "); val gw = sc.nextLine().toDouble()
    val custoW = 42 * (if (aw <= gw * 0.7) aw else gw)
    println("Wayne Oil: R$ %.2f".format(custoW))
}

fun exibirMapa() {
    for (i in 1..20) {
        val st = if (quartosOcupados[i]) "O" else "L"
        print("[$i:$st] ".padEnd(7))
        if (i % 5 == 0) println()
    }
}

fun relatorios() {
    println("\n--- RELATÓRIO SUNSHINE ---")
    println("Ocupação: ${quartosOcupados.count { it }} / 20")
    println("Hóspedes: ${listaHospedes.size}")
    println("Faturamento: R$ %.2f".format(faturamentoTotal))
}