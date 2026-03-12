import kotlin.math.PI
fun main() {
    print("Digite o raio do círculo: ")
    val raio = readln().toDouble()

    // r ao quadrado é raio * raio
    val area = PI * (raio * raio)

    println("A área do círculo é: $area")
}