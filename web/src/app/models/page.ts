export class Page<T> {
  constructor(public content: Array<T>,
              public pageable: Pageable = null,
              public totalElements: number = 0,
              public totalPages: number = 0,
              public last: boolean = false,
              public size: number = 0,
              public sort: any = null,
              public numberOfElements: number = 0,
              public first: boolean = false,
              public empty: boolean = true) {
  }
}

export class Pageable {
  constructor(public sort: any,
              public offset: number,
              public pageSize: number,
              public pageNumber: number,
              public unpaged: boolean,
              public paged: boolean) {
  }
}
