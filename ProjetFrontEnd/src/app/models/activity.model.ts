import { People } from './people.model';

export interface Activity {
  id: number;
  name: string;
  start: {
    dayOfMonth: string,
    monthValue: string,
    year: string
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
