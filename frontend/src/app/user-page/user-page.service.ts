import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from "@angular/http";
import { Observable } from "rxjs/Rx";
import "rxjs/add/operator/catch";
import "rxjs/add/operator/map";
import 'rxjs/add/observable/forkJoin'
import { LocalStorageService } from 'angular-2-local-storage';


@Injectable()
export class UserPageService {

  private matchUrl = 'api/match/';
  private userUrl = "api/users/";
  private messageUrl = "api/message/";
  users: any = [];
  constructor(private http: Http) { }


  getMatch(): Observable<any> {
    let self = this;
    return this.http.get(this.matchUrl + localStorage.getItem('userId'))
      .map(res => {
        return res.json()

      })
      .catch(this.handleError);
  }

  getUser(userId: any): Observable<any> {
    return this.http.get(this.userUrl + userId)
      .map(res => {
        console.log(res.json())
        // console.log(res.status)
        return (res.status == 200) ? res.json() : 'fail'

      })
      .catch(this.handleError);
  }

  getUsers(req: any) {
    let observableBatch: any = [];
    let self = this;
    Object.keys(req).forEach(function(key) {

      console.log(key, req[key]);
      observableBatch.push(self.http.get(self.userUrl + key).map((res: Response) => res.json()));

    });

    return Observable.forkJoin(observableBatch);
  }
  sendMessage(body: any){
    console.log(body)
    let bodyString = JSON.stringify(body);
    let headers = new Headers({ 'Content-Type': 'application/json' }); // ... Set content type to JSON
    let options = new RequestOptions({ headers: headers }); // Create a request option

    return this.http.post(this.messageUrl, bodyString, options)
      .map((res: Response) => res.json())
      .catch(this.handleError);
  }

  getMessage(id:number , userReceiveId:any){
    // let bodyString = JSON.stringify(userReceive);
    let headers = new Headers({ 'Content-Type': 'application/json' }); // ... Set content type to JSON
    let options = new RequestOptions({ headers: headers }); // Create a request option
    return this.http.patch(this.messageUrl + `${id}`,userReceiveId, options)
          .map( (res : Response) => {
              return res.json;
          })
          .catch(this.handleError);

  }

  private handleError(error: Response | any) {
    // In a real world app, we might use a remote logging infrastructure
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
