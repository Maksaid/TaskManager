package WorkingProc

import java.time.LocalDate
import java.util.*
import WorkingProc.Task as Task1

val DFSgrouplist = arrayListOf<String>()
val queue: Queue<Group> = LinkedList()
var pair: Pair<Any?, Any?> = Pair(0, 0)
var res_group: Group? = null


class Group(private val name: String) {
    val tasksContainer = mutableMapOf<String, Task1>()
    val groupContainer = mutableMapOf<String, Group>()
        get() = field
    val priorityQueue = PriorityQueue<Task1>()
        get() = field
    var deepness: Int = 0
    var parent : String = ""

    override fun toString(): String {
        var space = "   "
        var res = "\u001B[35m$name\u001B[0m\n"
        val copyOfPriorityQueue = PriorityQueue(priorityQueue)
        for (i in 1..priorityQueue.size) {
            res =
                res.plus("${space.repeat(deepness + 1)}${i}. ${if (copyOfPriorityQueue.peek().deadline > LocalDate.now()) copyOfPriorityQueue.poll() else "\u001b[31m" + "${copyOfPriorityQueue.poll()}" + "\u001b[0m"}")
        }
        for (group in groupContainer.values)
            res = res.plus("${space.repeat(deepness)}$group")
        return res
    }

    fun add(task: Task1, groupName: String = "") {
        if (groupName != "" && groupContainer.containsKey(groupName))
            groupContainer.get(groupName)!!.add(task)
        else if (groupName == "") {
            tasksContainer.put(task.ID, task)
            priorityQueue.add(task)
        } else {
            val group = findGroupByName(groupName)
            if (group != null) {
                group.add(task)
            } else
                println("group named $groupName doesn't exist")
        }
    }

    fun deleteAll() {
        groupContainer.clear()
        tasksContainer.clear()
        priorityQueue.clear()
    }

    fun setComplited(id: String) {
        if (tasksContainer.containsKey(id))
            tasksContainer[id]!!.isComplited = true
        else
            for (group in groupContainer.values)
                group.setComplited(id)
    }

    fun showComplited() {
        var res = "$name \n"
        for (task in priorityQueue.filter { it.isComplited })
            res = res.plus(task)
        for (group in groupContainer.values)
            res.plus(group)
        println(res)
    }

    fun getTaskByID(id: String): Pair<Any?, Any?> {
        val visited: MutableMap<String, Boolean> = createGroupMap()
        if (tasksContainer.containsKey(id))
            return Pair<Any?, Any?>(tasksContainer[id], this)
        else
            return BFS(visited, id)
    }

    fun BFS(visited: MutableMap<String, Boolean>, id: String): Pair<Any?, Any?> {
        if (visited.containsKey(name))
            visited.remove(name)
        visited.put(name, true)
        if (tasksContainer.containsKey(id))
            pair = Pair<Any?, Any?>(tasksContainer[id], this)
        else {
            for (group in groupContainer.values) {
                queue.add(group)
            }
            for (group in queue) {
                val el = queue.poll()
                if (visited[el.name] == false)
                    pair = el.BFS(visited, id)
            }
        }
        return pair
    }

    fun getTodayDeadline() {
        var res = "$name \n"
        for (task in priorityQueue.filter { it.deadline.isEqual(LocalDate.now()) })
            res = res.plus(task)
        for (group in groupContainer.values)
            res.plus(group)
        println(res)
    }

    fun changePriority(id: String) {
        val newTask = priorityQueue.find { it.ID == id }
        priorityQueue.remove(newTask)
        priorityQueue.add(newTask)
    }

    fun createGroup(name: String, destination: String = "") {
        DFSgrouplist.add(name)
        if (destination == ""){
            val newGroup = Group(name)
            newGroup.parent = this.name
            groupContainer.put(name, newGroup)

        }
        else {
            val group = findGroupByName(destination)
            if (group != null) {
                group.createGroup(name)
            } else
                println("group named $destination doesn't exist")
        }

    }

    fun createGroupMap(): MutableMap<String, Boolean> {
        val map = mutableMapOf<String, Boolean>()
        for (name in DFSgrouplist)
            map[name] = false
        return map
    }

    fun findGroupByName(name: String): Group? {
        val visited: MutableMap<String, Boolean> = createGroupMap()
        return BFS_Group(visited, name)
    }

    fun BFS_Group(visited: MutableMap<String, Boolean>, name: String): Group? {
        if (visited.containsKey(this.name))
            visited.remove(this.name)
        visited.put(this.name, true)
        if (this.name == name)
            res_group = this
        else {
            for (group in groupContainer.values) {
                queue.add(group)
                group.deepness = deepness + 1
                group.priorityQueue changeDeepness group.deepness
            }
            for (group in queue) {
                val el = queue.poll()
                if (visited[el.name] == false)
                    res_group = el.BFS_Group(visited, name)
            }
        }
        return res_group
    }
    fun deleteGroup(name: String){
       val gr = findGroupByName(name) as Group
        val par = findGroupByName(gr.parent)
        par?.groupContainer?.remove(name)
    }

    infix fun PriorityQueue<Task1>.changeDeepness(deep : Int){
        for(task in this){
            task.deepness = deep
        }
    }
    fun deleteFromGroup(id: String){
        val pair = getTaskByID(id)
         val gr = pair.second as Group
        gr.tasksContainer.remove(id)
        gr.priorityQueue.remove(pair.first as Task1)
    }
}