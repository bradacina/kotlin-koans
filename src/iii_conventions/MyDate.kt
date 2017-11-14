package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int)

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

operator fun MyDate.plus(timeInterval: TimeInterval):MyDate {
    return this.addTimeIntervals(timeInterval,1)
}

operator fun MyDate.plus(timeIntervals: TimeIntervals):MyDate {
    return this.addTimeIntervals(timeIntervals.timeInterval,timeIntervals.number)
}

data class TimeIntervals(val timeInterval: TimeInterval, val number: Int)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

operator fun TimeInterval.times(number: Int): TimeIntervals = TimeIntervals(this, number)

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterator<MyDate> {

    private var iterator: MyDate = start
    private val endExclusive = endInclusive.nextDay()

    override fun next(): MyDate {
        val result = iterator;

        iterator = iterator.nextDay()

        return result
    }

    override fun hasNext(): Boolean {
        if (iterator  == endExclusive) {
            return false
        }

        return true
    }


}
