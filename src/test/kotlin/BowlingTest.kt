import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

// Press '⌘ N' within the test class and select Test Function to create a new test with the IDE
class BowlingTest {
    private val bowling = Bowling()

    @Test
    fun `12 strikes`() {
//        X X X X X X X X X X X X (12 rolls: 12 strikes) = 10 frames * 30 points = 300
        val result = bowling.game(
            turns = listOf(
                listOf('X'),
                listOf('X'),
                listOf('X'),
                listOf('X'),
                listOf('X'),
                listOf('X'),
                listOf('X'),
                listOf('X'),
                listOf('X'),
                listOf('X', 'X', 'X')
            )
        )

        assertEquals(expected = 300, actual = result)
    }

    @Test
    fun `10 pairs of 9 and miss`() {
        // 9- 9- 9- 9- 9- 9- 9- 9- 9- 9- (20 rolls: 10 pairs of 9 and miss) = 10 frames * 9 points = 90
        val result = bowling.game(
            turns = listOf(
                listOf(9, '-'),
                listOf(9, '-'),
                listOf(9, '-'),
                listOf(9, '-'),
                listOf(9, '-'),
                listOf(9, '-'),
                listOf(9, '-'),
                listOf(9, '-'),
                listOf(9, '-'),
                listOf(9, '-')
            )
        )

        assertEquals(expected = 90, actual = result)
    }

    @Test
    fun `10 pairs of 5 and spare, with a final 5`() {
        // 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5 (21 rolls: 10 pairs of 5 and spare, with a final 5) = 10 frames * 15 points = 150
        val result = bowling.game(
            turns = listOf(
                listOf(5, '/'),
                listOf(5, '/'),
                listOf(5, '/'),
                listOf(5, '/'),
                listOf(5, '/'),
                listOf(5, '/'),
                listOf(5, '/'),
                listOf(5, '/'),
                listOf(5, '/'),
                listOf(5, '/', 5)
            )
        )
        assertEquals(expected = 150, actual = result)
    }
}