data class TurnResult(
    var totalScore: Int,
    val rollScores: List<Int>,
    val isSpare: Boolean,
    val isStrike: Boolean,
    var isFinalScore: Boolean
)