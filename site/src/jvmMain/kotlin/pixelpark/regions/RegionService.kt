package pixelpark.regions

import pixelpark.model.Kommune
import pixelpark.model.PostNumber
import pixelpark.model.Region
import pixelpark.model.RegionEntry

class RegionService {
    val regionEntries by lazy {
        getRegionDataCsvRaw()
            .lines()
            .map {
                val entryData = it.split(",")
                return@map RegionEntry(
                    Region(
                        id = entryData[0],
                        name = entryData[1]
                    ),
                    Kommune(
                        id = entryData[2],
                        name = entryData[3]
                    ),
                    PostNumber(
                        name = entryData[5],
                        postNumber = entryData[4],
                    )
                )
            }
    }
}