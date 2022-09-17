import java.lang.Exception

class Queue<T> {
    var head: Node<T>? = null
    var tail: Node<T>? = null
    var size = 0
    fun add(data: T) {
        size++
        val newNode = Node(data, tail)
        tail = newNode
        if (!isEmpty())
            head = newNode
    }

    fun pop(): String {
        if (isEmpty())
            throw Exception("Дядя ты лох, очередь то пустая")
        else {
            size--
            var deletedNode = head
            head = head?.next
            return deletedNode?.data.toString()
        }
    }

    fun isEmpty() = (size == 0)
    override fun toString(): String {
        var queue: String = ""
        var size = size
        var curNode = head
        while (size > 0) {
            size--
            queue += curNode?.data.toString() + " "
            if (curNode != null) {
                curNode = curNode.next
            }
        }
        return queue
    }

}