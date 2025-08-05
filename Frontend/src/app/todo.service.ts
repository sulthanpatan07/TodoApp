import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TodoService {

  constructor() { }
  taskId =3
  todoList =[
    {
      id:1,'task':"Task1"
    },
    {
      id:2,'task':"Task2"
    }
  ]
  addTodo(taskName:any){
    this.todoList.push({"id":this.taskId++,'task':taskName})
  }
            
  deleteTodo(index:any){
    this.todoList.splice(index,1)
  }
}
