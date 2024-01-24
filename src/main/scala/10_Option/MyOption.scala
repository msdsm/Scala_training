package masuda.yuya

object MyOption{
    def main(args: Array[String]): Unit = {
        // 問題1確認
        val x: Option[Int] = None
        val y: Option[Int] = Some(100)
        println("-----問題1-----")
        println(optionQuestion1(x))
        println(optionQuestion1(y))
        println()

        println("-----問題2-----")
        println(optionQuestion2(x))
        println(optionQuestion2(y))
        println()

        println("-----問題3-----")
        /*問題3
        val strOpt: Option[String] = Some("")
        を定義し、それに対して
        isEmpty, isDefined を実行した場合に
        どのような挙動をするか確かめてください。
        */
        val strOpt: Option[String] = Some("")
        println(strOpt.isEmpty) //false
        println(strOpt.isDefined) // true
        println()

        println("-----問題4-----")
        val x4: Option[Option[Int]] = None
        val y4: Option[Option[Int]] = Some(Some(4))
        println(optionQuestion4(x4))
        println(optionQuestion4(y4))
    }

    //問題1
    /*
    Option[Int]型である、numが引数として渡されるメソッド
    optionQuestion1があります。Someの場合はその値を、
    Noneの場合は０を返却するメソッドを、
    getOrElseメソッドを使って作成してください。
    */
    def optionQuestion1(num: Option[Int]): Int = {
        num.getOrElse(0)
    }

    //問題2
    /*
    Option[Int]型である、numが引数として渡される
    optionQuestion2メソッドがあります。
    Someの場合はその値に3を足したSome[Int]を、
    Noneの場合はそのままNoneを返却するメソッドを、
    mapメソッドを使って作成してください。
    */
    def optionQuestion2(num: Option[Int]): Option[Int] = {
        num.map(i => i+3)
    }

    /*問題4
    Option[Option[Int]]型である、
    numが引数として渡されるoptionQuestion4メソッドがあります。
    Someの場合はその値を2倍したSome[Int]を、
    Noneの場合はそのままNoneを返却するメソッドを作成してください。
    */
    def optionQuestion4(num: Option[Option[Int]]): Option[Int] ={
        num.flatten.map(i => i*2)
    }
}
