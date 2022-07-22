data class TurnResult(
    val score: Int,
    val isSpare: Boolean,
    val isStrike: Boolean
)

class Bowling {
    companion object {
        const val TOTAL_TURNS = 10
    }

    fun game(turns: List<List<Any>>): Int {
        val turnResultsList = mutableListOf<TurnResult>()

        for (turn in turns) {
            val turnResult = getTurnResult(turn = turn)
            turnResultsList.add(turnResult)
            updatePreviousTurnResults(turnResultsList)
        }

        return calculateTotalGameScore(turnResultsList)
    }

    private fun getTurnResult(turn: List<Any>): TurnResult {
        var score = 0
        var isSpare = false
        var isStrike = false

        for (roll in turn) {
            when (roll) {
                // Strike
                'X' -> {
                    score += 10
                    isStrike = true
                }
                // Spare
                '/' -> {
                    score = 10
                    isSpare = true
                }
                // Pins knocked down
                is Int -> {
                    score += roll
                }
                // Miss
                '-' -> {
                    // no-op
                }
            }
        }

        return TurnResult(score = score, isSpare = isSpare, isStrike = isStrike)
    }

    private fun updatePreviousTurnResults(turnResultsList: List<TurnResult>): MutableList<TurnResult> {
        val updatedTurnResults = turnResultsList as MutableList<TurnResult>
        updatedTurnResults.reversed().let { turnResults ->
            val mostRecentResult = turnResults[0]
            if (turnResults.size > 1) {
                val previousResult = turnResults[1]
                if (previousResult.isSpare || previousResult.isStrike) {
                    println("Previous result was a spare or strike")
                }
            }
            if (turnResults.size > 2) {
                val secondPreviousResult = turnResults[2]
                if (secondPreviousResult.isStrike) {
                    println("Second previous result was a strike")
                }
            }
            println()
        }
        return updatedTurnResults
    }

    private fun calculateTotalGameScore(turnResultsList: List<TurnResult>): Int {
        var totalScore = 0

        for (turnResult in turnResultsList) {
            totalScore += turnResult.score
        }

        return totalScore
    }
}