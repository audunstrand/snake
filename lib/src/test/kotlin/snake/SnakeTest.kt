package snake

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


internal class SnakeTest {

    @Test
    fun testEat() {
        val snake = Snake(Point(3, 3))
        assertEquals(1, snake.body.size)
        snake.eat()
        snake.eat()
        assertEquals(1, snake.body.size)
    }

    @Test
    fun testMove() {
        val snake = Snake(Point(3, 3))

        assertEquals(Point(3, 4), snake.turnRight())
        assertEquals(Point(4, 4), snake.turnLeft())
    }

    @Test
    fun testMoveAndEat() {
        val snake = Snake(Point(3, 3))
        assertEquals(1, snake.body.size)
        snake.eat()
        val head = snake.turnRight()
        assertEquals(2, snake.body.size)
        assertEquals(Point(3, 4), head)
    }

    @Test
    fun testEatsHimself() {
        val snake = Snake(Point(3, 3))
        assertEquals(Point(3, 3), snake.head())
        assertEquals(1, snake.body.size)
        snake.eat()
        snake.turnRight()
        assertFalse(snake.eatsHimself())
        snake.eat()
        snake.turnRight()
        assertFalse(snake.eatsHimself())
        snake.eat()
        snake.turnRight()
        assertFalse(snake.eatsHimself())
        snake.eat()
        snake.turnRight()
        assertTrue(snake.eatsHimself())
    }


}