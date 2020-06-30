import {Component, OnInit} from '@angular/core';
import {MovieService} from '../../services/movie.service';
import {ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {Movie} from '../../models/movie';
import {map, switchMap} from 'rxjs/operators';
import {ConversionService} from '../../services/conversion.service';

@Component({
  selector: 'app-movie',
  templateUrl: './movie.component.html',
  styleUrls: ['./movie.component.scss']
})
export class MovieComponent implements OnInit {
  movie$: Observable<Movie>;
  convertedPrice$: Observable<number>;

  constructor(private conversionService: ConversionService,
              private movieService: MovieService,
              private router: ActivatedRoute) {
  }

  ngOnInit(): void {
    const movieId = +this.router.snapshot.paramMap.get('id');
    this.movie$ = this.movieService.fetchMovie(movieId).pipe(map(value => value as Movie));
    this.convertedPrice$ = this.movie$
      .pipe(switchMap(value => this.conversionService.convert(value.price)
        .pipe(map(exchange => exchange.convertedValue))));
  }
}
