import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BowlingTest {

    private val bowling = Bowling()

    @Test
    internal fun `a spare score is calculated correctly when the next roll is a miss`() {

        val result = bowling.game(
            turns = listOf(
                listOf('-', '/'),
                listOf('-', '-')
            )
        )

        assertEquals(expected = 10, actual = result)

    }

    @Test
    internal fun `a strike score is calculated correctly when the next two rolls are misses`() {

        val result = bowling.game(
            turns = listOf(
                listOf('X'),
                listOf('-', '-')
            )
        )

        assertEquals(expected = 10, actual = result)

    }

    @Test
    internal fun `a strike score is calculated correctly when the latest turn has at least two rolls`() {

        val result = bowling.game(
            turns = listOf(
                listOf('X'),
                listOf(6, 3)
            )
        )

        assertEquals(expected = 28, actual = result)

    }

    @Test
    internal fun `a strike score is calculated correctly when the latest turn has only one roll`() {

        val result = bowling.game(
            turns = listOf(
                listOf('X'),
                listOf('X'),
                listOf('-', 3)
            )
        )

        assertEquals(expected = 36, actual = result)

    }

    @Test
    internal fun `a spare score is calculated correctly`() {

        val result = bowling.game(
            turns = listOf(
                listOf(3, '/'),
                listOf(5)
            )
        )

        assertEquals(expected = 20, actual = result)

    }

    @Test
    internal fun `a sequence of 12 strikes has a game score of 300`() {

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
    internal fun `a sequence of 10 pairs of 9 and miss has a game score of 90`() {

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
    internal fun `a sequence of 10 pairs of 5 and spare, with a final 5 has a game score of 150`() {

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
