package masuda.yuya

object MySeq{
    def main(args: Array[String]): Unit = {
        val intSeq: Seq[Int] = Seq(0, 1, 2, 3, 4, 5, 6)
        val intSeqSeq: Seq[Seq[Int]] = Seq(Seq(0, 1), Seq(1, 2), Seq(3, 4))
        val intOptSeq: Seq[Option[Int]] = Seq(Some(1), Some(2), None)
        val intOptSeq2: Seq[Option[Int]] = Seq(None)
        val intOptSeq3: Seq[Option[Int]] = Seq(Some(1), Some(3))
        val intOptSeq4: Seq[Option[Int]] = Seq(Some(0), Some(1))
        val intOptSeq5: Seq[Option[Int]] = Seq(Some(12), Some(102))
        val intOptSeq6: Seq[Option[Int]] = Seq(Some(12), Some(5))
        val strSeq: Seq[String] = Seq("abc", "de", "f", "gh", "i")
        val numSeq: Seq[Int] = Seq(1, 100)
        val numSeq2: Seq[Int] = Seq(1, 200, 150)
        val numSeq3: Seq[Int] = Seq(10)
        val numSeq4: Seq[Int] = Seq()
        val numSeq5: Seq[Int] = Seq(0, 1, 2, 3, 4)

        // 問題1確認
        println("-----問題1-----")
        println(seqQuestion1(intSeq))
        println()

        println("-----問題2-----")
        println(seqQuestion2(intSeq))
        println()

        println("-----問題3-----")
        println(seqQuestion3(intSeqSeq))
        println()

        println("-----問題4-----")
        println(seqQuestion4(intOptSeq))
        println(seqQuestion4(intOptSeq2))
        println(seqQuestion4(intOptSeq3))
        println()

        println("-----問題5-----")
        println(seqQuestion5(intOptSeq4))
        println(seqQuestion5(intOptSeq5))
        println(seqQuestion5(intOptSeq6))
        println()

        println("-----問題6-----")
        println(seqQuestion6(strSeq))
        println()

        println("-----問題7-----")
        println(seqQuestion7(numSeq))
        println(seqQuestion7(numSeq2))
        println(seqQuestion7(numSeq3))
        println()

        println("-----問題8-----")
        println(seqQuestion8(numSeq))
        println(seqQuestion8(numSeq2))
        println(seqQuestion8(numSeq3))
        println(seqQuestion8(numSeq4))
        println()

        println("-----問題9-----")
        println(seqQuestion9(numSeq2))
        println(seqQuestion9(numSeq5))
        println()

        println("-----問題10-----")
        println(seqQuestion10(numSeq))
        println(seqQuestion10(numSeq2))
        println(seqQuestion10(numSeq4))
        println()

        println("-----問題11-----")
        println(reverse(numSeq5))
        println()

        println("-----問題12-----")
        println(sum(numSeq2))
        println(sum(numSeq3))
        println(sum(numSeq4))
        println()

        println("-----問題13-----")
        println(sum2(numSeq2))
        println(sum2(numSeq3))
        //println(sum2(numSeq4)) // error
        println()

        println("-----問題14-----")
        println(Seq(3 * 4, 4 * 2, 4 * 1).min)
        println()

        println("-----問題15-----")
        println(Seq(3 * 4, 4 * 2, 4 * 1).max)
    }

    //問題1
    /*
    Seq[Int]型である、numSeqが引数として渡される
    seqQuestion1メソッドがあります。
    numSeqの要素のうち、3の倍数の要素のみ
    0に変換する関数を実装してください。
    */
    def seqQuestion1(numSeq: Seq[Int]): Seq[Int] = {
        numSeq.collect{
            case num if num % 3 == 0 => 0
            case num if num % 3 !=0 => num
        }
    }

    /*
    問題2
    Seq[Int]型である、numSeqが引数として渡される
    seqQuestion2メソッドがあります。 
    numSeqのうち3の倍数のみを返すメソッドを
    filterを使って作成してください。
    */
    def seqQuestion2(numSeq: Seq[Int]): Seq[Int] = {
        numSeq.filter(num => num % 3 == 0)
    }

    /*
    問題3
    Seq[Seq[Int]]型である、numSeqSeqが引数として渡される
    seqQuestion3メソッドがあります。 
    numSeqSeqのうち3の倍数を含むSeqのみを返すメソッドを
    filterとexistsを使って作成してください
    */
    def seqQuestion3(numSeqSeq: Seq[Seq[Int]]): Seq[Seq[Int]] = {
        numSeqSeq.filter(numSeq => numSeq.exists(num => num % 3 == 0))
    }

    /*
    問題4
    Seq[Int]型である、numSeqが引数として渡される
    seqQuestion4メソッドがあります。 
    numSeqに偶数が含まれていればその最初の値を、
    含まれていない場合は-1を返すメソッドを
    findを使って作成してください。
    */
    def seqQuestion4(numOptSeq: Seq[Option[Int]]): Any = {
        numOptSeq.find(
            numOpt => numOpt.getOrElse(1) % 2 == 0
            ).flatten.getOrElse(-1)
    }

    /*
    問題5
    Seq[Option[Int]]型である、
    numOptSeqが引数として渡される
    seqQuestion5メソッドがあります。 
    numOptSeqのうち、数値0を含む最初のOption[Int]
    を返すメソッドをfindとcontainsを使って
    作成してください。 数値0がない場合はNoneを返す用に実装してください。
    例） numOptSeq = Seq(Some(12), Some(102))の場合は、
    Some(102)が戻されます。
    */
    def seqQuestion5(numOptSeq: Seq[Option[Int]]): Option[Int] = {
        numOptSeq.find(numOpt => numOpt.toString.contains('0')).flatten
    }

    /*問題6
    Seq[String]型である、
    strSeqが引数として渡される
    seqQuestion6メソッドがあります。 
    strSeqのうち文字列の長さが
    2以上であるものの末尾に"x”を追加し、
    それらのみを含むSeqを返すメソッドを、
    collectを使って作成してください。
    */
    def seqQuestion6(strSeq: Seq[String]): Seq[String] = {
        strSeq.collect{
            case str if str.length >= 2 => str + "x"
        }
    }

    /*問題7
    Seq[Int]型であるnumSeqが引数として渡される
    seqQuestion7メソッドがあります。 
    numSeqのうち値が100以上である最初のIntを2倍した値
    を返すメソッドを作成してください。 
    100以上の値がない場合は0を返してください。 
    メソッドはcollectFirstを使って作成してください。
    */
    def seqQuestion7(numSeq: Seq[Int]): Int = {
        numSeq.collectFirst{
            case num if num >= 100 => num * 2
        }.getOrElse(0)
    }

    /*
    問題8
    Seq[Int]であるnumSeqが引数として渡される
    seqQuestion8メソッドがあります。 
    このうち先頭の値と末尾の値を足し合わせた値(Int)を、 
    Seqが空である場合は0を返すメソッドを
    headOptionとlastOptionを用いて作成してください。
    */
    def seqQuestion8(numSeq: Seq[Int]): Int = {
        numSeq.headOption.getOrElse(0) + numSeq.lastOption.getOrElse(0)
    }

    /*
    問題9
    Seq[Int]であるnumSeqが引数として渡される
    seqQuestion9メソッドがあります。 
    このうち先頭の値と末尾の値を除いた
    Seq[Int]を取得するメソッドを
    initとtailを用いて作成してください。
    */
    def seqQuestion9(numSeq: Seq[Int]): Seq[Int] = {
        numSeq.init.tail
    }

    /*
    問題10
    Seq[Int]型である、numSeqが引数として渡される
    seqQuestion10メソッドがあります。 
    numSeqの要素数が3以上の場合、
    最初の値と最後の値を足した値を返すメソッドを作成してください。 
    numSeqが要素が2以下、1以上の場合は0を、
    numSeqが空の場合は-1を返してください。 
    メソッドの作成にはmatch式と:+, +:などを用いてください。
    */
    def seqQuestion10(numSeq: Seq[Int]) : Int = {
        numSeq match{
            case h +: h2 +: l :+ t => h + t
            case h +: l => 0
            case Nil => -1
        }
    }

    /*
    問題11
    foldLeftを用いて、Seqの要素を反転させる
    次のシグニチャを持ったメソッド
    reverseを実装してみましょう。
    */
    def reverse[T](list: Seq[T]): Seq[T] = {
        list.foldLeft(Seq.empty[T])((acc, elem) => elem +: acc)
    }

    /*
    問題12
    Seqの全ての要素を掛け合わせるメソッドsumを
    foldRightを用いて実装してみましょう。
    Seqが空のときは1を返してみましょう。
    */
    def sum(numSeq: Seq[Int]): Int = {
        numSeq.foldRight(1)((elem, acc) => elem * acc)
    }

    /*問題13
    問題12でreduceを使ってSeqが空の場合、エラーを吐くようにしましょう。
    */
    def sum2(numSeq: Seq[Int]): Int = {
        numSeq.reduce((acc, elem) => acc * elem)
    }

    /*問題14
    */

    /*問題15
    */
}
