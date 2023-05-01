package cz.uhk.umte.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class WeaponInfoResponse(

    var weaponId: String = "",

    @SerialName("name")
    val name: String,

    //TODO:
//    Pokud se odkomentuje -> kotlinx.serialization.MissingFieldException:
//    Některé zbraně nemají description, při serializaci si s tím nedokáže
//    poradit a celý proces serializace selže i když v WeaponIfoResponse je
//    nullable String?
//    @SerialName("baseAttack")
//    val baseAttack: Int?,

    @SerialName("type")
    val type: String?,

    @SerialName("subStat")
    val subStat: String?,

    @SerialName("passiveDesc")
    val passiveDesc: String?,
)