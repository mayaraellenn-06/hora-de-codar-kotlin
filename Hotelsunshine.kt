import java.util.*
import kotlin.math.*
import kotlin.system.exitProcess

/**
 * HOTEL SUNSHINE: Plataforma Interna de Operações Hoteleiras
 */

var nomeUsuarioGlobal = ""
var quartosStatus = Array(20) { "L" } // L = Livre, O = Ocupado
var listaHospedesAtivos = mutableListOf<String>()
var totalReceitaHospedagem = 0.0
var totalReceitaEventos = 0.0
var contadorReservasConfirmadas = 0
var contadorEventosConfirmados = 0

fun main() {
    println("Bem-vindo ao Sunshine")
    if (autenticacao()) {
        menuPrincipal()
    }
}

fun autenticacao(): Boolean {
    val scanner = Scanner(System.`in`)
    print("Informe o nome de usuário: ")
    val nome = scanner.nextLine()

    var tentativas = 0
    val senhaCorreta = "2678"

    while (tentativas < 3) {
        print("Informe a senha: ")
        val senha = scanner.nextLine()

        if (senha == senhaCorreta) {
            nomeUsuarioGlobal = nome
            println("Bem-vindo ao Hotel Sunshine, $nomeUsuarioGlobal. É um imenso prazer ter você por aqui!")
            return true
        } else {
            tentativas++
            println("Senha incorreta. Tentativas: $tentativas/3")
        }
    }

    println("Número máximo de tentativas excedido. Sistema bloqueado.")
    exitProcess(0)
}

fun menuPrincipal() {
    val scanner = Scanner(System.`in`)
    while (true) {
        println("\n--- MENU PRINCIPAL ---")
        println("1. Reservas de Quartos")
        println("2. Cadastro de Hóspedes")
        println("3. Eventos")
        println("4. Ar-Condicionado")
        println("5. Abastecimento")
        println("6. Relatórios Operacionais")
        println("7. Sair")
        print("Opção: ")

        when (scanner.nextLine()) {
            "1" -> reservaQuartos()
            "2" -> cadastroHospedes()
            "3" -> pipelineEventos()
            "4" -> arCondicionado()
            "5" -> abastecimento()
            "6" -> exibirRelatorios()
            "7" -> {
                println("Muito obrigado e até logo, $nomeUsuarioGlobal.")
                exitProcess(0)
            }
            else -> erroOpcaoInvalida()
        }
    }
}

fun erroOpcaoInvalida() {
    println("Opção inválida. Por favor, tente novamente.")
}

fun reservaQuartos() {
    val sc = Scanner(System.`in`)
    println("\n[Reservas]")

    print("Informe o valor da diária: ")
    val valorDiaria = sc.nextLine().toDoubleOrNull() ?: -1.0
    print("Informe a quantidade de diárias (1-30): ")
    val qtdDiarias = sc.nextLine().toIntOrNull() ?: -1

    if (valorDiaria <= 0 || qtdDiarias !in 1..30) {
        println("Valor inválido, $nomeUsuarioGlobal")
        return
    }

    print("Informe o nome do hóspede: ")
    val nomeHospede = sc.nextLine()

    println("Tipo de quarto: (S) Standard | (E) Executivo | (L) Luxo")
    val tipo = sc.nextLine().uppercase()
    val fator = when (tipo) {
        "S" -> 1.0; "E" -> 1.35; "L" -> 1.65; else -> 1.0
    }

    var quartoValido = false
    var numQuarto = -1

    while (!quartoValido) {
        print("Escolha um quarto (1-20): ")
        numQuarto = sc.nextLine().toIntOrNull() ?: 0

        if (numQuarto !in 1..20) {
            println("Quarto inválido.")
        } else if (quartosStatus[numQuarto - 1] == "O") {
            println("Quarto já está ocupado.")
            println("Quartos livres: " + quartosStatus.indices.filter { quartosStatus[it] == "L" }.map { it + 1 })
        } else {
            quartoValido = true
        }
    }

    val subtotal = valorDiaria * qtdDiarias * fator
    val taxaServico = subtotal * 0.10
    val total = subtotal + taxaServico

    println("\nResumo:")
    println("Hóspede: $nomeHospede")
    println("Quarto: $numQuarto")


    println("Subtotal: R$ %.2f".format(subtotal))

    println("Taxa de serviço (10%%): R$ %.2f".format(taxaServico))

    println("Total: R$ %.2f".format(total))
    }

fun exibirGradeQuartos() {
    println("\nMapa de Quartos:")
    for (i in 0 until 20) {
        print("${quartosStatus[i]} ")
        if ((i + 1) % 5 == 0) println()
    }
}

fun cadastroHospedes() {
    val sc = Scanner(System.`in`)
    while (true) {
        println("\n[Cadastro de Hóspedes]")
        println("1-Cadastrar 2-Pesquisar exato 3-Pesquisar prefixo 4-Listar 5-Atualizar 6-Remover 7-Voltar")
        print("Opção: ")

        when (sc.nextLine()) {
            "1" -> {
                if (listaHospedesAtivos.size >= 15) {
                    println("Máximo de cadastros atingido")
                } else {
                    print("Nome do hóspede: ")
                    val nome = sc.nextLine()
                    if (listaHospedesAtivos.contains(nome)) println("Hóspede já cadastrado")
                    else {
                        listaHospedesAtivos.add(nome)
                        println("Operação realizada com sucesso")
                    }
                }
            }
            "2" -> {
                print("Nome para pesquisa: ")
                val nome = sc.nextLine()
                if (listaHospedesAtivos.contains(nome)) println("Hóspede $nome foi encontrado")
                else println("Hóspede não encontrado")
            }
            "3" -> {
                print("Prefixo: ")
                val pre = sc.nextLine()
                val result = listaHospedesAtivos.filter { it.startsWith(pre, ignoreCase = true) }
                if (result.isEmpty()) println("Nenhum hóspede encontrado")
                else result.forEachIndexed { i, s -> println("[$i] $s") }
            }
            "4" -> {
                if (listaHospedesAtivos.isEmpty()) println("Lista vazia")
                else listaHospedesAtivos.sorted().forEachIndexed { i, s -> println("[$i] $s") }
            }
            "5" -> {
                print("Índice para atualizar: ")
                val idx = sc.nextLine().toIntOrNull() ?: -1
                if (idx in listaHospedesAtivos.indices) {
                    print("Novo nome: ")
                    listaHospedesAtivos[idx] = sc.nextLine()
                    println("Operação realizada com sucesso")
                } else println("Índice inválido")
            }
            "6" -> {
                print("Índice para remover: ")
                val idx = sc.nextLine().toIntOrNull() ?: -1
                if (idx in listaHospedesAtivos.indices) {
                    listaHospedesAtivos.removeAt(idx)
                    println("Operação realizada com sucesso")
                } else println("Índice inválido")
            }
            "7" -> return
            else -> println("Opção inválida")
        }
    }
}

fun pipelineEventos() {
    val sc = Scanner(System.`in`)
    println("\n[Eventos]")

    // A - Capacidade
    print("Número de convidados: ")
    val convidados = sc.nextLine().toIntOrNull() ?: -1
    if (convidados !in 0..350) {
        println("Número de convidados inválido")
        return
    }

    var auditorio = ""
    var extras = 0
    if (convidados <= 220) {
        auditorio = "Laranja"
        if (convidados > 150) extras = convidados - 150
    } else {
        auditorio = "Colorado"
    }
    println("Auditório selecionado: $auditorio" + if (extras > 0) " ($extras cadeiras adicionais)" else "")

    // B - Agenda
    print("Dia da semana: ")
    val dia = sc.nextLine().lowercase().trim()
    print("Hora inicial (0-23): ")
    val hora = sc.nextLine().toIntOrNull() ?: -1
    print("Duração do evento (horas): ")
    val duracao = sc.nextLine().toIntOrNull() ?: -1

    val disponivel = when (dia) {
        "segunda", "terca", "terça", "quarta", "quinta", "sexta" -> hora in 7..23 && (hora + duracao) <= 23
        "sabado", "sábado", "domingo" -> hora in 7..15 && (hora + duracao) <= 15
        else -> false
    }

    if (!disponivel) {
        println("Auditório indisponível")
        return
    }

    print("Nome da empresa: ")
    val empresa = sc.nextLine()

    val garconsBase = ceil(convidados / 12.0).toInt()
    val reforcoGarcons = floor(duracao / 2.0).toInt()
    val totalGarcons = garconsBase + reforcoGarcons
    val custoGarcons = totalGarcons * duracao * 10.50

    val cafeL = convidados * 0.2
    val aguaL = convidados * 0.5
    val salgados = convidados * 7
    val custoBuffet = (cafeL * 0.8) + (aguaL * 0.4) + (ceil(salgados / 100.0) * 34.0)

    val totalGeral = custoGarcons + custoBuffet

    println("\nRESUMO DO EVENTO")
    println("Empresa: $empresa | Auditório: $auditorio")
    println("Data: $dia às ${hora}h | Duração: ${duracao}h")
    println("Garçons necessários: $totalGarcons")
    println("Custo Garçons: R$ %.2f".format(custoGarcons))
    println("Custo Buffet: R$ %.2f".format(custoBuffet))
    println("Total: R$ %.2f".format(totalGeral))

    print("Confirmar reserva? (S/N): ")
    if (sc.nextLine().uppercase() == "S") {
        totalReceitaEventos += totalGeral
        contadorEventosConfirmados++
        println("Reserva efetuada com sucesso.")
    }
}

fun arCondicionado() {
    val sc = Scanner(System.`in`)
    var melhorEmpresa = ""
    var menorValor = Double.MAX_VALUE

    do {
        println("\n[Orçamento Ar-Condicionado]")
        print("Nome da empresa: ")
        val emp = sc.nextLine()
        print("Valor por aparelho: ")
        val valorAp = sc.nextLine().toDoubleOrNull() ?: 0.0
        print("Quantidade de aparelhos: ")
        val qtd = sc.nextLine().toIntOrNull() ?: 0
        print("Percentual de desconto: ")
        val perc = sc.nextLine().toDoubleOrNull() ?: 0.0
        print("Mínimo para desconto: ")
        val min = sc.nextLine().toIntOrNull() ?: 0
        print("Valor do deslocamento: ")
        val desloc = sc.nextLine().toDoubleOrNull() ?: 0.0

        var total = valorAp * qtd
        if (qtd >= min) total -= total * (perc / 100)
        total += desloc

        println("O serviço de $emp custará R$ %.2f".format(total))

        if (total < menorValor) {
            menorValor = total
            melhorEmpresa = emp
        }

        print("Deseja informar novos dados, $nomeUsuarioGlobal? (S/N): ")
    } while (sc.nextLine().uppercase() == "S")

    println("Melhor orçamento: $melhorEmpresa — R$ %.2f".format(menorValor))
}

fun abastecimento() {
    val sc = Scanner(System.`in`)
    println("\n[Abastecimento]")

    print("Posto Wayne Oil - Álcool: ")
    val aW = sc.nextLine().toDoubleOrNull() ?: 0.0
    print("Posto Wayne Oil - Gasolina: ")
    val gW = sc.nextLine().toDoubleOrNull() ?: 0.0

    print("Posto Stark Petrol - Álcool: ")
    val aS = sc.nextLine().toDoubleOrNull() ?: 0.0
    print("Posto Stark Petrol - Gasolina: ")
    val gS = sc.nextLine().toDoubleOrNull() ?: 0.0

    val idealW = if (aW <= gW * 0.7) "Álcool" else "Gasolina"
    val precoW = if (idealW == "Álcool") aW * 42 else gW * 42

    val idealS = if (aS <= gS * 0.7) "Álcool" else "Gasolina"
    val precoS = if (idealS == "Álcool") aS * 42 else gS * 42

    println("Wayne Oil: melhor opção = $idealW | Total (42L) = R$ %.2f".format(precoW))
    println("Stark Petrol: melhor opção = $idealS | Total (42L) = R$ %.2f".format(precoS))

    val melhorPosto = if (precoW <= precoS) "Wayne Oil" else "Stark Petrol"
    println("$nomeUsuarioGlobal, é mais barato abastecer no posto $melhorPosto.")
}

fun exibirRelatorios() {
    val ocupados = quartosStatus.count { it == "O" }
    val taxa = (ocupados / 20.0) * 100

    println("\n=== RELATÓRIOS OPERACIONAIS - SUNSHINE ===")
    println("Total de reservas confirmadas: $contadorReservasConfirmadas")
    println("Taxa de ocupação: %.2f%%".format(taxa))
    println("Hóspedes cadastrados: ${listaHospedesAtivos.size}")
    println("Eventos confirmados: $contadorEventosConfirmados")
    println("------------------------------------------")
    println("Receita Hospedagem: R$ %.2f".format(totalReceitaHospedagem))
    println("Receita Eventos: R$ %.2f".format(totalReceitaEventos))
    println("RECEITA TOTAL: R$ %.2f".format(totalReceitaHospedagem + totalReceitaEventos))
    println("==========================================")
}