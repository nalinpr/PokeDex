package hu.ait.genonepokedex.data

data class PokemonResults(
    val abilities: List<Abilities>?,
    val base_experience: Number?,
    val forms: List<Forms>?,
    val game_indices: List<PokeGameIndices>?,
    val height: Number?, val held_items: List<Any>?,
    val id: Number?, val is_default: Boolean?,
    val location_area_encounters: String?,
    val moves: List<Any>?, val name: String?,
    val order: Number?, val species: Species?,
    val sprites: Sprites?,
    val stats: List<Stats>?,
    val types: List<Types>?,
    val weight: Number?
)

data class Abilities(
    val ability: Ability?,
    val is_hidden: Boolean?,
    val slot: Number?
)

data class Ability(
    val name: String?,
    val url: String?
)

data class Forms(
    val name: String?,
    val url: String?
)

data class PokeGameIndices(
    val game_index: Number?,
    val version: Version?
)

data class Move(
    val name: String?,
    val url: String?
)

data class MoveLearnMethod(
    val name: String?,
    val url: String?
)

data class Moves(
    val move: Move?,
    val version_group_details: List<VersionGroupDetails>?
)

data class Species(
    val name: String?,
    val url: String?
)

data class Sprites(
    val back_default: String?,
    val back_female: Any?,
    val back_shiny: String?,
    val back_shiny_female: Any?,
    val front_default: String?,
    val front_female: Any?,
    val front_shiny: String?,
    val front_shiny_female: Any?
)

data class Stat(
    val name: String?,
    val url: String?
)

data class Stats(
    val base_stat: Number?,
    val effort: Number?,
    val stat: Stat?
)

data class Type(
    val name: String?,
    val url: String?
)

data class Types(
    val slot: Number?,
    val type: Type?
)

data class Version(
    val name: String?,
    val url: String?
)

data class VersionGroup(
    val name: String?,
    val url: String?
)

data class VersionGroupDetails(
    val level_learned_at: Number?,
    val move_learn_method: MoveLearnMethod?,
    val version_group: VersionGroup?
)

