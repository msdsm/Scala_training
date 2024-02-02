package masuda.yuya

import scala.math.Ordering

object MySort{
    import java.time.LocalDate

    // 学生のテストの結果
    case class Score(
        name:    String,    // 学生の名前
        english: Int,       // 英語の点数
        math:    Int,       // 数学の点数
        science: Int,       // 理科の点数
        date:    LocalDate  // 受験日
    )

    val scoreOfAlice   = Score(name = "Alice",   english = 77,  math = 74, science = 26, date = LocalDate.of(2020, 1, 30))
    val scoreOfBob     = Score(name = "Bob",     english = 100, math = 74, science = 14, date = LocalDate.of(2020, 1, 26))
    val scoreOfCharlie = Score(name = "Charlie", english = 100, math = 74, science = 99, date = LocalDate.of(2020, 1, 26))
    val scoreOfDave    = Score(name = "Dave",    english = 50,  math = 81, science = 88, date = LocalDate.of(2020, 1, 30))

    val scores: Seq[Score] = Seq(scoreOfAlice, scoreOfBob, scoreOfCharlie, scoreOfDave)

    def main(args: Array[String]): Unit = {
        println("-----問題1-----")
        println(getTotalRanking(scores))
        println()

        println("-----問題2-----")
        val nullsLastOptionStringOrdering: Ordering[Option[String]] = Ordering.by(x => (x.isEmpty, x.getOrElse("")))
        val stroptseq: Seq[Option[String]] = Seq(Some("abc"), None, Some("cde"), None, Some("bcd"), Some("d"))
        println(stroptseq.sorted(nullsLastOptionStringOrdering))
        println()


        println("-----問題3-----")
        println(sortScore(scores, Seq(-5, 1, 2, 3, 4)))
        println()

        println("-----問題4-----")
        println(getDateNameScoreMap(scores)(LocalDate.of(2020, 1, 30))("Alice"))
        println()
    }

    def getTotalRanking(scoreSeq: Seq[Score]): Seq[String] = {
        scoreSeq.sortBy(
            score => -(score.english + score.math + score.science)
            ).map(sc => sc.name)
    }

    def scoreToTuple(score: Score, keys: Seq[Int]): Seq[(String, Int)] = {
        keys.flatMap {
            case 1 => Seq((score.name, 0))
            case 2 => Seq(("", score.english))
            case 3 => Seq(("", score.math))
            case 4 => Seq(("", score.science))
            case 5 => Seq((score.toString, 0))
            case -1 => Seq((score.name.reverse, 0))
            case -2 => Seq(("", -score.english))
            case -3 => Seq(("", -score.math))
            case -4 => Seq(("", -score.science))
            case -5 => Seq((score.toString.reverse, 0))
            case _ => Seq(("", 0))
        }
    }
    def sortScore(scoreSeq: Seq[Score], keys: Seq[Int]): Seq[Score] = {
        if (keys.isEmpty) {
            scoreSeq // キーが指定されていない場合はそのまま返す
        }else{
            implicit val ordering: Ordering[Seq[(String, Int)]] = Ordering.Iterable(Ordering.Tuple2(Ordering.String, Ordering.Int)).asInstanceOf[Ordering[Seq[(String, Int)]]]
            scoreSeq.sortBy(x => scoreToTuple(x, keys))
        }
    }

    def getDateNameScoreMap(scoreSeq: Seq[Score]): Map[LocalDate, Map[String, Int]] = {
        scoreSeq
        .groupBy(_.date)
        .mapValues { scoresOnDate =>
          scoresOnDate
            .groupBy(_.name)
            .mapValues { studentScores =>
              studentScores.map(score => score.english + score.math + score.science).max
            }
        }
    }
}
