package routes

import grab.Storage
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.route

fun Routing.setMetrics() = route("") {
    val TABLE_NAME = "l2_market"

    get("metrics") {
        val builder = StringBuilder()
        for ((type, value) in Storage.itemInfoStorage) {
            builder.appendln("$TABLE_NAME{item=\"${type.name}\",itemId=\"${type.id}\",type=\"min\",action=\"sale\"} ${value.sale.min}")
            builder.appendln("$TABLE_NAME{item=\"${type.name}\",itemId=\"${type.id}\",type=\"avg\",action=\"sale\"} ${value.sale.avg}")
            builder.appendln("$TABLE_NAME{item=\"${type.name}\",itemId=\"${type.id}\",type=\"max\",action=\"sale\"} ${value.sale.max}")
            builder.appendln("$TABLE_NAME{item=\"${type.name}\",itemId=\"${type.id}\",type=\"min\",action=\"buy\"} ${value.buy.min}")
            builder.appendln("$TABLE_NAME{item=\"${type.name}\",itemId=\"${type.id}\",type=\"avg\",action=\"buy\"} ${value.buy.avg}")
            builder.appendln("$TABLE_NAME{item=\"${type.name}\",itemId=\"${type.id}\",type=\"max\",action=\"buy\"} ${value.buy.max}")

        }
        call.respond(builder.toString())
    }
}
