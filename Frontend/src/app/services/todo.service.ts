import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Todo } from '../models/todo.model';

@Injectable({
  providedIn: 'root'
})
export class TodoService {
  private todosSubject = new BehaviorSubject<Todo[]>([]);
  public todos$ = this.todosSubject.asObservable();
  private nextId = 1;

  constructor() {
    // Load from localStorage if available
    this.loadTodos();
  }

  private loadTodos(): void {
    const saved = localStorage.getItem('todos');
    if (saved) {
      const todos = JSON.parse(saved);
      this.todosSubject.next(todos);
      this.nextId = Math.max(...todos.map((t: Todo) => t.id), 0) + 1;
    }
  }

  private saveTodos(): void {
    localStorage.setItem('todos', JSON.stringify(this.todosSubject.value));
  }

  getAllTodos(): Observable<Todo[]> {
    return this.todos$;
  }
  

  addTodo(title: string, description?: string, dueDate?: string, priority?: string)
 {
    const newTodo: Todo = {
      id: this.nextId++,
      title: title.trim(),
      description: description?.trim(),
      completed: false,
      createdAt: new Date().toISOString(),
      dueDate,
      priority: priority as 'low' | 'medium' | 'high' || 'medium'
    };

    const currentTodos = this.todosSubject.value;
    this.todosSubject.next([...currentTodos, newTodo]);
    this.saveTodos();
  }

  updateTodo(id: number, updates: Partial<Todo>): void {
    const currentTodos = this.todosSubject.value;
    const updatedTodos = currentTodos.map(todo =>
      todo.id === id ? { ...todo, ...updates } : todo
    );
    this.todosSubject.next(updatedTodos);
    this.saveTodos();
  }

  deleteTodo(id: number): void {
    const currentTodos = this.todosSubject.value;
    const filteredTodos = currentTodos.filter(todo => todo.id !== id);
    this.todosSubject.next(filteredTodos);
    this.saveTodos();
  }

  toggleComplete(id: number): void {
    const currentTodos = this.todosSubject.value;
    const updatedTodos = currentTodos.map(todo =>
      todo.id === id ? { ...todo, completed: !todo.completed } : todo
    );
    this.todosSubject.next(updatedTodos);
    this.saveTodos();
  }

  getFilteredTodos(filter: 'all' | 'active' | 'completed'): Observable<Todo[]> {
    return new Observable(observer => {
      this.todos$.subscribe(todos => {
        let filtered = todos;
        switch (filter) {
          case 'active':
            filtered = todos.filter(todo => !todo.completed);
            break;
          case 'completed':
            filtered = todos.filter(todo => todo.completed);
            break;
          default:
            filtered = todos;
        }
        observer.next(filtered);
      });
    });
  }
}
