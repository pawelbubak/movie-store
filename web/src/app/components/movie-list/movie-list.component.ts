import {Component, OnInit} from '@angular/core';
import {PageEvent} from '@angular/material/paginator';
import {MovieService} from '../../services/movie.service';
import {Pageable} from '../../models/pageable';
import {Observable} from 'rxjs';
import {Movie} from '../../models/movie';
import {map, startWith} from 'rxjs/operators';
import {FormControl, FormGroup} from '@angular/forms';
import {TokenService} from '../../services/token.service';
import {CartService} from '../../services/cart.service';
import {CartItem} from '../../models/cart-item';

@Component({
  selector: 'app-movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.scss']
})
export class MovieListComponent implements OnInit {
  pageable: Pageable = {page: 0, size: 10};
  pageSizeOptions: number[] = [10, 20, 50, 100];
  categoryOptions: string[] = ['Action', 'Classics', 'Comedy', 'Drama', 'Family', 'Sci-fi'];
  filteredOptions: Observable<string[]>;

  movieSearchForm = new FormGroup({
    title: new FormControl('')
  });

  movies$: Observable<Array<Movie>>;
  totalSize$: Observable<number>;

  constructor(private cartService: CartService,
              private movieService: MovieService,
              private tokenService: TokenService) {
  }

  ngOnInit(): void {
    this.initCategoryFiltering();
    this.fetchMovies();
  }

  addToCart(movie: Movie): void {
    this.cartService.fetchCartContent().subscribe(cart => {
      const cartItem = cart.items.find(item => item?.movie?.id === movie?.id);
      if (!cartItem) {
        this.cartService.addToCart(new CartItem(movie)).subscribe(() => {
          location.reload();
        });
      } else {
        cartItem.quantity += 1;
        this.cartService.updateCart(cartItem).subscribe(() => {
          location.reload();
        });
      }
    });
  }

  isAuthenticated(): boolean {
    return this.tokenService.isAuthenticated();
  }

  onPageChange(event: PageEvent): void {
    if (event) {
      this.pageable.page = event.pageIndex;
      this.pageable.size = event.pageSize;
    }
    this.fetchMovies();
  }

  private fetchMovies() {
    const category = (this.movieSearchForm.get('title').value as string)?.toUpperCase();
    const moviesPage = this.movieService.fetch(this.pageable, category);
    this.movies$ = moviesPage.pipe(map(value => value?.content));
    this.totalSize$ = moviesPage.pipe(map(value => value?.totalElements));
  }

  private initCategoryFiltering() {
    this.filteredOptions = this.movieSearchForm.valueChanges.pipe(
      map(value => value.title),
      startWith(''),
      map(value => this.filterCategoryOptions(value))
    );
  }

  private filterCategoryOptions(value: string): string[] {
    const filterValue = value.toLowerCase();
    const filteredOptions = this.categoryOptions.filter(option => option.toLowerCase().indexOf(filterValue) === 0);
    if ((filteredOptions?.length === 1 && this.movieSearchForm.get('title')?.value === filteredOptions[0]) ||
      (filteredOptions?.length === this.categoryOptions?.length && !this.movieSearchForm.get('title')?.value)) {
      this.fetchMovies();
    }
    return filteredOptions;
  }
}
