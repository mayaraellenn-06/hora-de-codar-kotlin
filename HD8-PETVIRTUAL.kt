class VirtualPet(val nome: String) {
    var nivelDeFome = 50
    var nivelFelicidade = 50
    var nivelCansaco = 0
    var idade = 0

    var vontadeBanheiro = 0
    var nivelSujeira = 0


    fun alimentar(tipo: Int) {
        when (tipo) {
            1 -> {
                println("🍲 $nome comeu um belo prato de arroz e feijão e depois uma pizza portuguesa de 8 pedaços!")
                nivelDeFome -= 30
                vontadeBanheiro += 20
            }
            2 -> {
                println("🍰 $nome comeu um pedaço de bolo! Ele amou, mas quer ir ao banheiro logo.")
                nivelDeFome -= 15
                nivelFelicidade += 10
                vontadeBanheiro += 40
            }
            3 -> {
                println("🥤 $nome tomou um suco refrescante de limonada suiça.")
                nivelDeFome -= 5
                vontadeBanheiro += 30
            }
        }
        if (nivelDeFome < 0) nivelDeFome = 0
    }

    fun brincar(opcao: Int) {
        when (opcao) {
            1 -> { // Bola
                println("⚽ $nome correu atrás da bola! Ficou muito feliz, mas suou bastante.")
                nivelFelicidade += 20
                nivelCansaco += 15
                nivelSujeira += 25
            }
            2 -> { // Videogame
                println("🎮 $nome jogou videogame. Divertido, mas cansa a vista!")
                nivelFelicidade += 15
                nivelCansaco += 25
                nivelSujeira += 5
            }
        }
    }

    fun descansar(horas: Int) {
        if (horas >= 8) {
            nivelCansaco = 0
            println("😴 $nome dormiu um sono profundo de $horas horas e acordou zerado!")
        } else {
            val recuperacao = horas * 12 
            nivelCansaco -= recuperacao
            if (nivelCansaco < 0) nivelCansaco = 0
            println("🛌 $nome descansou por $horas horas.")
        }
    }

    fun irAoBanheiro() {
        println("🚽 $nome se sente muito mais leve agora!")
        vontadeBanheiro = 0
    }

    fun darBanho() {
        println("🧼 $nome está limpinho e cheiroso!")
        nivelSujeira = 0
    }

    fun verificarStatus() {
        println("\n--- 📊 STATUS DE $nome ---")
        println("🎂 Idade: $idade/50 | 🍖 Fome: $nivelDeFome/100")
        println("😊 Felicidade: $nivelFelicidade/100 | 💤 Cansaço: $nivelCansaco/100")
        println("🚽 Banheiro: $vontadeBanheiro/100 | 🧼 Sujeira: $nivelSujeira/100")
        println("--------------------------")
    }

    fun passarTempo(): Boolean {
        nivelDeFome += 5
        nivelFelicidade -= 5
        nivelCansaco += 5
        idade += 1

        if (nivelDeFome >= 100 || nivelCansaco >= 100 || nivelFelicidade <= 0 || vontadeBanheiro >= 100 || nivelSujeira >= 100) {
            println("\n💀 FIM DE JOGO! Você não cuidou bem das necessidades de $nome.")
            if (vontadeBanheiro >= 100) println("Motivo: Explosão intestinal! 🚽")
            if (nivelSujeira >= 100) println("Motivo: Infestação de pulgas! 🦠")
            return false
        }

        if (idade >= 50) {
            println("\n🏆 VITÓRIA! $nome chegou aos 50 anos!")
            return false
        }
        return true
    }
}

fun main() {
    println("👾 BEM-VINDO AO SIMULADOR DO SEU BICHINHO VIRTUAL 👾")
    print("Dê um nome ao seu pet: ")
    val pet = VirtualPet(readLine() ?: "Sir Byte-a-Lot")
    var jogoAtivo = true

    while (jogoAtivo) {
        println("\nO que deseja fazer?")
        println("1. 🍴 Alimentar | 2. ⚽ Brincar | 3. 😴 Descansar | 4. 🚽 Banheiro | 5. 🧼 Banho | 6. 📊 Status | 7. Sair")

        when (readLine()?.toIntOrNull()) {
            1 -> {
                println("\nO que servir?\n1. Salgado | 2. Doce | 3. Bebida")
                val tipo = readLine()?.toIntOrNull() ?: 1
                pet.alimentar(tipo)
            }
            2 -> {
                println("\nQual brincadeira?\n1. Bola | 2. Videogame")
                val jogo = readLine()?.toIntOrNull() ?: 1
                pet.brincar(jogo)
            }
            3 -> {
                print("\nQuantas horas de sono? ")
                val h = readLine()?.toIntOrNull() ?: 1
                pet.descansar(h)
            }
            4 -> pet.irAoBanheiro()
            5 -> pet.darBanho()
            6 -> pet.verificarStatus()
            7 -> return
            else -> println("Opção inválida!")
        }
        jogoAtivo = pet.passarTempo()
    }
}