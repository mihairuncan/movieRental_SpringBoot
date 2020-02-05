import {Injectable} from '@angular/core';
import {Movie} from './movie.model';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MovieService {
  private moviesUrl = 'http://localhost:8080/movies';

  constructor(private httpClient: HttpClient) {
  }

  getMovies(): Observable<Array<Movie>> {
    return this.httpClient.get<Array<Movie>>(this.moviesUrl);
  }

  getMovie(id: number): Observable<Movie> {
    return this.getMovies()
      .pipe(map(movies => movies.find(movie => movie.id === id)));
  }

  delete(movieId: number): Observable<any> {
    const url = `${this.moviesUrl}/${movieId}`;
    return this.httpClient
      .delete(url);
  }

  update(movie: Movie): Observable<Movie> {
    const url = `${this.moviesUrl}/${movie.id}`;
    return this.httpClient
      .put<Movie>(url, movie);
  }

  save(movie): Observable<Movie> {
    console.log('movie service: ', movie);

    return this.httpClient
      .post<Movie>(this.moviesUrl, movie);
  }
}
