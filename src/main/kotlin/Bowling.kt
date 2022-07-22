data class TurnResult(
    var totalScore: Int,
    val rollScores: List<Int>,
    val isSpare: Boolean,
    val isStrike: Boolean,
    var isFinalScore: Boolean
)

class Bowling {
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
        val rollScores = mutableListOf<Int>()
        var isSpare = false
        var isStrike = false

        turn.forEachIndexed { index, roll ->
            when (roll) {
                // Strike
                'X' -> {
                    rollScores.add(index, 10)
                    isStrike = true
                }
                // Spare
                '/' -> {
                    when (index) {
                        1 -> rollScores.add(index, 10 - rollScores[0])
                        2 -> rollScores.add(index, 10 - rollScores[1])
                    }
                    isSpare = true
                }
                // Number of pins knocked down
                is Int -> {
                    rollScores.add(index, roll)
                }
                // Miss
                '-' -> {
                    rollScores.add(index, 0)
                }
            }
        }

        return TurnResult(
            totalScore = rollScores.sum(),
            rollScores = rollScores,
            isSpare = isSpare,
            isStrike = isStrike,
            isFinalScore = !(isSpare || isStrike)
        )
    }

    private fun updatePreviousTurnResults(turnResultsList: List<TurnResult>): MutableList<TurnResult> {
        val updatedTurnResults = turnResultsList as MutableList<TurnResult>
        updatedTurnResults.reversed().let { turnResults ->
            val latestResult = turnResults[0]
            if (turnResults.size > 1) {
                val previousResult = turnResults[1]
                if (previousResult.isSpare && !previousResult.isFinalScore) {
                    turnResults[1].totalScore += latestResult.rollScores[0]
                    turnResults[1].isFinalScore = true
                }

                if (previousResult.isStrike && !previousResult.isFinalScore) {
                    if (latestResult.rollScores.size > 1) {
                        turnResults[1].totalScore += (latestResult.rollScores[0] + latestResult.rollScores[1])
                        turnResults[1].isFinalScore = true
                    }
                }

                if (turnResults.size > 2) {
                    val secondPreviousResult = turnResults[2]
                    if (secondPreviousResult.isStrike && !secondPreviousResult.isFinalScore) {
                        turnResults[2].totalScore += (previousResult.rollScores[0] + latestResult.rollScores[0])
                        turnResults[2].isFinalScore = true
                    }
                }
            }
        }
        return updatedTurnResults
    }

    private fun calculateTotalGameScore(turnResultsList: List<TurnResult>): Int {
        var totalScore = 0

        for (turnResult in turnResultsList) {
            totalScore += turnResult.totalScore
        }

        return totalScore
    }
}