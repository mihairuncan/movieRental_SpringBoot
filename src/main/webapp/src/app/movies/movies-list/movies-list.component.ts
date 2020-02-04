import {Component, OnInit} from '@angular/core';
import {Movie} from '../shared/movie.model';
import {MovieService} from '../shared/movie.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-movies-list',
  templateUrl: './movies-list.component.html',
  styleUrls: ['./movies-list.component.css', '../../app.component.css']
})
export class MoviesListComponent implements OnInit {
  movies: Movie[];
  selectedMovie: Movie;

  constructor(private movieService: MovieService,
              private router: Router) {
  }

  ngOnInit() {
    this.getMovies();
  }

  private getMovies() {
    this.movieService.getMovies()
      .subscribe(movies => this.movies = movies);
  }

  onSelected(movie: Movie) {
    this.selectedMovie = movie;
  }

  delete(movie: Movie) {
    this.movieService.delete(movie.id)
      .subscribe(_ => {
        console.log('movie deleted');
        this.movies = this.movies.filter(movieAux => movieAux.id !== movie.id);
      });
  }

  goToDetails() {
    this.router.navigate(['movie/detail', this.selectedMovie.id]);
  }


}
