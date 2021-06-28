package kata.base

import kotlin.math.min

data class Stock(
    val value: Int,
    val minimum: Int = value,
    private val total: Int = value,
    private val amount: Int = 1
) {
    val average by lazy {
        total / amount
    }

    companion object {
        val NEUTRAL = Stock(0)
    }

    operator fun plus(stock: Stock) = Stock(
        value + stock.value,
        min(stock.value, value),
        total + stock.total,
        amount + stock.amount
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Stock

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value
    }


}
