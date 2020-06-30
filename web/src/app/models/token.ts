export class Token {
  constructor(public readonly tokenType: string,
              public accessToken: string,
              public userId: number,
              public username: string,
              public email: string,
              public roles: string[]) {
  }
}
