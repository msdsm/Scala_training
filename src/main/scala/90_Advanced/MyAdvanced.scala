package masuda.yuya

import scala.concurrent.Future
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import java.time.LocalDateTime

object MyAdvanced{
    def main(args: Array[String]): Unit = {
        question1(Some(3));
        question1(Some(6));
        question1(Some(11));
        question1(None);

        println(question2(Some(123)))
        println(question2(None))

        println(question3(Seq(1,3,2,5,4)))

        println(question4(Seq(1,3,2,5,4)))

        println(question5(Seq(Some(1), None, Some(2), None)))

        println(question6(Seq(1,3,2,5,4)))

        println(question7(Seq(1,3,2,5,4)))

        println(question8(Seq(1,1,3,2,5,5,4,2)))

        println(question9(
            Seq(Article(1, "a", "a"),
                Article(1, "b", "b"),
                Article(2, "c", "c"),
                Article(3, "d", "d"),
                ),
            Seq(
                ArticleDetail(1, "x"),
                ArticleDetail(2, "y")
                )
        ))

        question10(Set(1, 2, 3, 4), Set(4, 5))

        println(question11(Seq(
            Person(1, "a", Some("M")), 
            Person(2, "b", Some("F")), 
            Person(3, "c", Some("X")), 
            Person(4, "d", None), 
            )
        ))

        println(question12("1,2,3,4,hello"))

        question13(Future(Some("abcdef")))
        question13(Future(None))

        val leftEither: Future[Either[Int, String]] = Future(Left(100))
        val rightEither: Future[Either[Int, String]] = Future(Right("string"))
        question14(leftEither)
        question14(rightEither)

        val futures: Seq[Future[Int]] = Seq(
            Future.successful(1),
            Future.successful(2),
            Future.successful(3)
        )
        val res: Future[Int] = question15(futures)
        res.onComplete{
            case Success(v) => println(v)
            case Failure(e) => println("error"+e)
        }
        question15(Seq()).onComplete{
            case Success(v) => println(v)
            case Failure(e) => println("error"+e)
        }

        println(
            Seq(
                User(1, "a", 18),
                User(2, "b", 18),
                User(3, "c", 18)
                ),
            Seq(
                UserInfo(1, "a@gmail.com", "000-0000-0000"), 
                UserInfo(2, "b@gmail.com", "123-4567-8901")
                )
        )

        val res17: Future[ViewValueUser] = question17(1);
        res17.onComplete{
            case Success(v) => println(v)
            case Failure(e) => println("error"+e)
        }

        implicit val height: Int = 2
        question18(Props(3, 4))

        println(question19(LocalDateTime.now()))

        println(question20(LocalDateTime.now()))
    }
    // 問1
    def question1(numOpt: Option[Int]): Unit = {
        numOpt match {
            case Some(num) if num >= 10 => println("A")
            case Some(num) if num <= 9 && num >= 5 => println("B")
            case Some(num) if num <= 4 => println("C")
            case None => println("D")
        }
    }

    // 問2
    def convertToString(numOpt: Option[Int])(conv: Option[Int] => String): String = conv(numOpt)
    def question2(numOpt: Option[Int]):String = {
       convertToString(numOpt) {
            case Some(num) => num.toString
            case None => "空でした"
        }
    }

    // 問3
    def question3(nums: Seq[Int]): Seq[Int] = nums.sorted

    // 問4
    def question4(nums: Seq[Int]): Seq[Int] = nums.sortWith(_ > _)

    // 問5
    def question5(numOps: Seq[Option[Int]]): Seq[Int] = numOps.flatten

    // 問6
    def question6(nums: Seq[Int]): Seq[(Int,Int)] = nums.map(num => (num,nums.indexOf(num)))

    // 問7
    def question7(nums: Seq[Int]): Int = nums.indexOf(nums.max)

    // 問8
    def question8(nums: Seq[Int]): Seq[Int] = nums.distinct

    // 問9
    case class Article(id: Long, title: String, body: String)
    case class ArticleDetail(articleId: Long, category: String)
    def question9(
        articles: Seq[Article],
        articleDetails: Seq[ArticleDetail]
    ): Seq[(Article, Option[ArticleDetail])] = {
        articles.map { article =>
            val detailOpt = articleDetails.find(_.articleId == article.id)
            (article, detailOpt)
        }
    }

    // 問10
    def question10(set1: Set[Int], set2: Set[Int]): Unit = {
        val diffSet = set1.diff(set2)
        val unionSet = set1.union(set2)
        println("差集合は" + diffSet)
        println("和集合は" + unionSet)
    }

    // 問11
    case class Person(id: Long, name: String, gender: Option[String])
    def question11(people: Seq[Person]): Seq[Person] = people.map(p => Person(p.id, p.name, None))

    // 問12
    def question12(numStr: String): Seq[Option[Int]] = {
        numStr.split(",").map(s => if (s.forall(_.isDigit)) Some(s.toInt) else None)
    }

    // 問13
    def question13(fOps: Future[Option[String]]): Future[Unit] = {
        fOps.map {
            case Some(value) => println(value)
            case None => println("なし")
        }
    }

    // 問14
    def question14(fEith: Future[Either[Int, String]]): Future[Unit] = {
        fEith.map {
            case Right(value) => println(value)
            case Left(num) => println(num)
        }
    }

    def question15(nums: Seq[Future[Int]]): Future[Int] = {
        val zero: Future[Int] = Future.successful(0)
      
        nums.foldLeft(zero) { (accFuture, futureInt) =>
            for {
                acc <- accFuture
                value <- futureInt
            } yield acc + value
        }
    }

    // 問16
    case class User(id: Long, name: String, age: Int)
    case class UserInfo(userId: Long, email: String, phone: String)
    case class ViewValueUser(userId: String, name: String, age: Int, email: String, phone: String)

    def question16(users: Seq[User], userInfoSeq: Seq[UserInfo]): Seq[ViewValueUser] = {
        users.map { user =>
            val userInfoOpt = userInfoSeq.find(_.userId == user.id)
            val email = userInfoOpt.map(_.email).getOrElse("")
            val phone = userInfoOpt.map(_.phone).getOrElse("")
            ViewValueUser(user.id.toString, user.name, user.age, email, phone)
        }
    }

    // 問17
    // ==== DBIO オブジェクト =====
    object Database {
        // 暫定的に、特定のUserを返却するような実装にしている
        def getUser(id: Long): Future[User] = {
            Future.successful(
                User(
                    id   = 1L,
                    name = "nextbeat",
                    age  = 10
                )
            )
        }
  
        // 暫定的に、特定のUserInfoをOption型で返却するような実装にしている
        def getUserInfo(userId: Long): Future[Option[UserInfo]] = {
            Future.successful(
                Some(
                    UserInfo(
                        userId = 1L,
                        email  = "nextbeat@nextbeat.net",
                        phone  = "09000000000"
                    )
                )
            )
        }
    }
    def question17(userId: Long): Future[ViewValueUser] = {
        for {
            user <- Database.getUser(userId)
            userInfoOpt <- Database.getUserInfo(userId)
        } yield {
            val email = userInfoOpt.map(_.email).getOrElse("")
            val phone = userInfoOpt.map(_.phone).getOrElse("")
            ViewValueUser(user.id.toString, user.name, user.age, email, phone)
        }
    }

    //問18
    case class Props(
        length: Int,
        width: Int
    )
    def question18(props: Props)(implicit height: Int): Unit = {
        println("直方体の体積は" + props.length * props.width * height)
        println("四角錐の体積は" + props.length * props.width * height / 3) 
    }

    // 問19
    def question19(ldt: LocalDateTime): Boolean = {
        ldt.isBefore(LocalDateTime.now())
    }

    def question20(ldt: LocalDateTime): String = {
        val formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy年MM月d日 HH時m分")
        ldt.format(formatter)
    }
}