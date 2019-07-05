import { Activity } from './activity.model';

export class Event {
  id: number;
  name: string;
  start: Date;
  finish: Date;
  listActi: Activity[];
  img: string;
}
