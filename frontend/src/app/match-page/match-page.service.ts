import { Injectable } from "@angular/core";
import { Http, Response, Headers, RequestOptions } from "@angular/http";
import { Observable } from "rxjs/Rx";
import { User } from "../user-profile/models/user"
import "rxjs/add/operator/catch";
import "rxjs/add/operator/map";

@Injectable()
export class MatchPageService {


  private userUrl = "api/users/"
  private messageUrl = "api/message/";
  constructor(private http: Http) { }

  getProfile(id:number) {

    return this.http.get(this.userUrl + `${id}`)
            .map( res => {
              return (res.status == 200) ? res.json() : 'fail'
            })
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

  getUser(userId: any): Observable<any> {
   return this.http.get(this.userUrl + userId)
     .map(res => {
       console.log(res.json())
       // console.log(res.status)
       return (res.status == 200) ? res.json() : 'fail'

     })
     .catch(this.handleError);
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



}
