package routes

import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.route

fun Routing.setMetrics() = route("") {
    val TABLE_NAME = "metrics"

    get("metrics") {
        val builder = StringBuilder()
/*        for (room in Persistance.home.rooms) {
            for((type, value) in room.metrics) {
                builder.appendln("$TABLE_NAME{room=\"${room.name}\",metric=\"${type}\"} ${value}")
            }
        }
        call.respond(builder.toString())
        */
    }
}
