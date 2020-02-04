import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';

import {ClientListComponent} from './clients/client-list/client-list.component';
import {ClientsComponent} from './clients/clients.component';
import {ClientService} from './clients/shared/client.service';

import {MovieService} from './movies/shared/movie.service';
import {MoviesComponent} from './movies/movies.component';
import {MoviesListComponent} from './movies/movies-list/movies-list.component';
import {ClientDetailsComponent} from './clients/client-details/client-details.component';
import {MovieDetailsComponent} from './movies/movie-details/movie-details.component';
import {ClientNewComponent} from './clients/client-new/client-new.component';
import {DatePipe} from '@angular/common';
import { MovieNewComponent } from './movies/movie-new/movie-new.component';

@NgModule({
  declarations: [
    AppComponent,

    ClientsComponent,
    ClientListComponent,
    ClientNewComponent,
    ClientDetailsComponent,

    MoviesComponent,
    MoviesListComponent,
    MovieDetailsComponent,
    MovieNewComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
  ],
  providers: [ClientService, MovieService, DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule {
}
