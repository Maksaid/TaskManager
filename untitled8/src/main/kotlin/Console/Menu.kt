package Console

import WorkingProc.Group
import WorkingProc.Task
import java.time.LocalDate

class Menu(val main: Group) {
    var task_id = 0
    fun work(args: Array<String>) {
        val command = args[0]
        when (command) {
            "add" -> {
                task_id++
                when (args.size) {
                    2 -> main.add(Task(args[1], task_id.toString()))
                    3 -> main.add(Task(args[1], task_id.toString()), args[2])
                    4 -> main.add(Task(args[1], task_id.toString(), LocalDate.parse(args[2])), args[3])
                }
            }
            "show" -> show()
            "delete-all" -> main.deleteAll()
            "set-complited" -> main.setComplited(args[1])
            "show-all-complited" -> main.showComplited()
            "set-deadline" -> {
                val res = main.getTaskByID(args[1])
                val task = res.first as Task
                task.setDeadline(args[2])
                val group = res.second as Group
                group.changePriority(args[1])
            }
            "today" -> main.getTodayDeadline()
            "create-group" -> when (args.size) {
                2 -> main.createGroup(args[1])
                3 -> main.createGroup(args[1], args[2])
            }
            "delete-group" -> main.deleteGroup(args[1])
            "delete-task" -> main.deleteFromGroup(args[1])
            "add-subtask" -> {
                task_id++
                val pair = main.getTaskByID(args[2])
                val task = pair.first as Task
                task.addSubtask(args[1], task_id)
            }
            "set-subtask-complited" -> {
                val parent: Task = main.getTaskByID(args[2]).first as Task
                parent.setSubtaskComplited(args[1])
            }
//            "save" -> save()
//            "load" -> load()
//            "exit" -> exit()
            else -> println("\u001b[31m" + "wrong command: '$command'" + "\u001b[0m")

        }
    }

    fun show() {
        println(main)
    }

}