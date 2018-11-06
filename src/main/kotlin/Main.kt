import config.serverConfig
import grab.Grabber
import model.Item
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.routing.Routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import routes.setMetrics
import java.text.DateFormat


fun main(args: Array<String>) {
    //startServer()
    Grabber().updateItem(Item.STONE_OF_PURITY)
}

private fun startServer() =
        embeddedServer(Netty, port = serverConfig.port) {
            install(Routing) {
                setMetrics()
            }

            install(ContentNegotiation) {
                gson {
                    setDateFormat(DateFormat.LONG)
                    setPrettyPrinting()
                }
            }
        }.start(true)