package cz.uhk.umte.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ArtifactsListResponse (

    @SerialName("name")
    val name: String,
)