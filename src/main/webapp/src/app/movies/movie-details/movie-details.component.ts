import {Component, OnInit, Input} from '@angular/core';
import {Movie} from '../shared/movie.model';
import {MovieService} from '../shared/movie.service';
import {ActivatedRoute, Params} from '@angular/router';
import {switchMap} from 'rxjs/operators';
import {Location} from '@angular/common';

@Component({
  selector: 'app-movie-details',
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.css']
})
export class MovieDetailsComponent implements OnInit {
  @Input() movie: Movie;

  constructor(private movieService: MovieService,
              private route: ActivatedRoute,
              private location: Location) {

  }

  ngOnInit(): void {
    this.route.params
      .pipe(switchMap((params: Params) => this.movieService.getMovie(+params.id)))
      .subscribe(movie => this.movie = movie);
  }

  goBack() {
    this.location.back();
  }

  update() {
    this.movieService.update(this.movie)
      .subscribe(_ => this.goBack());
  }
}
