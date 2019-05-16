package hu.ait.genonepokedex.data

data class TypeResults(
    val damage_relations: DamageRelations?,
    val game_indices: List<TypeGameIndices>?,
    val generation: Generation?,
    val id: Number?,
    val move_damage_class: MoveDamageClass?,
    val moves: List<TypeMoves>?,
    val name: String?,
    val names: List<Names>?,
    val pokemon: List<Pokemons>?
)

data class DamageRelations(
    val double_damage_from: List<DoubleDamageFrom>?,
    val double_damage_to: List<DoubleDamageTo>?,
    val half_damage_from: List<HalfDamageFrom>?,
    val half_damage_to: List<HalfDamageTo>?,
    val no_damage_from: List<Any>?,
    val no_damage_to: List<NoDamageTo>?
)

data class DoubleDamageFrom(
    val name: String?,
    val url: String?
)

data class DoubleDamageTo(
    val name: String?,
    val url: String?
)

data class TypeGameIndices(
    val game_index: Number?,
    val generation: Generation?
)

data class Generation(
    val name: String?,
    val url: String?
)

data class HalfDamageFrom(
    val name: String?,
    val url: String?
)

data class HalfDamageTo(
    val name: String?,
    val url: String?
)

data class Language(
    val name: String?,
    val url: String?
)

data class MoveDamageClass(
    val name: String?,
    val url: String?
)

data class TypeMoves(
    val name: String?,
    val url: String?
)

data class Names(
    val language: Language?,
    val name: String?
)

data class NoDamageTo(
    val name: String?,
    val url: String?
)

data class TypePokemon(
    val name: String?,
    val url: String?
)

data class Pokemons(
    val pokemon: TypePokemon?,
    val slot: Number?
)