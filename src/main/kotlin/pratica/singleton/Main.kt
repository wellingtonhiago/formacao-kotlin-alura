package pratica.singleton

fun main() {
    println(Player.Properties.DEFAULT_SPEED)
    println(Player.Factory.create("João").nick)

    val player = Player.Factory.create("João")
    val player2 = Player.Factory.create("Maria")

    val players = arrayListOf(player, player2)

    PlayingField.addPlayer(player)  // Adicione um player à lista

    println(PlayingField.getAllPlayers())
    println(PlayingField.isPlayerInGame(player))
    println(PlayingField.isPlayerInGame(player2))

}