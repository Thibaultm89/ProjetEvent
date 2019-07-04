import { People } from './people.model';

export class Activity {
  id: number;
  name: string;
  start: Date;
  finish: Date;
  manager: People;
  idEvent: number;
}
