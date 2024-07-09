package br.com.alura.alugames.model

import kotlin.random.Random

data class Gamer (
    var _name: String? = null,
    var age: String? = null,
    var email: String? = null,
    private var _internalId: String? = null,
    val games: MutableList<Game?> = mutableListOf()
) {

    var name: String?
        get() = _name
        set(value) {
            _name = value
            if (internalId.isNullOrBlank()) internalId = criarInternalId()
        }

    var internalId: String?
        get() = _internalId
        private set(value) {
            _internalId = value
        }

    constructor(name: String?, email: String?) : this(name, "18+", email)

    init {
        require(!(name.isNullOrBlank())) { "Nome é invalido" }

        this.email = validarEmail()

        internalId = name + criarInternalId()
    }

    private fun criarInternalId(): String {
//      val numberRange = (1000..10000).random()

        val number = Random.nextInt(10000)
        val format = String.format("%04d", number)

        return format
    }

    private fun validarEmail(): String? {
        require(!(this.email.isNullOrBlank())) { "Email vazio" }

        val regex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")

        if (regex.matches(email!!)) return email else return throw IllegalArgumentException("Email inválido")
    }

    companion object {
        fun criarGame(): Gamer {

            println("Vamos criar um jogador." +
                    "\nDeseja igorar a idade? S/N")
            val opcao = readlnOrNull()

            if (opcao.equals("S", ignoreCase = true)) {
                println("Digite o seu nome")
                val name = readlnOrNull()

                println("Digite o seu email")
                val email = readlnOrNull()
                return Gamer(name, email)
            }

            println("Digite o seu nome")
            val name = readlnOrNull()

            println("Digite a data de nascimento")
            val birthDate = readlnOrNull()

            println("Digite o seu email")
            val email = readlnOrNull()

            return Gamer(name, birthDate, email)
        }
    }

}