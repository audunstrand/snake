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
        val head = snake.move(turn)
        return when {
            snake.eatsHimself() -> false.also { println("eats himself ${points()} points") }
            board.isWall(head) -> false.also { println("hitsWall ${points()} points") }
            board.isFood(head) -> true.also {
                snake.eat()
                board.removeFood(head)
            }
            else -> true
        }.also { board.printWith(snake) }
    }

    enum class TURN {
        RIGHT,
        LEFT,
        NO_TURN
    }
}