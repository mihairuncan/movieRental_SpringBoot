import {Component, OnInit} from '@angular/core';
import {Location} from '@angular/common';
import {MovieService} from '../shared/movie.service';

@Component({
  selector: 'app-movie-new',
  templateUrl: './movie-new.component.html',
  styleUrls: ['./movie-new.component.css']
})
export class MovieNewComponent implements OnInit {

  constructor(private movieService: MovieService,
              private location: Location) {
  }

  ngOnInit() {
  }

  save(name, genre, year, rentalPrice) {
    this.movieService.save(
      {
        name,
        genre,
        year,
        rentalPrice
      })
      .subscribe(movie => this.location.back(),
        err => console.log('Error in saving movie', err),
        () => console.log('save movie complete'));
  }

  goBack() {
    console.log('movie-new component go BACK');
    this.location.back();
  }
}
