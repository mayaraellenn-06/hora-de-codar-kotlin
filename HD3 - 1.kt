fun main(args: Array<String>) {
    println("BOMBA ATIVADA! Iniciando contagem regressiva...")

    for (segundos in 30 downTo 0) {
        println("Detonação em: $segundos")

        Thread.sleep(1000)
    }

    println("\n💥 EXPLOSÃO 💥")
}