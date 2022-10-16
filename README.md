# TaskManager
Console taks manager, priority queue, BFS used.
Tasks in group are being sorted by their deadline because of the priority queue, to search the task I need to gind by id I ude BFS algorithm.
use these commands, to work in application

add {taskName}
add {taskName} {groupname}
add {taskname} {year-month-day} {groupname}
create-group {newGroupname}
create-group {newGroupname} {oldGroupname}
set-deadline {taskId} {year-month-day)
set-complited {taskId}
add-subtask {subtaskName} {taskId}
set-subtask-complited {subtaskId} {taskId}
show - print all



add {name} - to add a task in a main group with name "name"
create-group {hubabuba} - to add a task-group inside the main group
create-group {v_huinye} {hubabuba} - to add a task-group v_huinye inside hubabuba
add {nihuya} {hubabuba} - add task nihuya to group hubabuba
add zalupa v_huinye - add task to v_huinye
add blya v_nihuya - add task to v_nihuya
add pizda v_nihuya
set-deadline 3 2022-05-04 - set deadline to the task with id == 3
add yay 2022-08-08 hubabuba - add task with the deadline to group hubabuba
create-group v_v_huinye v_huinye
add v_v_huinye v_v_huinye
create-group v_v_huinyeB v_huinye
add v_v_s v_v_huinyeB
set-complited 9 - set task with id == 9 complited
set-complited 2
set-complited 3
add-subtask Anton 4 - add subtask to task id == 4
add-subtask nahui 4
add-subtask poshel 4
set-subtask-complited 9 4 - set subtask with id == 9 in task with id == 4 complited
set-subtask-complited 10 4
show - show the results
set-subtask-complited 11 4
show
