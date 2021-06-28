package kata.base

data class Stock(val value: Int) {
    companion object {
        val NEUTRAL = Stock(0)
    }

    operator fun plus(stock: Stock) = Stock(value + stock.value)
}
