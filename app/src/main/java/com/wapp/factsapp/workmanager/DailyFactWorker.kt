package com.wapp.factsapp.workmanager

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.content.Context
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.bumptech.glide.load.HttpException
import com.wapp.factsapp.R
import com.wapp.factsapp.data.providers.firebase.FirebaseProvider
import com.wapp.factsapp.data.providers.preference.PreferenceProvider
import com.wapp.factsapp.data.repositories.factsDataRepository.FactsDataRepo
import com.wapp.factsapp.data.repositories.userDataRepositoy.UserDataRepo
import com.wapp.factsapp.notifications.sendNotification
import com.wapp.factsapp.utils.REMOTE_FACT_NUM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception


class DailyFactWorker @WorkerInject constructor(@Assisted context: Context,
                                          @Assisted workerParams: WorkerParameters,
                                          val factsDataRepo: FactsDataRepo,
                                          val userDataRepo: UserDataRepo,
                                          val firebaseProvider: FirebaseProvider, val preferenceProvider: PreferenceProvider,
                                          private val notificationManager: NotificationManager) :CoroutineWorker(context,workerParams) {


    @SuppressLint("RestrictedApi")
    override suspend fun doWork(): Result {
        return try {
            withContext(Dispatchers.IO){
                val x = userDataRepo.getUserFromRoom(firebaseProvider.getCurrentUserId())
                val y = factsDataRepo.getFactFromFireStore(x.factNum.toString())
                factsDataRepo.insertFactIntoRoom(y)
                userDataRepo.updateUserField(x.uid, REMOTE_FACT_NUM,x.factNum.plus(1))
                userDataRepo.getCurrentUserFromFireStore(firebaseProvider.getCurrentUserId())?.let {
                    userDataRepo.updateUserIntoRoom(it)
                }
                
                if(preferenceProvider.getNotificationStatus())
                    notificationManager.sendNotification(
                        applicationContext.getString(R.string.fact_notification_title),y.title,applicationContext)

            }
            Result.success()
        }catch (e:HttpException){
            Result.Retry()
        }
        catch (e:Exception){
            Result.failure()
        }
    }
}