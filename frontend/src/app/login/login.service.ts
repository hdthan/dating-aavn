import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from "@angular/http";
import { User } from "../resource/user";
import { Observable } from "rxjs/Rx";
import "rxjs/add/operator/catch";
import "rxjs/add/operator/map";

@Injectable()
export class LoginService {
  private userUrl = 'api/login/';

  constructor(private http: Http) {

  }

  login(body: any) {
    let bodyString = JSON.stringify(body);
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.post(this.userUrl, bodyString, options)
      .map((res: any) => {
        // console.log(res)
        if (res._body) {
          console.log("not null here");
          return res.json()
        }
        console.log("null here");
        return null;
      })
      .catch(this.handleError);
  }

  postToken(token: string) {

    let headers = new Headers({ 'Content-Type': 'text/plain' });
    let options = new RequestOptions({ headers: headers });
    return this.http.post('api/social_login', token, options)
      .map((res: any) => {
        // console.log(res)
        if (res._body) {
          return res.json()
        }
        return null;
        ;
      })
      .catch(this.handleError);
  }

  private handleError(error: Response | any) {
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return Observable.throw(errMsg);
  }

}
