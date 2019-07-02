import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { JavaService } from './services/java.service';
import { HttpClientModule } from '@angular/common/http';
import { CreatePeopleComponent } from './pages/creations/create-people/create-people.component';
import { CreateActivityComponent } from './pages/creations/create-activity/create-activity.component';
import { CreateEventComponent } from './pages/creations/create-event/create-event.component';
import { EventsComponent } from './pages/events/events.component';
import { ActivitiesComponent } from './pages/activities/activities.component';
import { LoginComponent } from './pages/login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    CreatePeopleComponent,
    CreateActivityComponent,
    CreateEventComponent,
    EventsComponent,
    ActivitiesComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [JavaService],
  bootstrap: [AppComponent]
})
export class AppModule { }
