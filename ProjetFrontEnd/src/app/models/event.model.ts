import { DateWithoutTime } from './date-without-time.model';

export class Event {
  id: number;
  name: string;
  start: DateWithoutTime;
  finish: DateWithoutTime;
}
