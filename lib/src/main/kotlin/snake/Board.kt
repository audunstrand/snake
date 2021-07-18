package snake

import kotlin.random.Random

class Board(val size: Int, foodPercent: Int) {
    private val food = generateFood(foodPercent)
    private val walls = walls(size)

    fun isWall(point: Point) = point in walls
    fun isFood(point: Point) = point in food
    fun noMoreFood() = food.isEmpty()

    fun validMove(snake: Snake): List<Game.TURN> {
        return listOf(
            Game.TURN.NO_TURN to snake.head() + snake.currentDirection,
            Game.TURN.LEFT to snake.head() + snake.currentDirection.turnLeft(),
            Game.TURN.RIGHT to snake.head() + snake.currentDirection.turnRight()
        ).filter { isValid(it.second, snake) }.map { it.first }.toList().shuffled()
    }

    private fun isValid(point: Point, snake: Snake): Boolean {
        return !(point in snake.body || point in walls)
    }

    fun print(snake: Snake) {
        var board = ""
        (size downTo 0).forEach { x ->
            (0..size).forEach { y ->
                board += (char(Point(x, y), snake))
            }
            board += "\n"
        }
        print(board).also { Thread.sleep(100) }
    }

    private fun char(point: Point, snake: Snake): String {
        if (snake.body.contains(point)) return " S "
        if (walls.contains(point)) return "###"
        if (food.contains(point)) return " 0 "
        return "   "
    }

    private fun walls(size: Int): List<Point> {
        val list = mutableListOf<Point>()
        (0..size).forEach() { x ->
            (0..size)
                .map { y -> Point(x, y) }
                .filter { p -> wall(p) }
                .toCollection(list)
        }
        return list
    }

    private fun wall(p: Point): Boolean {
        return p.x == 0 || p.x == size || p.y == 0 || p.y == size
    }

    private fun generateFood(percent: Int): MutableList<Point> {
        var list = mutableListOf<Point>()
        (1..size).forEach { x ->
            (1..size)
                .map { y -> Point(x, y) }
                .filter { Random.nextInt(100) < percent }
                .toCollection(list)
        }
        return list
    }

    fun removeFood(head: Point) {
        food.remove(head)
    }
}

