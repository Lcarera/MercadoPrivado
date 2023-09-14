package data

data class CardItem(val id:String,val thumbnailUrl:String,val price:Float,
                    val title: String, val location:String,val pictureUrl:String? = null, val description: String? = null,
                    val condition: String? = null, val parsedCategories: String? = null)