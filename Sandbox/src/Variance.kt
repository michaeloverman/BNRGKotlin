class Barrel<in T>(item: T)

fun main(args: Array<String>) {
    var fedoraBarrel: Barrel<Fedora> = Barrel(Fedora("a bland, tasteless gray fedora", 15))
    var lootBarrel: Barrel<Loot> = Barrel(Coin(16))

//    lootBarrel = fedoraBarrel
    fedoraBarrel = lootBarrel
//    lootBarrel.item = Coin(16)
//    val myFedora: Fedora = lootBarrel.item
}