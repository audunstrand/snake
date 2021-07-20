package snake


import snake.Game.TURN.*

fun main() {
    val game = Game()
    while (game.tick(game.validMove())) {
        if (game.won()) {
            println("HURRA ${game.points()} points")
            return
        }
    }
}

class Game(
    boardSize: Int = 20,
    private val board: Board = Board(boardSize, 12),
    private val snake: Snake = Snake(Point(boardSize / 2, boardSize / 2))
) {

    fun points() = snake.length
    fun won() = board.noMoreFood()
    fun validMove() = board.validMoves(snake).getOrElse(0) { NO_TURN }
    fun tick(turn: TURN): Boolean {
        return snake.move(turn).let { newHead ->
            when {
                snake.eatsHimself() -> false.also { println("eats himself ${points()} points") }
                hitsWall(newHead) -> false.also { println("hitsWall ${points()} points") }
                hitsFood(newHead) -> true.also { eat(newHead) }
                else -> true
            }
        }.also { board.printWith(snake) }
    }

    private fun eat(newHead: Point) {
        snake.eat()
        board.removeFood(newHead)
    }

    private fun hitsFood(newHead: Point) = board.isFood(newHead)
    private fun hitsWall(newHead: Point) = board.isWall(newHead)

    enum class TURN {
        RIGHT,
        LEFT,
        NO_TURN
    }
}