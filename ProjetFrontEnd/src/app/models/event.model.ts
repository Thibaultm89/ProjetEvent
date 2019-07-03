import { DateWithoutTime } from './date.model';

export class Event {
  id: number;
  name: string;
  start: DateWithoutTime;
  finish: DateWithoutTime;
}
