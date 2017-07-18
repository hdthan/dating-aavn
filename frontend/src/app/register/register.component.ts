import { Component, OnInit } from '@angular/core';
import { RegisterService } from "./register.service";
import { NgForm } from '@angular/forms';
import { Directive, ElementRef, Input } from '@angular/core';
import { User } from "../resource/user";
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: 'register.component.html',
  providers: [RouterModule, RegisterService] // DI here
})
export class RegisterComponent implements OnInit {

  test: any;
  public registerUser: any;

  constructor(private registerService: RegisterService, private router: Router) { }

  ngOnInit() {
    this.registerUser = {
      email: '',
      password: '',
      passwordConfirm: ''
    }
  }

    addUser(user: any) {
    this.registerService.addUser(user)
      .subscribe(
      (result: any) => {
        this.test = "true"
        // this.router.navigate(['./login'])
      }, error => {
        this.test = "fail";
      });
  }

  refreshHandler(){
    this.test = ""
  }

  onSubmit(user: NgForm, event: Event) {
    // console.log(user.value)
    event.preventDefault();
    let userForm = user.value;
    // console.log(user_res);
    this.addUser(userForm);
  }
}
