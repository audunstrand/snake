package snake

import snake.Direction.*


class Snake(start: Point = Point(3, 3), var currentDirection: Direction = UP) {
    fun head() = body.first()
    private fun tail() = body.subList(1, length)

    var body = ArrayDeque(listOf(start))

    private var length = 1
    fun eat() = length++

    fun eatsHimself(): Boolean = head() in tail()
    fun noTurn() = move(currentDirection)
    fun turnRight(): Point = move(currentDirection.turnRight())
    fun turnLeft(): Point = move(currentDirection.turnLeft())


    private fun move(direction: Direction): Point {
        this.currentDirection = direction
        body.addFirst(head().new(direction))
        if (length < body.size) body.removeLast()
        return head()
    }

    override fun toString(): String {
        return body.joinToString { point -> "$point" }
    }
}

enum class Direction(val x: Int, val y: Int) {

    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    fun turnRight(): Direction {
        return when (this) {
            UP -> RIGHT
            RIGHT -> DOWN
            DOWN -> LEFT
            LEFT -> UP
        }
    }

    fun turnLeft(): Direction {
        return when (this) {
            UP -> LEFT
            RIGHT -> UP
            DOWN -> RIGHT
            LEFT -> DOWN
        }
    }
}

data class Point(val x: Int, val y: Int) {

    fun new(direction: Direction): Point {
        return this + direction
    }

    override fun toString(): String {
        return "[$x:$y]"
    }

    operator fun plus(direction: Direction): Point {
        return Point(x + direction.x, y + direction.y)
    }
}



