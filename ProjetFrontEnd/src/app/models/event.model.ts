import { DateWithoutTime } from './date-without-time.model';

export class Event {
  id: number;
  name: string;
  start: Date;
  finish: DateWithoutTime;

  public getStart(): string {
    return this.start.toISOString();
  }
}
