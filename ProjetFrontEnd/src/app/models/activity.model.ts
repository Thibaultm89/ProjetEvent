import { People } from './people.model';

export class Activity {
  id: number;
  name: string;
  start: Date;
  finish: Date;
  manager: number;
  idEvent: number;
  img: string;
  listPeople: People[];
}
