package cz.uhk.umte.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class WeaponInfoResponse(

    var weaponId: String = "",

//    var isInCompareList: Boolean = false,

    @SerialName("name")
    val name: String,

//    Pokud se odkomentuje -> kotlinx.serialization.MissingFieldException:
//    Stejný problém jako u description
//    @SerialName("baseAttack")
//    val baseAttack: Int?,

    @SerialName("type")
    val type: String?,

    //TODO
//    Pokud se odkomentuje -> kotlinx.serialization.MissingFieldException:
//    Některé zbraně nemají description, při serializaci si s tím nedokáže
//    poradit a celý proces serializace selže i když v WeaponIfoResponse je
//    nullable String?

//    @SerialName("description")
//    val description: String?,

    @SerialName("subStat")
    val subStat: String?,

    @SerialName("passiveDesc")
    val passiveDesc: String?,
)