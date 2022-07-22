import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

// Press '⌘ N' within the test class and select Test Function to create a new test with the IDE
class BowlingTest {
    private val bowling = Bowling()

    @Test
    fun `a sequence of 12 strikes has a game score of 300`() {
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
    fun `a sequence of 10 pairs of 9 and miss has a game score of 90`() {
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
    fun `a sequence of 10 pairs of 5 and spare, with a final 5 has a game score of 150`() {
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