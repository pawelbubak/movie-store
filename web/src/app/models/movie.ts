export class Movie {
  constructor(
    public id: number,
    public title: string,
    public category: string,
    public year: string,
    public cast: string,
    public director: string,
    public story: string,
    public price: number
  ) {
  }
}
