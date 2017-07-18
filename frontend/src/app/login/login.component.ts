import { Component, OnInit, OnDestroy } from '@angular/core';
import { User } from '../resource/user';
import { LoginService } from './login.service';
import { NgForm } from '@angular/forms';
import { AuthService } from "angular2-social-login";
import { Router, RouterModule } from '@angular/router';
// import { LocalStorageService } from 'angular-2-local-storage';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
})
export class LoginComponent implements OnInit {
  error: String;
  public user: any;
  sub: any;
  loginUser : any;

  constructor(private loginService: LoginService, public _auth: AuthService, private router: Router) {

  }

  ngOnInit() {
    this.loginUser = {
      email: '',
      password: '',
      passwordConfirm: ''
    }
  }

  login(user_res: any) {
    this.loginService.login(user_res)
      .subscribe(
      (user) => {

          localStorage.setItem('userId', user.userId);
          localStorage.setItem('name', user.name);
          localStorage.setItem('userId', user.userId);
          this.router.navigate(['./user-profile/Profile'])
          
      },
      error => {
        this.error = 'Incorrect email or password'
      });
  }

  socialLogin(provider: any) {
    this.sub = this._auth.login(provider).subscribe(
      (data) => {
        console.log(data);
        this.user = data;

        this.loginService.postToken(this.user.token).subscribe(
          (user) => {
            if (user != null) {
              localStorage.setItem('name', user.name);
              localStorage.setItem('userId', user.userId);
              this.router.navigate(['./user-profile/Profile'])
            }
          },
        error => { });



      }
    )
  }

  refreshHandler(){
    this.error = "";
  }

  onSubmit(user: NgForm, event: Event) {
    // console.log(user.value)
    event.preventDefault();
    let user_res = user.value;
    this.login(user_res)
    // user.reset();
  }

}
