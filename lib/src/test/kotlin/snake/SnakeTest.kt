package snake

import snake.Game.TURN.LEFT
import snake.Game.TURN.RIGHT
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


internal class SnakeTest {

    @Test
    fun testEat() {
        val snake = Snake(Point(3, 3))
        assertEquals(1, snake.length)
        snake.eat()
        snake.eat()
        assertEquals(3, snake.length)
    }

    @Test
    fun testMove() {
        val snake = Snake(Point(3, 3))
        assertEquals(Point(3, 4), snake.move(RIGHT))
        assertEquals(Point(4, 4), snake.move(LEFT))
    }

    @Test
    fun testMoveAndEat() {
        val snake = Snake(Point(3, 3))
        assertEquals(1, snake.length)
        snake.eat()
        val head = snake.move(RIGHT)
        assertEquals(2, snake.length)
        assertEquals(Point(3, 4), head)
        assertTrue { snake.inBody(Point(3, 3)) }
    }

    @Test
    fun testEatsHimself() {
        val snake = Snake(Point(3, 3))
        assertEquals(Point(3, 3), snake.head())
        assertEquals(1, snake.length)
        snake.eat()
        snake.move(RIGHT)
        assertFalse(snake.eatsHimself())
        snake.eat()
        snake.move(RIGHT)
        assertFalse(snake.eatsHimself())
        snake.eat()
        snake.move(RIGHT)
        assertFalse(snake.eatsHimself())
        snake.eat()
        snake.move(RIGHT)
        assertTrue(snake.eatsHimself())
    }


}