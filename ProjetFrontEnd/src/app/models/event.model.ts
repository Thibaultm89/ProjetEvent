import { Activity } from './activity.model';

export interface Event {
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
  listActivity: Activity[];
}
