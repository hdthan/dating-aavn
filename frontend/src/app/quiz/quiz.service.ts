import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from "@angular/http";
import { User } from "../resource/user";
import { Observable } from "rxjs/Rx";
import "rxjs/add/operator/catch";
import "rxjs/add/operator/map";
import { LocalStorageService } from 'angular-2-local-storage';

@Injectable()
export class QuizService {

  private quizUrl = 'api/questions/'; // TODO take real server url
  private userUrl = 'api/users'
  constructor(private http: Http) { }

  getQuiz(): Observable<any> {
    return this.http.get(this.quizUrl)
      .map(res => res.json())
      .catch(this.handleError);
  }


  postQA(qa: any) {
    const userId = localStorage.getItem('userId');
    // console.log('is that right?')
    let body = {
      'userId': localStorage.getItem('userId'),
        list_ans: qa
    }

    console.log(body);

    let bodyString = JSON.stringify(body);
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.post(this.userUrl, bodyString, options)
      .map(res => res)
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
