package pixelpark.api.region

import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.data.getValue
import com.varabyte.kobweb.api.http.HttpMethod
import com.varabyte.kobweb.api.http.setBodyText
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import pixelpark.regions.RegionRepository

@Api("/region/get")
fun getRegion(ctx: ApiContext) {
    if (ctx.req.method != HttpMethod.GET) return
    val regionId = ctx.req.params["id"] ?: return

    val repo = ctx.data.getValue<RegionRepository>()
    ctx.res.setBodyText(Json.encodeToString(repo.getRegion(regionId)))
}