package pixelpark.api.region

import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.data.getValue
import com.varabyte.kobweb.api.http.HttpMethod
import com.varabyte.kobweb.api.http.setBodyText
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import pixelpark.regions.RegionRepository

@Api("/region/get/all")
fun getRegionList(ctx: ApiContext) {
    if (ctx.req.method != HttpMethod.GET) return

    val repo = ctx.data.getValue<RegionRepository>()
    ctx.res.setBodyText(Json.encodeToString(repo.getRegions()))
}