import config.serverConfig
import grab.Harvester
import model.Item
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.routing.Routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import routes.setMetrics
import routes.setRoutes
import java.text.DateFormat


fun main(args: Array<String>) {
    Thread().run {
        startServer()
    }

    Thread().run {
        Harvester().launch()
    }


}

private fun startServer() =
        embeddedServer(Netty, port = serverConfig.port) {
            install(Routing) {
                setMetrics()
                setRoutes()
            }

            install(ContentNegotiation) {
                gson {
                    setDateFormat(DateFormat.LONG)
                    setPrettyPrinting()
                }
            }
        }.start()