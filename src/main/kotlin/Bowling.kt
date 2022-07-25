class Bowling {

    companion object {

        const val TOTAL_PINS = 10

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

        val rollScores = mutableListOf<Int>()
        var isSpare = false
        var isStrike = false

        turn.forEachIndexed { index, roll ->
            when (roll) {
                // Strike
                'X' -> {
                    rollScores.add(index, TOTAL_PINS)
                    isStrike = true
                }
                // Spare
                '/' -> {
                    when (index) {
                        1 -> rollScores.add(index, TOTAL_PINS - rollScores[0])
                        2 -> rollScores.add(index, TOTAL_PINS - rollScores[1])
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

        updatedTurnResults.asReversed().let { turnResults ->
            if (turnResults.size > 1) {
                turnResults[1] = updatePreviousResultScore(
                    previousResult = turnResults[1],
                    latestResult = turnResults[0]
                )
            }

            if (turnResults.size > 2) {
                turnResults[2] = updateSecondPreviousResultScore(
                    secondPreviousResult = turnResults[2],
                    previousResult = turnResults[1],
                    latestResult = turnResults[0]
                )
            }
        }

        return updatedTurnResults

    }

    private fun updatePreviousResultScore(previousResult: TurnResult, latestResult: TurnResult): TurnResult {

        if (previousResult.isSpare && !previousResult.isFinalScore) {
            previousResult.totalScore += latestResult.rollScores[0]
            previousResult.isFinalScore = true
        }

        if (previousResult.isStrike && !previousResult.isFinalScore) {
            if (latestResult.rollScores.size > 1) {
                previousResult.totalScore += latestResult.rollScores[0] + latestResult.rollScores[1]
                previousResult.isFinalScore = true
            }
        }

        return previousResult

    }

    private fun updateSecondPreviousResultScore(
        secondPreviousResult: TurnResult,
        previousResult: TurnResult,
        latestResult: TurnResult
    ): TurnResult {

        if (secondPreviousResult.isStrike && !secondPreviousResult.isFinalScore) {
            secondPreviousResult.totalScore += previousResult.rollScores[0] + latestResult.rollScores[0]
            secondPreviousResult.isFinalScore = true
        }

        return secondPreviousResult

    }

    private fun calculateTotalGameScore(turnResultsList: List<TurnResult>): Int {

        var totalScore = 0

        for (turnResult in turnResultsList) {
            totalScore += turnResult.totalScore
        }

        return totalScore

    }

}
