import { Component, OnInit } from '@angular/core';
import { UserPageService } from "./user-page.service";
import 'rxjs/add/observable/forkJoin'


@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.scss']
})
export class UserPageComponent implements OnInit {
  users: any;
  user: any = {};

  showMatch: boolean = true;
  imageURL: string = 'http://res.cloudinary.com/cocacode2/image/upload/w_374,h_374,c_crop,x_84,r_max/qhncz2z6icty9u7zjjf0.jpg'
  showDialog: boolean = false;
  countMessage: any = '';
  message: any;
  userReceive: any;

  constructor(private userPageService: UserPageService) {
    this.users = [];


  }

  interestShow(user: any) {
    this.showDialog = true;
    this.userReceive = user;
    console.log(this.userReceive.userId)
  }
  interestClose() {
    this.showDialog = false;
    this.countMessage = '';
  }
  ngOnInit() {
    let userId: number = parseInt(localStorage.getItem('userId'));

    this.userPageService.getUser(userId).subscribe((res) => {
      this.user = res;
      if (this.user.list_ans.length == 0)
        this.showMatch = false
      else
        this.showMatch = true
      if (this.showMatch) {
        this.userPageService.getMatch()
          .subscribe(
          (res: any) => {
            console.log(res);
            let list: any = res;
            this.userPageService.getUsers(res).subscribe(res => {
              console.log(res)
              this.users = res;
              // let i = 0;
              this.users.forEach((user: any) => {
                user.percentage = list[user.userId]
                this.userPageService.getMessage( parseInt( localStorage.getItem('userId')), user.userId)
                  .subscribe(
                    res => {
                      user.interested = true;
                    }, error => {
                      console.log("error")
                      user.interested = false;
                    }
                  );


              })
              this.users = this.users.sort((a: any, b: any) => {
                return b.percentage - a.percentage
              })
            })
          },
          (error: any) => { });
      }
    })
  }

  getAge(dateString: any) {
    var today = new Date();
    var birthDate = new Date(dateString);
    var age = today.getFullYear() - birthDate.getFullYear();
    var m = today.getMonth() - birthDate.getMonth();
    if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
      age--;
    }
    return age;
  }
  sendMessage(msg: string) {
    this.userReceive.interested = true;
    let userId: number = parseInt(localStorage.getItem('userId'));
    this.userPageService.getUser(userId).subscribe((res) => {
      console.log(res.userId)
      this.message = {
        'userSend': res,
        'userReceive': this.userReceive,
        'content': msg
      }
      console.log(this.message)
      this.userPageService.sendMessage(this.message).subscribe((res: any) => {
        console.log(res)
      })
    })

    this.interestClose()

  }



}
