/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package kata.base


import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.next
import io.kotest.property.forAll
import kotlin.random.Random

class StockTest : WordSpec({
    "combine" should {
        "50" {
            val result = Stock(20) + Stock(30)
            result shouldBe Stock(50)
        }
        "30" {
            val result = Stock(20) + Stock(10)
            result shouldBe Stock(30)
        }
        "Neutral" {
            val result = forAnyStock();
            result + Stock.NEUTRAL shouldBe result
            Stock.NEUTRAL + result shouldBe result
        }

        "associative" {
            val a = forAnyStock()
            val b = forAnyStock()
            val c = forAnyStock()

            a + b + c shouldBe b + c + a
        }
        "pbt" {
            forAll(anyStock, anyStock) { a, b ->
                a + b == Stock(a.value + b.value)
            }
        }
    }

    "minimum" should {
        "return the minimum of two stocks" {
            val a = Stock(1)
            val b = Stock(50)

            val result = a + b

            result.minimum shouldBe 1
        }

        "return the minimum of three stocks" {
            val a = Stock(0)
            val b = Stock(20)
            val c = Stock(50)
            val result = a + ( b + c)

            result.minimum shouldBe 0
        }
        "return the average stock" {
            val a = Stock(0)
            val b = Stock(20)
            val c = Stock(50)
            val result = a + ( b + c)

            result.average shouldBe (0 + 20 + 50) / 3
        }
    }
})

fun forAnyStock() = Stock(Random.nextInt())

val anyStock = arbitrary { rs ->
    val value = Arb.int(21, 150).next(rs)
    Stock(value)
}
