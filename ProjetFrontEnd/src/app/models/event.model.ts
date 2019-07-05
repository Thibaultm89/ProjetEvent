import { Activity } from './activity.model';

export class Event {
  id: number;
  name: string;
  start: Date;
  finish: Date;
  img: string;
  listActi: Activity[];
}
