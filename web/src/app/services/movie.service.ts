import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Movie} from '../models/movie';
import {Pageable} from '../models/pageable';
import {Page} from '../models/page';
import {environment} from '../../environments/environment';

@Injectable()
export class MovieService {
  private readonly host: string;

  constructor(private http: HttpClient) {
    this.host = environment.host;
  }

  public fetch(pageable: Pageable, category: string): Observable<Page<Movie>> {
    let url = `${this.host}/api/movies?page=${pageable.page}&size=${pageable.size}`;
    if (category) {
      url += `&category=${category}`;
    }
    return this.http.get<Page<Movie>>(url);
  }

  public fetchMovie(id: number): Observable<Movie> {
    return this.http.get<Movie>(`${this.host}/api/movies/${id}`);
  }
}
