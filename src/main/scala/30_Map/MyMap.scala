package masuda.yuya

object MyMap{
    def main(args: Array[String]): Unit = {
        val fruits = Map(
            "りんご" -> 100,
            "みかん" -> 80,
            "ぶどう" -> 300
            )
        println("-----問題1-----")
        println(fruits.get("みかん")) // 80
        println()

        println("-----問題2-----")
        println(fruits.get("いちご")) // None
        println(fruits.getOrElse("いちご", 0)) // 0
        println(fruits.getOrElse("りんご", 0)) // 100
        println()

        println("-----問題3-----")
        println(fruits.contains("ぶどう")) // true
        println(fruits.contains("いちご")) // false
        println()

        println("-----問題3-2-----")
        println(checkBothExist(fruits, Seq("ぶどう", "いちご"))) // false
        println(checkBothExist(fruits, Seq("ぶどう", "みかん"))) //true
        println(checkBothExist(fruits, Seq())) // true
        println()

        println("-----問題3-3-----")
        println(checkBothExist2(fruits, Seq("ぶどう", "いちご"))) // false
        println(checkBothExist2(fruits, Seq("ぶどう", "みかん"))) //true
        println(checkBothExist2(fruits, Seq())) // false
        println()

        println("-----問題3-4-----")
        println(checkExist(fruits, Seq("ぶどう", "いちご"))) // true
        println(checkExist(fruits, Seq("ぶどう", "みかん"))) //true
        println(checkExist(fruits, Seq("いちご"))) // false
        println(checkExist(fruits, Seq("みかん"))) //true
        println(checkExist(fruits, Seq())) // false
        println()

        println("-----問題4-----")
        println(fruits.mapValues(_ + "円")) // Map(りんご -> 100円, みかん -> 80円, ぶどう -> 300円)
        println()

        println("-----問題5-----")
        val numbers: Seq[Int] = Seq(1, 2, 3, 4, 5)
        println(numbers.groupBy(_ % 2)) // Map(1 -> List(1, 3, 5), 0 -> List(2, 4))
        println()
    }

    //問題3-2
    def checkBothExist(fruits: Map[String, Int], checkSeq: Seq[String]): Boolean = {
        if (checkSeq.isEmpty) true
        else checkSeq.forall(fruits.contains)
    }

    //問題3-3
    def checkBothExist2(fruits: Map[String, Int], checkSeq: Seq[String]): Boolean = {
        if (checkSeq.isEmpty) false
        else checkSeq.forall(fruits.contains)
    }

    //問題3-4
    def checkExist(fruits: Map[String, Int], checkSeq: Seq[String]): Boolean = {
        checkSeq.exists(fruits.contains)
    }

}