package br.com.alura.alugames.main

import br.com.alura.alugames.model.Game
import br.com.alura.alugames.model.Gamer
import br.com.alura.alugames.model.InfoGame
import br.com.alura.alugames.service.ConsumoApi
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.net.http.HttpResponse.BodyHandlers

fun main() {

    ConsumoApi().printGame()

}