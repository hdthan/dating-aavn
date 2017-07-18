import { Injectable } from "@angular/core";
import { Http, Response, Headers, RequestOptions } from "@angular/http";
import { Observable } from "rxjs/Rx";
import { User } from "./models/user"
import "rxjs/add/operator/catch";
import "rxjs/add/operator/map";

@Injectable()
export class ProfileService {
  private userUrl = "api/users/"

  constructor(private http: Http) {

  }

  updateProfile(user: User) {
    // let userId : number = parseInt ( localStorage.getItem('userId') );
    console.log(user)
    let userString = JSON.stringify(user);

    let headers = new Headers({ 'Content-Type': 'application/json' }); // ... Set content type to JSON
    let options = new RequestOptions({ headers: headers }); // Create a request option
    return this.http.put(this.userUrl, userString, options)
      .map((res: Response) => {
        console.log(res.statusText)
        return (res.status == 200) ? res.json() : 'fail' // return json if status response is Created
      })
      .catch(this.handleError);
  }

  updateProfileImg( id: number, img : string){
    let headers = new Headers({ 'Content-Type': 'application/json' }); // ... Set content type to JSON
    let options = new RequestOptions({ headers: headers }); // Create a request option
    return this.http.patch(this.userUrl + `${id}`, img, options)
      .map((res: Response) => {
        console.log(res.statusText)
        return (res.status == 200) ? "true" : 'fail' // return json if status response is Ok
      })
      .catch(this.handleError);

  }

  getProfile() {
    let userId: number = parseInt(localStorage.getItem('userId'));
    console.log(userId)
    return this.http.get(this.userUrl + `${userId}`)
      .map(res => {
        return (res.status == 200) ? res.json() : 'fail'
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
