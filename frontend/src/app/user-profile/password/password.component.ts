import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { User } from "../models/user"
import { ProfileService } from "../user-profile.service";

@Component({
  selector: 'app-password',
  templateUrl: './password.component.html',
  styleUrls: ['./password.component.scss']
})
export class PasswordComponent implements OnInit {

  // test
  test : string ;
  public confirmUser: any;
  private userRes : User;
  constructor(private profileService: ProfileService) {
    this.userRes = new User();
    this.userRes.userId = parseInt ( localStorage.getItem('userId') );
  }

  ngOnInit() {
    this.confirmUser = {
      password: '',
      confirmPassword: '',
      NewPassRepeat: ''
    }
  }

  updateUser(user: User) {
    this.profileService.updateProfile(user)
      .subscribe(
      (result: any) => {
        console.log(result)
        if ( result !== "fail" )
        {

          this.userRes = result;
          this.test = "true";
          // refresh page
          this.refresh()
        }


        },
        err => {
          this.test = "fail"
          console.log(this.test)
        }

      );
    }

  refresh(){
    this.confirmUser.password = "";
    this.confirmUser.confirmPassword = "";
    this.confirmUser.NewPassRepeat = "";
  }

  refreshHandler(event:Event){
    this.test = ""

  }
  onSave(user: NgForm, event: Event) {
    // pressed Save-button and do something
    event.preventDefault();
    console.log(user)
    let userForm : User = user.value;
    userForm.userId =  parseInt ( localStorage.getItem('userId') );
    // set input to label

    this.updateUser(userForm);

  }
}
