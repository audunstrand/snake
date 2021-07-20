package snake

import snake.Game.TURN.*
import kotlin.random.Random

class Board(private val size: Int, foodPercent: Int) {
    private val food = generateFood(foodPercent)

    fun isWall(point: Point) = wall(point)
    fun isFood(point: Point) = point in food
    fun noMoreFood() = food.isEmpty()
    fun removeFood(point: Point) = food.remove(point)
    fun validMoves(snake: Snake): List<Game.TURN> =
        listOf(
            NO_TURN to snake.head() + snake.currentDirection,
            LEFT to snake.head() + snake.currentDirection.turnLeft(),
            RIGHT to snake.head() + snake.currentDirection.turnRight()
        ).filter { isValid(it.second, snake) }.map { it.first }.toList().shuffled()

    fun printWith(snake: Snake) {
        var board = ""
        (size downTo 0).forEach { x ->
            (0..size).forEach { y ->
                board += (char(Point(x, y), snake))
            }
            board += "\n"
        }
        print(board).also { Thread.sleep(100) }
    }

    private fun isValid(point: Point, snake: Snake): Boolean = !(snake.inBody(point) || wall(point))
    private fun char(point: Point, snake: Snake): String {
        if (snake.inBody(point)) return " S "
        if (wall(point)) return "###"
        if (food.contains(point)) return " 0 "
        return "   "
    }

    private fun wall(p: Point): Boolean {
        return p.x == 0 || p.x == size || p.y == 0 || p.y == size
    }

    private fun generateFood(percent: Int): MutableList<Point> {
        val list = mutableListOf<Point>()
        (1..size - 1).forEach { x ->
            (1..size - 1)
                .map { y -> Point(x, y) }
                .filter { Random.nextInt(100) < percent }
                .toCollection(list)
        }
        return list
    }


}

