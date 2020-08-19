package taxipark

/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> =
        allDrivers.minus(trips.map { it.driver })
/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> =
        allPassengers.filter { pass -> trips.count { t -> t.passengers.contains(pass) } >= minTrips }.toSet()

/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> =
        allPassengers.filter { pass -> trips.count { t -> t.driver == driver && pass in t.passengers } > 1 }.toSet()

/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> =
        allPassengers.filter {
            pass -> trips.count { t -> pass in t.passengers && t.discount != null } >
                trips.count { t -> pass in t.passengers && t.discount == null } }
                .toSet()

/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {
//    val maxDur = trips.map { t -> t.duration / 10 }.groupingBy { it }.eachCount().maxBy { it.value }?.key
//    return if (maxDur != null) (maxDur * 10)..(maxDur * 10 + 9) else null
    return trips
            .groupBy {
                val start = it.duration / 10 * 10
                val end = start + 9
                start..end
            }.maxBy { (_, ts) -> ts.size }?.key

}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
    if (trips.isEmpty()) return false
    // List of drivers with total earnings, sorted most to least
//    val byDriver = allDrivers.map { d -> d to trips.filter { t -> t.driver == d }.sumByDouble { it.cost } }.sortedBy { it.second }.reversed()
    val byDriver = trips.groupBy(Trip::driver)
            .map { (_, t) -> t.sumByDouble(Trip::cost) }
            .sortedDescending()
    // 80% of total earnings
    val eighty = byDriver.sum() * 0.8
    // 20% of drivers
    val twenty = (allDrivers.size * 0.2).toInt()
//    var compare = 0.0
    // Add up earnings, one driver at a time
//    byDriver.forEachIndexed { i, it ->
        // If we're over 20% of drivers, it doesn't matter anymore
//        if (i + 1 > twenty) return false
//        compare += it.second
        // If running total has topped 80% of earnings, we're good
//        if (compare >= eighty) return true
//    }
    // shouldn't actually get here...
    return byDriver.take(twenty).sum() >= eighty
}