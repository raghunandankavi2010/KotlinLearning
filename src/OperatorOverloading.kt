
fun main(){

    val coordinate1 = Coordinate(2,5)
    val coordinate2 = Coordinate(2,6)
    val coordinate3 = coordinate1+coordinate2
    print(coordinate3)


}

data class Coordinate(val x:Int=0, val y:Int=0){
    operator fun plus(coordinate: Coordinate): Coordinate = Coordinate(x+coordinate.x, y+coordinate.y)

    override fun toString():String = "$x $y"
}