package cz.uhk.umte.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CharacterInfoResponse(

    var characterId: String,

    @SerialName("name")
    val name: String,

    @SerialName("description")
    val description: String,

    @SerialName("weapon")
    val weapon: String,

    @SerialName("vision")
    val vision: String,

    @SerialName("affiliation")
    val affiliation: String,

    @SerialName("nation")
    val nation: String,

    @SerialName("rarity")
    val rarity: Int
)