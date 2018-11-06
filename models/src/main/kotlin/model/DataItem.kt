package model

data class DataItem(val item: Item,
                    val name: String,
                    val minSalePrice: Int,
                    val maxSalePrice: Int,
                    val averageSalePrice: Int,
                    val minBuyPrice: Int,
                    val maxBuyPrice: Int,
                    val averageBuyPrice: Int)