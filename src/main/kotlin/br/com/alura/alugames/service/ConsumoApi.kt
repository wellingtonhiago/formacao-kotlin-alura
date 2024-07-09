package br.com.alura.alugames.service

import br.com.alura.alugames.model.Game
import br.com.alura.alugames.model.Gamer
import br.com.alura.alugames.model.InfoGame
import br.com.alura.alugames.utils.transformarEmIdade
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class ConsumoApi {

    fun numberGame(): String {
        println("Digite o id do jogo:")
        return readln()
    }

    fun printGame(): Unit {

        val gamer: Gamer = Gamer.criarGame()
        println("++++Cadastro concluído com sucesso!!!++++")
        println(gamer)
        when (gamer.age) {
            "18+" -> println("Idade não informada")
            else -> println("Idade do gamer: ${gamer.age?.transformarEmIdade()}")
        }

        while (true) {
            println(
                "Escolha uma opção:" +
                        "\n1 - Digitar o numero do jogo" +
                        "\n2 - Sair"
            )

            if (readln() == "2") break else {

                val URI_API = "https://www.cheapshark.com/api/1.0/games?id=${numberGame()}"
                val client = HttpClient.newHttpClient()
                val request = HttpRequest.newBuilder()
                    .uri(URI.create(URI_API))
                    .build()
                val response = client.send(request, HttpResponse.BodyHandlers.ofString())
                val json = response.body()

                val gson = Gson()
                val myInfoGame = runCatching {
                    gson.fromJson(json, InfoGame::class.java)
                }

                var myGame: Game? = null

                myInfoGame.onFailure { exeption ->
                    when (exeption) {
                        is JsonSyntaxException -> println(exeption.message)
                        else -> println(exeption.message)
                    }
                    println("Jogo não encontrado. Tente novamente")
                }
                myInfoGame.onSuccess {

                    myGame = Game(
                        title = it.info.title,
                        thumb = it.info.thumb,
                        description = it.info.description
                    )

                    println(myGame)

                    println("Deseja inserir uma descrição personalizada? S/N")
                    val opcao = readln()
                    if (opcao.equals("S", ignoreCase = true)) {
                        println("Digite a descrição:")
                        val descricaoPersonalizada: String? = readln()
                        myGame?.description = descricaoPersonalizada

                    } else myGame?.description = myGame?.title

                    gamer.games.add(myGame)

                }

            }

        }
        println("Jogos buscados")
        println(gamer.games)

        println("Jogos ordenados por títulos")
        gamer.games.sortBy { it?.title }
        gamer.games.forEach {
            println("título: ${it?.title}")
        }

        println("\njogos filtrados:")

        val jogosFiltrados = gamer.games.filter {
            it?.title?.contains("batman", ignoreCase = true) ?: false
        }

        println(jogosFiltrados)

        jogosFiltrados.forEach {
            println(it?.title)
        }

        println("Deseja excluir algum jogo? S/N")

        val opcao = readln()
        if (opcao.equals("S", ignoreCase = true)) {
            println("informe a posição do jogo que deseja excluir:")
            val excluirPosicao = readln()
            gamer.games.removeAt(excluirPosicao.toInt() + 1)
        }

        println("\nlista de jogos atualizada:")
        println(gamer.games)

    }
}