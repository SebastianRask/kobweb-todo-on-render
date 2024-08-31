package pixelpark.api

import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.http.setBodyText

@Api(routeOverride = "parks")
suspend fun parks(ctx: ApiContext) {
    val query = ctx.req.params["query"] ?: ""
    ctx.res.setBodyText(query)
}

