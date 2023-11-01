package edu.hw5

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

class Task2 {

    public fun getAllFriday13th(year:Int) : List<LocalDate>{
        var date:LocalDate? = LocalDate.of(year,1,1);

        val fridays = mutableListOf<LocalDate>()

        while(date!=null){
            date = getNextFriday13th(date)
            if(date != null)
                fridays.add(date)
        }

        return fridays

    }

    private fun getNextFriday13th(date:LocalDate?): LocalDate? {

        var currentDate = date

        while(currentDate!!.year==date!!.year){
            currentDate = currentDate.with(TemporalAdjusters.firstDayOfNextMonth()).plusDays(12)

            if(currentDate.dayOfWeek == DayOfWeek.FRIDAY)
                return currentDate
        }
        return null
    }

}
