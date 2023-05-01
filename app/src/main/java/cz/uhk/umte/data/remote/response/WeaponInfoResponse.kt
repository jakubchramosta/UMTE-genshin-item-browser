package cz.uhk.umte.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class WeaponInfoResponse(

    var weaponId: String = "",

    @SerialName("name")
    val name: String,

    @SerialName("baseAttack")
    val baseAttack: Int? = 0,

    @SerialName("type")
    val type: String?,

    @SerialName("subStat")
    val subStat: String?,

    @SerialName("passiveDesc")
    val passiveDesc: String?,
)