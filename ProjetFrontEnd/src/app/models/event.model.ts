import { Activity } from './activity.model';

export interface Event {
  id: number;
  name: string;
  start: string;
  finish: string;
  listActivity: Activity[];
}
