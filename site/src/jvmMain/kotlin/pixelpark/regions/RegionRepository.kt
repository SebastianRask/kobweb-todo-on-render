package pixelpark.regions

import pixelpark.model.City
import pixelpark.model.Kommune
import pixelpark.model.Region
import pixelpark.model.cities

class RegionRepository {
    private val service = RegionService()

    fun getRegion(id: String): Region = getRegions().first { it.id == id }

    fun getRegions(): List<Region> = service.regionEntries.map { it.region }.distinct()

    fun getKommune(id: String): Kommune = getKommunes().first { it.id == id }

    fun getKommunes(): List<Kommune> = service.regionEntries.map { it.kommune }.distinct()

    fun getCities(): List<City> = service.regionEntries.cities()

    fun getCity(name: String): City = getCities().first { it.name == name}

    fun getCityFromPostNumber(postNumber: String): City = getCities().first { it.postNumbers.contains(postNumber)}

    fun getCitiesInRegion(region: Region) = service.regionEntries
        .filter { it.region == region }
        .cities()

    fun getCitiesInKommune(kommune: Kommune) = service.regionEntries
        .filter { it.kommune == kommune }
        .cities()
}

