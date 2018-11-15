package grab

import com.google.gson.Gson
import model.Item
import model.ItemInfo
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class Harvester {
    fun launch() {
        Timer().schedule(object :TimerTask(){
            override fun run() {
                for (item in Item.values()) {
                    System.out.println("Updating item: ${item.name} ")
                    val response = sendRequest("http://aksenov.online:6661/?id=${item.id}")
                    val itemInfo = Gson().fromJson(response, ItemInfo::class.java)
                    Storage.updateItem(item, itemInfo)
                    System.out.println("Item: ${item.name} was updated")
                }
            }
        }, 0 , 1000*15)

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