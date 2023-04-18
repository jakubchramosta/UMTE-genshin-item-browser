package cz.uhk.umte.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CharacterInfoResponse(

    @SerialName("name")
    val name: String,

    @SerialName("vision")
    val vision: String
)