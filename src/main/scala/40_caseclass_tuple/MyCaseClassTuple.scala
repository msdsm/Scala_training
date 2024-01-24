package masuda.yuya

object MyCaseClassTuple{
    def main(args: Array[String]): Unit = {
        //1. 会社のtuple
        val com: (String, String, String) = 
        (
            "nextbeat",
            "03-5423-6131",
            "東京都渋谷区恵比寿4丁目9-10"
        )

        //2. 電話番号取得
        println(com._2) // 03-5423-6131

        //3. case class
        case class Company(
            name: String,
            phone: String,
            address: String
        )

        //4. インスタンス生成
        val com2 = Company(
            "nextbeat",
            "03-5423-6131",
            "東京都渋谷区恵比寿4丁目9-10"
        )

        //5. 会社名変更
        println(com2.copy(name="ネクストビート")) //Company(ネクストビート,03-5423-6131,東京都渋谷区恵比寿4丁目9-10)
    }
}