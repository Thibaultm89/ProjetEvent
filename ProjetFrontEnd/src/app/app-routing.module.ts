import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { CreateActivityComponent } from './pages/creations/create-activity/create-activity.component';
import { CreateEventComponent } from './pages/creations/create-event/create-event.component';
import { EventsComponent } from './pages/events/events.component';
import { ActivitiesComponent } from './pages/activities/activities.component';
import { LoginComponent } from './pages/login/login.component';

const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'}, // toujours à mettre en premier
  {path: 'home', component: HomeComponent},
  {path: 'create-activity/:id', component: CreateActivityComponent},
  {path: 'create-event', component: CreateEventComponent},
  {path: 'events/:id', component: EventsComponent},
  {path: 'activity/:id', component: ActivitiesComponent},
  {path: 'login', component: LoginComponent},
  {path: '**', redirectTo: '/home'} // toujours à mettre en dernier
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
