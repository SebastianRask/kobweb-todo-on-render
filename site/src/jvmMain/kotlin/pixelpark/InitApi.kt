package pixelpark

import com.varabyte.kobweb.api.data.add
import com.varabyte.kobweb.api.init.InitApi
import com.varabyte.kobweb.api.init.InitApiContext
import pixelpark.regions.RegionRepository

@InitApi
fun initApi(ctx: InitApiContext) {
    ctx.data.add(RegionRepository())
}