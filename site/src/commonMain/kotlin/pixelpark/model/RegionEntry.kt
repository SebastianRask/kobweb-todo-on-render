package pixelpark.model

import kotlinx.serialization.Serializable

@Serializable
data class RegionEntry(
    val region: Region,
    val kommune: Kommune,
    val postNumber: PostNumber
)

@Serializable
data class Region(val id: String, val name: String)

@Serializable
data class Kommune(val id: String, val name: String)

@Serializable
data class PostNumber(val name: String, val postNumber: String)

@Serializable
data class City(val name: String, val postNumbers: List<String>)

fun Iterable<PostNumber>.toCities() = this.groupBy { it.name }
    .map { keyValue -> City(keyValue.key, keyValue.value.map { it.postNumber }.distinct().sorted()) }

fun Iterable<RegionEntry>.cities() = this.map { it.postNumber }.toCities()