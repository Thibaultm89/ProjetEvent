import { People } from './people.model';

export interface Activity {
  id: number;
  name: string;
  start: string;
  finish: string;
  manager: People;
  listPeople: People[];
}
