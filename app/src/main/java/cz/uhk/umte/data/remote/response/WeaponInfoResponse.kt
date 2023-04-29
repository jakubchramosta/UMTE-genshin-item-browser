package cz.uhk.umte.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class WeaponInfoResponse(

    var weaponId: String = "",

    @SerialName("name")
    val name: String,

//    If uncommented throws -> kotlinx.serialization.MissingFieldException:
//    @SerialName("baseAttack")
//    val baseAttack: Int?,

    @SerialName("type")
    val type: String?,

//    If uncommented throws -> kotlinx.serialization.MissingFieldException:
//    @SerialName("description")
//    val description: String?,

    @SerialName("subStat")
    val subStat: String?,

    @SerialName("passiveDesc")
    val passiveDesc: String?,
)