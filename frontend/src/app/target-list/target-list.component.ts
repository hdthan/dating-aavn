import { Component, OnInit } from '@angular/core';
import { TargetListService } from './target-list.service'
import 'rxjs/add/observable/forkJoin'


@Component({
  selector: 'app-target-list',
  templateUrl: './target-list.component.html',
  styleUrls: ['./target-list.component.scss']
})
export class TargetListComponent implements OnInit {
  users: any;
  user: any = {};
  imageURL: string = 'http://res.cloudinary.com/cocacode2/image/upload/w_374,h_374,c_crop,x_84,r_max/qhncz2z6icty9u7zjjf0.jpg'
  showmessageBox: boolean = false;
  countMessage: any = '';
  message: any;
  currentMessage: any= {};
  sentMessages: any;
  receivedMessages: any;
  messages: any;
  userReceive: any;

  constructor(private targetListService: TargetListService) {
    this.users = []
  }

  openMessageBox(message: any) {
    this.showmessageBox = true;
    this.currentMessage = message;

  }
  closeMessageBox() {
    this.showmessageBox = false;
  }

  ngOnInit() {
    let userId: number = parseInt(localStorage.getItem('userId'));

    this.targetListService.getUser(userId).subscribe((res) => {
      this.user = res;
    })

    let allMessages: any = [];
    this.targetListService.getReceivedMessages(userId).subscribe((receivedMessages) => {
      // console.log(receivedMessages)
      this.receivedMessages = receivedMessages;
      this.targetListService.getSentMessages(userId).subscribe((sentMessages) => {
        allMessages = this.receivedMessages.concat(sentMessages);
        // console.log(this.allMessages)
        allMessages = allMessages.sort((a: any, b: any) => {
          return a.messageId - b.messageId
        });

        let len = allMessages.length;
        let messages: any = [];
        for (let i = 0; i < len; i++) {

          if (allMessages[i] != null) {
            let userSendID = allMessages[i].userSend.userId;
            // console.log('receive: ' + allMessages[i].userReceive.userId, ' send: ' + allMessages[i].userSend.userId)

            if (userSendID != userId && userSendID != undefined) {
              console.log(userSendID)
              let cnt = 0;
              for (let j = i + 1; j < len; j++) {
                // console.log(userSendID, allMessages[j].userReceive.userId)
                if (allMessages[j] != null)
                  if (userSendID == allMessages[j].userReceive.userId) {
                    messages.push({});
                    messages[messages.length - 1].target = allMessages[i].userSend;
                    messages[messages.length - 1].content = allMessages[i].content;
                    messages[messages.length - 1].myReply = allMessages[j].content;
                    allMessages[j] = null;
                    cnt++;
                    break;
                  }
              }

              if (cnt == 0) {
                messages.push({});
                messages[messages.length - 1].target = allMessages[i].userSend;
                messages[messages.length - 1].content = allMessages[i].content;
                messages[messages.length - 1].newRequest = true;
              }

            }

            else {
              let cnt = 0;
              let userReceiveID = allMessages[i].userReceive.userId;
              for (let j = i + 1; j < len; j++) {
                if (allMessages[j] != null) {
                  if (userReceiveID == allMessages[j].userSend.userId) {
                    messages.push({});
                    messages[messages.length - 1].target = allMessages[i].userReceive;
                    messages[messages.length - 1].content = allMessages[i].content;
                    messages[messages.length - 1].theirReply = allMessages[j].content;
                    allMessages[j] = null;
                    cnt++;
                    break;
                  }
                }

              }

              if (cnt == 0) {
                messages.push({});
                messages[messages.length - 1].target = allMessages[i].userReceive;
                messages[messages.length - 1].content = allMessages[i].content;
                messages[messages.length - 1].myRequest = true;
              }
            }
          }

        }

        console.log(messages)
        this.messages = messages;
      })


    })

  }
  sendMessage(countMessage: string) {
    // this.currentMessage.myReply = countMessage;
    let userId: number = parseInt(localStorage.getItem('userId'));

    this.targetListService.getUser(userId).subscribe((res) => {
      console.log(res.userId)
      this.message = {
        'userSend': res,
        'userReceive': this.currentMessage.target,
        'content': countMessage
      }
      console.log(this.message)
      this.targetListService.sendMessage(this.message).subscribe((res: any) => {
        this.currentMessage.myReply = countMessage;
        this.currentMessage.newRequest = false;
        this.countMessage = '';
      })
    })

    // this.closeMessageBox()
  }

}
