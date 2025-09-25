export class Todo {
}

export interface Todo {
  id: number;
  title: string;
  description?: string;
  dueDate?: string;      // <--- string, not Date
  priority: 'low' | 'medium' | 'high';
  completed: boolean;
  createdAt: string;
}
