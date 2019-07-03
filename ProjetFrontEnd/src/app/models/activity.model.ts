import { People } from './people.model';
import { min } from 'rxjs/operators';

export interface Activity {
  id: number;
  name: string;
  start: {
    dayOfMonth: string,
    monthValue: string,
    year: string,
    hour: string,
    minute: string,
    second: string,
  };
  finish: {
    dayOfMonth: string,
    monthValue: string,
    year: string
  };
  manager: People;
  idEvent: number;
  listPeople: People[];
}
