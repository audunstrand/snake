package snake

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


internal class BoardTest {


    @Test
    fun testWalls() {
        val board = Board(10, 1)
        assertTrue(board.isWall(Point(0, 0)))
        assertTrue(board.isWall(Point(10, 10)))
    }

    @Test
    fun testFood() {
        val board = Board(2, 100)
        assertTrue(board.isFood(Point(1, 1)))
        board.removeFood(Point(1, 1))
        assertTrue(board.noMoreFood())
    }

    @Test
    fun testValidMoved() {
        val board = Board(4, 0)
        val snake = Snake(start = Point(3, 1))
        val validMoves = board.validMoves(snake)
        assertEquals(1, validMoves.size)
        assertTrue(Game.TURN.RIGHT in validMoves)
    }
}