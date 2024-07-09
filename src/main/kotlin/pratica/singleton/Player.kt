package pratica.singleton

class Player(val nick: String) {

    //Aqui criamos um singleton para as propriedades da classe. é como se fosse um atributo static no Java.
    //Mas a melhor opção é usar o companion object.
    object Properties {
        const val DEFAULT_SPEED = 5

        fun calcMovePenalty(cell: Int): Int = DEFAULT_SPEED * cell
    }

    //Neste exemplo, criamos um singleton adicional que pode criar uma nova instância da classe.
    //Esse padrão é chamado de Factory e pode ser muito útil para casos complexos.
    object Factory {
        fun create(playerNick: String): Player = Player(playerNick)
    }

    val superSpeed = Properties.DEFAULT_SPEED * 2
}