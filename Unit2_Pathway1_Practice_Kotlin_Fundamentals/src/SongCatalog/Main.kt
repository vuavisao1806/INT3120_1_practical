package SongCatalog

class Song(
    val title: String,
    val artist: String,
    val yearPublished: Int,
    val playCount: Int
) {
    var isPopular: Boolean = (playCount >= 1000)
        private set


    fun printSongDescription() {
        println("$title, performed by $artist, was released in $yearPublished.")
    }
}


fun main() {
    val songByMtp = Song("Hay trao cho anh", "Son Tung MTP", 2019, 300_000_000)
    songByMtp.printSongDescription()
    println("Is ${songByMtp.title} popular? " + songByMtp.isPopular)
    val songByUnknown = Song("Unknown", "Who", 2025, 100)
    songByUnknown.printSongDescription()
    println("Is ${songByUnknown.title} popular? " + songByUnknown.isPopular)
}