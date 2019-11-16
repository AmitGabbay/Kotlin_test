import java.math.BigDecimal
import java.time.DayOfWeek
import java.time.LocalDateTime
/*
Note: Changed creationDate in Order class from LocalDateTime to Sting to solve JSON parsing issue.
 */
class OrdersAnalyzer {

    data class Order(val orderId: Int, val creationDate: String, val orderLines: List<OrderLine>)

    data class OrderLine(val productId: Int, val name: String, val quantity: Int, val unitPrice: BigDecimal)

    fun totalDailySales(orders: List<Order>): Map<DayOfWeek, Int> {
        //val ordersByDay = orders.groupBy {it.creationDate.dayOfWeek } ==> if creation date comes as LocalDateTime
        val ordersByDay = orders.groupBy {LocalDateTime.parse(it.creationDate).dayOfWeek }
        val salesByDay = ordersByDay.mapValues {it.value.flatMap{ it.orderLines }.sumBy { it.quantity }}

        return salesByDay
    }
}