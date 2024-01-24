package masuda.yuya

object MyTraitAbstractClass{
    def main(args: Array[String]): Unit = {

        // 抽象クラス
        abstract class Shape(height: Int){
            def calculate(): Int // abstract method
        }

        // 直方体クラス
        case class Cuboid(
            height: Int,
            vertical: Int,
            horizontal: Int
        )extends Shape(height = height){
            override def calculate(): Int = {
                height * vertical * horizontal
            }
        }

        //四角錐クラス
        case class Pyramid(
            height: Int,
            vertical: Int,
            horizontal: Int
        )extends Shape(height = height){
            override def calculate(): Int = {
                height * vertical * horizontal / 3
            }
        }

        //test
        println(Cuboid(2, 3, 4).calculate()) // 24
        println(Pyramid(2, 3, 4).calculate()) // 8
    }
}