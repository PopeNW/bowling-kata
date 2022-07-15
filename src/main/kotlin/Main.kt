fun main() {
    val bowling = Bowling()

    // X X X X X X X X X X X X (12 rolls: 12 strikes) = 10 frames * 30 points = 300
    val game1 = bowling.game(
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
    println("First game score: $game1")

    // 9- 9- 9- 9- 9- 9- 9- 9- 9- 9- (20 rolls: 10 pairs of 9 and miss) = 10 frames * 9 points = 90
    val game2 = bowling.game(
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
    println("Second game score: $game2")

    // 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5 (21 rolls: 10 pairs of 5 and spare, with a final 5) = 10 frames * 15 points = 150
    val game3 = bowling.game(
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
    println("Third game score: $game3")
}