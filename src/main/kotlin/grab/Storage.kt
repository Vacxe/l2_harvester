package grab

import model.ItemInfo

object Storage{
    val itemInfoStorage = HashMap<String, ItemInfo>()

    fun updateItem(id: String, itemInfo: ItemInfo){
        itemInfoStorage[id] = itemInfo
    }

    fun getInfo(id: String): ItemInfo? = itemInfoStorage[id]
}