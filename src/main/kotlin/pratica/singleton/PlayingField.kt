package pratica.singleton

object PlayingField {

    private val players = arrayListOf<Player>()

    fun addPlayer(player: Player) {
        players.add(player)
    }

    fun getAllPlayers(): ArrayList<Player> {
        return players
    }

    fun isPlayerInGame(player: Player): Boolean {
        return getAllPlayers().contains(player)
    }

    fun startNewGameTurn() {
        val players = getAllPlayers()

        if (players.size < 2) println("The game cannot be continued without players")
        else for (player in players) nextPlayerTurn(player)
    }

    fun nextPlayerTurn(player: Player) {
        if (!isPlayerInGame(player)) println("Current player lost. Next...")
    }
}