package pixelpark.api

import com.varabyte.kobweb.browser.api
import kotlinx.browser.window
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import pixelpark.model.Region

object RegionApi {
    suspend fun getRegions(): List<Region> {
        return window.api.get("region/get/all").let { bytes ->
            Json.decodeFromString(bytes.decodeToString())
        }
    }

    suspend fun getRegion(id: String): Region {
        return window.api.get("region/get?id=$id").let { bytes ->
            Json.decodeFromString(bytes.decodeToString())
        }
    }
}