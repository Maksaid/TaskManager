package Console
    import WorkingProc.*


fun main() {
    val mainGroup = Group("")
    val menu = Menu(mainGroup)
    while (true){
       val args =  parseCommands(readLine()!!)
        menu.work(args)
    }
}
fun parseCommands(stroke: String) = stroke.split(" ").toTypedArray()