package WorkingProc

import java.time.LocalDate
import java.time.format.DateTimeFormatter

val DATA_DEFAULT_CONST: LocalDate = LocalDate.of(2090, 12, 1)

class Task(val name: String,val ID: String, var deadline: LocalDate = DATA_DEFAULT_CONST) : Comparable<Task>{
    val subTasksList = mutableMapOf<String,SubTask>()
        get() = field
    var deepness: Int = 0
    var isComplited = false
        get() = field
        set(value) {
            field = value
        }
    //var deadline: LocalDate = LocalDate.of(2090,12,1)

    fun setDeadline(date: String) {
        deadline = LocalDate.parse(date)
    }

    override fun toString(): String {
        val space: String = "   "
        var mainres =
            "#id:"+"\u001b[34m"+ "$ID\u001b[0m $name ${if (deadline != DATA_DEFAULT_CONST) deadline.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")) else ""} ${if (isComplited) "[✓]" else "[✗]"} \n"
        if (!subTasksList.isEmpty())
            for ((index, subtask) in subTasksList.values.withIndex())
                mainres = mainres.plus("${space.repeat(deepness+2)}→\u001b[34m$ID\u001b[0m.${index+1} $subtask")
        return mainres
    }

    override fun compareTo(other: Task): Int {
        var cmp: Int = deadline.year - other.deadline.year
        if (cmp == 0) {
            cmp = deadline.month.value - other.deadline.month.value
            if (cmp == 0) {
                cmp = deadline.dayOfMonth - other.deadline.dayOfMonth
            }
        }
        return cmp
    }
    fun addSubtask(name: String,id:Int){
        val st = SubTask(name,id.toString())
        subTasksList.set(id.toString(),st)
        st.parent = this.ID
    }

    fun setSubtaskComplited(id: String) {
        subTasksList.get(id)?.isDone  = true
        if(subTasksList.values.all{ it.isDone })
            isComplited = true
    }
}