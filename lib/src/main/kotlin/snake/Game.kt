package snake


import snake.Game.TURN.*

fun main() {
    val game = Game()
    while (game.tick(game.validMove())) {
        if (game.won()) {
            println("HURRA")
            break
        }
    }
}

class Game(boardSize: Int = 20) {
    private val board = Board(boardSize, 25)
    private val snake = Snake(Point(boardSize / 2, boardSize / 2))

    fun won() = board.noMoreFood()
    fun validMove() = board.validMoves(snake).getOrElse(0) { NO_TURN }

    fun tick(turn: TURN): Boolean {
        val head = snake.move(turn)
        board.print(snake)

        if (snake.eatsHimself()) return false.also { println("eats himself") }
        if (board.isWall(head)) return false.also { println("hitsWall") }
        if (board.isFood(head)) {
            snake.eat()
            board.removeFood(head)
        }
        return true
    }

    enum class TURN() {
        RIGHT,
        LEFT,
        NO_TURN
    }
}