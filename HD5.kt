import java.util.Scanner

var saldo = 1000.0
var nomeUsuario = ""
const val SENHA_CORRETA = 3589
val sc = Scanner(System.`in`)

fun main() {
    println("=========================================")
    println("          🏦 BANCO MAYS 24H            ")
    println("=========================================")

    print("Por favor, informe seu nome: ")
    nomeUsuario = sc.nextLine()

    println("\nOlá, $nomeUsuario, é um prazer ter você por aqui!")

    inicio()
}

fun inicio() {
    println("\n-----------------------------------------")
    println("Escolha uma opção:")
    println("1. Saldo")
    println("2. Extrato")
    println("3. Saque")
    println("4. Depósito")
    println("5. Transferência")
    println("6. Sair")
    print("Opção: ")

    when (sc.next()) {
        "1" -> verSaldo()
        "2" -> verExtrato()
        "3" -> fazerSaque()
        "4" -> fazerDeposito()
        "5" -> fazerTransferencia()
        "6" -> sair()
        else -> erro()
    }
}

fun validarSenha(): Boolean {
    print("Digite sua senha de 4 dígitos: ")
    val senhaInformada = sc.nextInt()

    return if (senhaInformada == SENHA_CORRETA) {
        true
    } else {
        println("❌ Senha incorreta!")
        false
    }
}

fun verSaldo() {
    if (validarSenha()) {
        println("\n💰 Seu saldo atual é: R$ %.2f".format(saldo))
    }
    inicio()
}

fun verExtrato() {
    if (validarSenha()) {
        println("\n--- 📝 EXTRATO ---")
        println("- Supermercado: R$ 150,00 (Débito)")
        println("- Farmácia: R$ 45,50 (Débito)")
        println("- Depósito Recebido: R$ 500,00 (Crédito)")
        println("- Netflix: R$ 55,90 (Débito)")
        println("---------------------------")
        println("Saldo Final: R$ %.2f".format(saldo))
    }
    inicio()
}

fun fazerSaque() {
    if (validarSenha()) {
        print("Quanto deseja sacar? R$ ")
        val valorSaque = sc.nextDouble()

        if (valorSaque <= 0 || valorSaque > saldo) {
            println("⚠️ Operação não autorizada. Valor inválido ou saldo insuficiente.")
        } else {
            saldo -= valorSaque
            println("✅ Saque realizado com sucesso!")
        }
    }
    inicio()
}

fun fazerDeposito() {
    print("Quanto deseja depositar? R$ ")
    val valorDep = sc.nextDouble()

    if (valorDep <= 0) {
        println("⚠️ Operação não autorizada. O valor deve ser maior que zero.")
    } else {
        saldo += valorDep
        println("✅ Depósito de R$ %.2f realizado!".format(valorDep))
    }
    inicio()
}

fun fazerTransferencia() {
    if (validarSenha()) {
        print("Informe o número da conta destino (apenas números): ")

        if (sc.hasNextInt()) {
            val conta = sc.nextInt()
            print("Informe o valor da transferência: R$ ")
            val valorTransf = sc.nextDouble()

            if (valorTransf <= 0 || valorTransf > saldo) {
                println("⚠️ Operação não autorizada. Valor inválido ou saldo insuficiente.")
            } else {
                saldo -= valorTransf
                println("✅ Transferência de R$ %.2f para a conta $conta realizada!".format(valorTransf))
            }
        } else {
            println("⚠️ Operação não autorizada. O número da conta deve conter apenas dígitos.")
            sc.next()
        }
    }
    inicio()
}

fun sair() {
    println("\n$nomeUsuario, foi um prazer ter você por aqui! Até logo. 👋")
}

fun erro() {
    println("❌ Opção inválida! Por favor, escolha entre 1 e 6.")
    inicio()
}