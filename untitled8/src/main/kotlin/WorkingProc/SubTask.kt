package WorkingProc

open class SubTask(val name : String, val ID : String) {
    var isDone : Boolean = false
        get() = field
        set(value){
            field = value
        }
    var parent = ""
    override fun toString(): String {
        return "#$ID $name ${if(isDone) "[✓]" else "[✗]"} \n"
    }
}