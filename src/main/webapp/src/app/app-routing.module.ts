import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {ClientsComponent} from './clients/clients.component';
import {MoviesComponent} from './movies/movies.component';
import {ClientNewComponent} from './clients/client-new/client-new.component';
import {ClientDetailsComponent} from './clients/client-details/client-details.component';
import {MovieDetailsComponent} from './movies/movie-details/movie-details.component';
import {MovieNewComponent} from './movies/movie-new/movie-new.component';

const routes: Routes = [
  {path: 'clients', component: ClientsComponent},
  {path: 'client-new', component: ClientNewComponent},
  {path: 'client/detail/:id', component: ClientDetailsComponent},

  {path: 'movies', component: MoviesComponent},
  {path: 'movie-new', component: MovieNewComponent},
  {path: 'movie/detail/:id', component: MovieDetailsComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
