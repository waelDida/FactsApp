package com.wapp.factsapp.flow


import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.wapp.factsapp.R
import com.wapp.factsapp.models.Category
import com.wapp.factsapp.models.Fact
import com.wapp.factsapp.models.Question
import com.wapp.factsapp.utils.getPercentageValue
import de.hdodenhof.circleimageview.CircleImageView


@BindingAdapter("setImg")
fun setImage(imageView: ImageView, url: String?){
    url?.let {
        val uri = url.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
                .load(uri)
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.ic_broken)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)
    }
}

@BindingAdapter("setCategoryImage")
fun ImageView.setCategoryImage(category: Category){
    val uri = category.image.toUri().buildUpon().scheme("https").build()
    Glide.with(this.context)
            .load(uri)
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(R.drawable.ic_broken)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(this)
}

@BindingAdapter("setUserImage")
fun setUserImage(imageView: CircleImageView, url: String?){
    url?.let {
        val uri = url.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(uri)
            //.apply(RequestOptions.placeholderOf(ColorDrawable(ContextCompat.getColor(imageView.context,R.color.loading_color))))
                .error(ContextCompat.getDrawable(imageView.context, R.drawable.ic_user_photo))
                .into(imageView)
    }
}

@BindingAdapter("setVisibility")
fun ImageView.setVisibility(category: Category){
    visibility = if(category.isPicked) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("changeLikeImg")
fun ImageView.setLikeImage(fact: Fact?){
    fact?.let {
        if(it.isLiked)
            Glide.with(this.context).load(ContextCompat.getDrawable(this.context, R.drawable.ic_heart_red)).into(this)
        else
            Glide.with(this.context).load(ContextCompat.getDrawable(this.context, R.drawable.ic_heart_black)).into(this)
    }


}

@BindingAdapter("changeBookMarkImg")
fun ImageView.setBookMarkImage(fact: Fact?){
    fact?.let {
        if(it.isBookmarked)
            Glide.with(this.context).load(ContextCompat.getDrawable(this.context, R.drawable.ic_bookmarks_blue)).into(this)
        else
            Glide.with(this.context).load(ContextCompat.getDrawable(this.context, R.drawable.ic_bookmarks_black)).into(this)
    }
}

@BindingAdapter("setLockVisibility")
fun ImageView.setLockVisibility(category: Category){
    if(category.isLocked)
        this.visibility = View.VISIBLE
    else
        this.visibility = View.INVISIBLE
}

//Quiz Binding UI

@BindingAdapter("setNoImageQuestionText")
fun TextView.setText(question: Question?){
    question?.let {
        if(question.image == null){
            visibility = View.VISIBLE
            text = question.question
        }
        else
            visibility = View.GONE
    }

}

@BindingAdapter("setImageQuestionText")
fun TextView.setImageText(question: Question?){
    question?.let {
        if(it.image != null){
            visibility = View.VISIBLE
            text = it.question
        }
        else
            visibility = View.GONE
    }
}

@BindingAdapter("setQuestionImage")
fun ImageView.setImageQuestion(question: Question?){
    question?.let {
        if(question.image != null){
            visibility = View.VISIBLE
            Glide.with(this.context).load(ContextCompat.getDrawable(this.context, question.image!!)).into(this)
        }
        else
            visibility = View.GONE
    }
}

// Completed Quiz UI

@BindingAdapter("setGainedCoins")
fun TextView.setCoins(correctAnswers: Int){
    val str = "+${correctAnswers.times(10)}"
    text = str
}

@BindingAdapter("correctAnswers", "questionNumbers")
fun TextView.setPercentage(correctAnswers: Int, questionsNumber: Int){
    val str = "${correctAnswers.getPercentageValue(questionsNumber)}%"
    text = str
}

@BindingAdapter("setSecondText")
fun TextView.setSecondText(correctAnswers: Int){
    text = if(correctAnswers == 10)  resources.getString(R.string.perfect) else resources.getString(R.string.well_done)
}

// GameOver ** TimeUp UI

@BindingAdapter("gameOverFirstText")
fun TextView.setFirstText(timeIsUp: Boolean){
    text = if(timeIsUp) resources.getString(R.string.time_is_up) else resources.getString(R.string.game_over)
}

@BindingAdapter("timeIsUp", "correctAnswersSecondText")
fun TextView.setSecondTextGameOver(timeIsUp: Boolean, correctAnswers: Int){
    text = if(timeIsUp) resources.getString(R.string.answer_faster_next_time)
    else {
        if(correctAnswers < 5) resources.getString(R.string.better_luck) else resources.getString(R.string.very_close)
    }
}

