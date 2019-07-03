import { People } from './people.model';
import { DateWithTime } from './date-with-time.model';

export class Activity {
  id: number;
  name: string;
  start: DateWithTime;
  finish: DateWithTime;
  manager: People;
  idEvent: number;
}
