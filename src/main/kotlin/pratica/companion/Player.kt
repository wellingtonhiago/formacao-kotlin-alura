package pratica.companion

class Player(val id: Int) {

    /* se quisermos apenas adicionar algum static às nossas classes,
      podemos usar Companion objects. */
    companion object {
        val defaulSpeed = 5

        fun calcMovePenalty(cell: Int): Int = defaulSpeed * cell
    }

    val novoSpeed = defaulSpeed
}