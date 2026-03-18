fun main(args: Array<String>) {
    val planetas = listOf("Terra", "Marte", "Plutão", "Vênus", "Júpiter", "Saturno")

    print("Digite o nome de um planeta: ")
    val planetaInformado = readln().trim()

    val existe = planetas.any { it.equals(planetaInformado, ignoreCase = true) }

    if (existe) {
        println("Sim! O planeta $planetaInformado está na nossa lista.")
    } else {
        println("Sinto muito, o planeta $planetaInformado não foi encontrado.")
    }
}