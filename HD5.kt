import kotlin.system.exitProcess

var saldo = 100.5
var nome: String = ""
const val SENHA_CORRETA = 3589

fun main() {
    print("Por favor, informe seu nome: ")
    nome = readlnOrNull().orEmpty()
    println("Olá $nome, é um prazer ter você por aqui!")

    inicio()
}

fun inicio() {
    println("\n--- MENU PRINCIPAL ---")
    println("1. Saldo")
    println("2. Extrato")
    println("3. Saque")
    println("4. Depósito")
    println("5. Transferência")
    println("6. Sair")
    print("Escolha uma opção: ")

    val escolha = readlnOrNull()?.toIntOrNull()

    when (escolha) {
        1 -> verSaldo()
        2 -> verExtrato()
        3 -> fazerSaque()
        4 -> fazerDeposito()
        5 -> fazerTransferencia()
        6 -> sair()
        else -> erro()
    }
}

fun validarSenha(): Boolean {
    print("Informe sua senha: ")
    val senhaDigitada = readlnOrNull()?.toIntOrNull()

    return if (senhaDigitada == SENHA_CORRETA) {
        true
    } else {
        println("Senha incorreta!")
        false
    }
}

fun verSaldo() {
    // Exige senha para acessar o saldo
    if (validarSenha()) {
        println("Seu saldo atual é: R$ $saldo")
        inicio()
    } else {
        verSaldo() // Caso a senha não seja correta, chama a função atual novamente
    }
}

fun verExtrato() {
    if (validarSenha()) {
        println("\n--- EXTRATO ---")
        println("Mercado Extra..... R$ 50,00")
        println("Posto Shell....... R$ 30,00")
        println("Depósito Recebido. R$ 100,00")
        println("Saldo atual: R$ $saldo")
        inicio()
    } else {
        verExtrato()
    }
}

fun fazerDeposito() {
    print("Qual o valor para depósito? ")
    val deposito = readlnOrNull()?.toDoubleOrNull()

    if (deposito == null || deposito <= 0) {
        println("Operação não autorizada")
        inicio()
    } else {
        saldo += deposito
        println("Depósito realizado com sucesso!")
        inicio()
    }
}

fun fazerSaque() {
    if (validarSenha()) {
        print("Qual o valor para saque? ")
        val saque = readlnOrNull()?.toDoubleOrNull()

        // Validações: valor > 0 e valor <= saldo
        if (saque == null || saque <= 0 || saque > saldo) {
            println("Operação não autorizada")
        } else {
            saldo -= saque
            println("Saque realizado com sucesso!")
        }
        inicio()
    } else {
        fazerSaque()
    }
}

fun fazerTransferencia() {
    if (validarSenha()) {
        print("Informe o número da conta destino: ")
        val conta = readlnOrNull()?.toIntOrNull()

        if (conta == null) {
            println("Operação não autorizada (Conta deve ser apenas números)")
            inicio()
        } else {
            print("Qual o valor da transferência? ")
            val valorTransf = readlnOrNull()?.toDoubleOrNull()

            if (valorTransf == null || valorTransf <= 0 || valorTransf > saldo) {
                println("Operação não autorizada")
            } else {
                saldo -= valorTransf
                println("Transferência realizada com sucesso!")
            }
            inicio()
        }
    } else {
        fazerTransferencia()
    }
}

fun erro() {
    // Ajuste da ortografia conforme solicitado
    println("Por favor, informe um número entre 1 a 6.")
    inicio()
}

fun sair() {
    print("Você deseja sair? (S/N) ")
    val confirma = readlnOrNull().orEmpty().uppercase()

    when (confirma) {
        "S" -> {
            println("$nome, foi um prazer ter você por aqui!")
            exitProcess(0)
        }
        "N" -> inicio()
        else -> sair()
    }
}