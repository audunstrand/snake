package snake

import org.junit.Test
import snake.Game.TURN.*
import kotlin.random.Random.Default.nextInt

internal class GameTest {
    @Test
    fun RunGame() {
        val game = Game()
        while (game.tick(listOf(RIGHT, LEFT, NO_TURN)[nextInt(0, 3)])){Thread.sleep(100)}
    }


}
