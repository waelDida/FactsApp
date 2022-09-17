package com.wapp.factsapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import androidx.core.util.PatternsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.DocumentSnapshot
import com.wapp.factsapp.models.Category
import com.wapp.factsapp.models.Fact
import com.wapp.factsapp.models.Question
import java.util.regex.Pattern



fun List<DocumentSnapshot>.convertToFactList():MutableList<Fact>{
    val list = mutableListOf<Fact>()
    this.forEach {
        list.add(it.toObject(Fact::class.java)!!)
    }
    return list
}

fun List<DocumentSnapshot>.convertToQuestionsList():MutableList<Question>{
    val list = mutableListOf<Question>()
    this.forEach {
        list.add(it.toObject(Question::class.java)!!)
    }
    return list
}


fun isValidEmail(email: String) = PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()

fun isPasswordValid(password: String): Boolean{
    val rule  = "^[^\\s]{8,12}"
    val pattern = Pattern.compile(rule)
    val matcher = pattern.matcher(password)
    return matcher.matches()
}

fun Int.getPercentageValue(y:Int): Int{
    return (this.times(100)).div(y)
}

fun String.getFirstName():String{
    val x = this.split(" ")
    return x[0]
}

fun displayMessage(view: View, message: String){
    Snackbar.make(view,message, Snackbar.LENGTH_SHORT).show()
}

fun displayLongMessage(view: View, message: String){
    Snackbar.make(view,message, Snackbar.LENGTH_LONG).show()
}

fun categoriesList(): MutableList<Category>{
    val sport = Category(0,"Sport", "https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_sport.jpg?alt=media&token=249da63e-4341-4b62-a33f-a1adb3db43ad")
    val brain = Category(1,"Brain","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_brain.jpg?alt=media&token=78ac7246-5105-42b1-9f12-80113c1130b7")
    val ww2 = Category(2,"WW2","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_war_2.jpg?alt=media&token=5c19f38c-3e70-49fa-8be8-e6dc67110818")
    val space = Category(3,"Space","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fspace_img.jpg?alt=media&token=f2f82b59-037a-4626-b57b-d02a9f9c38f9",isPicked = false,isLocked = true,value = 1000)
    val celebrities = Category(4,"Celebrities","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_celebrities.jpg?alt=media&token=150feb12-f42e-4149-a1bc-a2855a37177c",isLocked = true,value = 1700)
    val earth = Category(5,"Earth","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_earth.jpg?alt=media&token=e92b0fb2-dd01-4259-a146-4881a113add5")
    val food = Category(6,"Food","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_food.jpg?alt=media&token=61343e5d-255f-43c1-9011-44e9d4d85db3")
    val dating = Category(7,"Dating","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_dating.jpg?alt=media&token=43213741-1e62-48e8-b24c-260faa2cecfd",isPicked = false,isLocked = true,value = 2000)
    val babies = Category(8,"Babies","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_baby.jpg?alt=media&token=de10859d-0d8c-44c1-8606-432fa6234ebc",isLocked = true,value = 1400)
    val cars = Category(9,"Cars","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_car.jpg?alt=media&token=b1019153-53f5-4d20-b994-769dc6ed1f64")
    val art = Category(10,"Art","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_art.jpg?alt=media&token=a0f4ce65-653a-4eba-990f-80354a665a26",isPicked = false,isLocked = true,value = 1000)
    val geography = Category(11,"Geography","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_geography.jpg?alt=media&token=3da596f2-41ee-439b-87ce-1825743fe809")
    val ai = Category(12,"AI","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_ai.jpg?alt=media&token=f9a098ad-6412-450f-acdb-2d9544c5c125",isPicked = false,isLocked = true,value = 1200)
    val science = Category(13,"Science","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_biology.jpg?alt=media&token=d4143403-03c7-45ee-8baf-e702d4de54e4")
    val socialMedia = Category(14,"Social Media","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_social.jpg?alt=media&token=a9b50222-7d88-412d-85e4-b2fb0de091a5", isPicked = false, isLocked = true, value = 1000)

    val history = Category(15,"History","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_history.jpg?alt=media&token=eeb79ef3-e86e-439c-9420-a46473c70e61")
    val nature = Category(16,"Nature","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_nature.jpg?alt=media&token=198c8c3e-c1c3-42f7-9496-32a3e0fc0f93")
    val tesla = Category(17,"Nicola Tesla","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_tesla.jpeg?alt=media&token=679886c6-1940-4dd5-b5fb-5ccbead2666b",isPicked = false,isLocked = true,value = 1000)
    val crypto = Category(18,"Cryptocurrency","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_crypto.jpg?alt=media&token=9159d2f3-2a8e-4dc3-a890-47f46147717c",isPicked = false,isLocked = true,value = 1200)
    val animals= Category(19,"Animals","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_animals.jpg?alt=media&token=13c345ab-7dd7-471a-a18c-de7bb6631407")
    val corona = Category(20,"Coronavirus","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_corona.jpg?alt=media&token=1d872501-9fc3-4a19-a034-11363ee3e60d")
    val ww1 = Category(21,"WW1","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fwar_img.jpg?alt=media&token=6c0cc7c0-0fc5-48e2-816c-2d6643258a9d",isPicked = false,isLocked = true,value = 1500)
    val sex= Category(22,"Sex","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_sex.jpg?alt=media&token=8ab80fea-c8cd-480d-a99b-6f956318e269",isPicked = false,isLocked = true,value = 2200)
    val technology = Category(23,"Technology","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_technology.jpg?alt=media&token=93a5ff26-6d2d-4ea9-8f64-32026eadc491",isLocked = true,value = 1400)
    val dog_cats = Category(24,"Dog & Cat","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_dog_cat.jpg?alt=media&token=a783ea20-7f51-49ce-acfd-857ac94a9454")
    val ancient_egypt = Category(25,"Ancient Egypt","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_ancient_egypt.jpg?alt=media&token=a0646411-8957-48ac-9fdf-2837830972b0",isPicked = false,isLocked = true,value = 1500)

    val music = Category(26,"Music","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_music.jpg?alt=media&token=cfe2434f-717c-4dcc-ae3c-2c98e4bb4af6")
    val football = Category(27,"Football","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_football.jpg?alt=media&token=15b16b54-2cf1-45ca-9354-e81ed3c38ce9",isPicked = false,isLocked = true,value = 1000)
    val psychology = Category(28,"Psychology","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_psychology.jpg?alt=media&token=fa280cb9-41b5-477f-b5aa-d84f9ebd377f",isPicked = false,isLocked = true,value = 1700)
    val movies_series = Category(29,"Movie&Series","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_movie_serie.jpg?alt=media&token=52fa957c-a2b7-417f-bd82-cd8eb3cdfb3d")

    return mutableListOf(sport,brain,ww2,space,celebrities,earth,food,dating,babies,cars,art,geography,ai,science,socialMedia,
    history,nature,tesla,crypto,animals,corona,ww1,sex,technology,dog_cats,ancient_egypt,
    music,football,psychology,movies_series)
}

fun categoriesToPickList(): MutableList<Category>{
    val sport = Category(0,"Sport", "https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_sport.jpg?alt=media&token=249da63e-4341-4b62-a33f-a1adb3db43ad")
    val brain = Category(1,"Brain","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_brain.jpg?alt=media&token=78ac7246-5105-42b1-9f12-80113c1130b7")
    val ww2 = Category(2,"WW2","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_war_2.jpg?alt=media&token=5c19f38c-3e70-49fa-8be8-e6dc67110818")
    val earth = Category(3,"Earth","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_earth.jpg?alt=media&token=e92b0fb2-dd01-4259-a146-4881a113add5")
    val food = Category(4,"Food","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_food.jpg?alt=media&token=61343e5d-255f-43c1-9011-44e9d4d85db3")
    val cars = Category(5,"Cars","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_car.jpg?alt=media&token=b1019153-53f5-4d20-b994-769dc6ed1f64")
    val geography = Category(6,"Geography","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_geography.jpg?alt=media&token=3da596f2-41ee-439b-87ce-1825743fe809")
    val science = Category(7,"Science","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_biology.jpg?alt=media&token=d4143403-03c7-45ee-8baf-e702d4de54e4")
    val history = Category(8,"History","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_history.jpg?alt=media&token=eeb79ef3-e86e-439c-9420-a46473c70e61")
    val nature = Category(9,"Nature","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_nature.jpg?alt=media&token=198c8c3e-c1c3-42f7-9496-32a3e0fc0f93")
    val animals= Category(10,"Animals","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_animals.jpg?alt=media&token=13c345ab-7dd7-471a-a18c-de7bb6631407")
    val corona = Category(11,"Coronavirus","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_corona.jpg?alt=media&token=1d872501-9fc3-4a19-a034-11363ee3e60d")
    val dog_cats = Category(12,"Dog & Cat","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_dog_cat.jpg?alt=media&token=a783ea20-7f51-49ce-acfd-857ac94a9454")
    val music = Category(13,"Music","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_music.jpg?alt=media&token=cfe2434f-717c-4dcc-ae3c-2c98e4bb4af6")
    val movies_series = Category(14,"Movie&Series","https://firebasestorage.googleapis.com/v0/b/mytracker-8c61c.appspot.com/o/categories%2Fimg_movie_serie.jpg?alt=media&token=52fa957c-a2b7-417f-bd82-cd8eb3cdfb3d")
    return mutableListOf(sport,brain,ww2,earth,food,cars,geography,
            science,history,nature,animals,corona,dog_cats,music,movies_series)
}

fun categoriesNameList():MutableList<String>{
    val list = mutableListOf<String>()
    categoriesToPickList().forEach {
        list.add(it.name)
    }
    return list
}


fun isNetworkAvailable(context: Context): Boolean{
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo!=null && networkInfo.isConnected
}