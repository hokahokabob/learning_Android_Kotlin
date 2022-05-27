package com.example.busschedule.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.busschedule.database.schedule.Schedule
import com.example.busschedule.database.schedule.ScheduleDao

class BusScheduleViewModel(private val scheduleDao: ScheduleDao) : ViewModel() {

    fun fullSchedule(): List<Schedule> = this.scheduleDao.getAll()

    fun scheduleForStopName(name: String): List<Schedule> = this.scheduleDao.getByStopName(name)
}

/**
 * フラグメントで直接処理しなくてもビューモデルをライフサイクル対応にできるように
 */
class BusScheduleViewModelFactory(private val scheduleDao: ScheduleDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(BusScheduleViewModel::class.java)) {
            return BusScheduleViewModel(scheduleDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}