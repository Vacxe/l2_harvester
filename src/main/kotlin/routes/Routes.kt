package routes

import grab.Storage
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.route

fun Routing.setRoutes() = route("") {
    get("/item/{id}") {
        val id = call.parameters["id"]?:""
        call.respond(Storage.getInfo(id) ?: "Item not found")
    }
}
