/**
 * Created by lmchuc on 6/2/2017.
 */
import { Component } from "@angular/core";
import { LocalStorageService } from 'angular-2-local-storage';
import { Router, RouterModule } from '@angular/router';


@Component({
  selector: 'my-app',
  templateUrl: 'app.component.html'
})
export class AppComponent {
  private name: string;

  constructor(private localStorageService: LocalStorageService, private router: Router) {

    router.events.subscribe((val) => {
      if(localStorage.getItem('name')) {
        this.name = localStorage.getItem('name')
      }
    })

  }

  ngOnInit() {
    this.name = 'My Account'
  }

  logout() {
    localStorage.removeItem('name');
    localStorage.removeItem('userId');
    localStorage.removeItem('answers');
    this.router.navigate(['./'])
  }

}
