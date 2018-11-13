package grab

import model.Item
import model.ItemInfo

object Storage{
    val itemInfoStorage = HashMap<Item, ItemInfo>()

    fun updateItem(item: Item, itemInfo: ItemInfo){
        itemInfoStorage.put(item, itemInfo)
    }

    fun getInfo(id: String): ItemInfo? = itemInfoStorage[Item.values().first { it.id == id }]
}