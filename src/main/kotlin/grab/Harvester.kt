package grab

import com.google.gson.Gson
import model.Item
import model.ItemInfo
import org.w3c.dom.Element
import org.xml.sax.InputSource
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import javax.xml.parsers.DocumentBuilderFactory

class Harvester {

    fun launch() {
        Timer().schedule(object : TimerTask(){
            override fun run() {
                for (item in Item.values()) {
                    val response = sendRequest("http://localhost:6661/?id=${item.id}")
                    val itemInfo = Gson().fromJson(response, ItemInfo::class.java)
                    Storage.updateItem(item, itemInfo)
                    System.out.println("Item: ${item.name} was updated")
                }
            }
        }, 0, 1000*60*5)

    }

    @Synchronized
    private fun sendRequest(url: String): String {
        val obj = URL(url)

        with(obj.openConnection() as HttpURLConnection) {
            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuffer()

                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                return response.toString()
            }
        }
    }
}