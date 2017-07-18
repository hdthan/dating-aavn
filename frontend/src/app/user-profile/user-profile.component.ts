import { MyDatePicker } from 'mydatepicker';
import { Component, OnInit, ViewChild } from '@angular/core';
import { CloudinaryOptions, CloudinaryUploader } from 'ng2-cloudinary';
import { ImageCropperComponent, CropperSettings, Bounds, CropPosition, ImageCropper } from 'ng2-img-cropper';
import { User } from './models/user';
import { ProfileService } from './user-profile.service';
@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

  private selectedTab: Tab;
  private Tabs: Tab[] = [
    { tabName: 'Profile' },
    { tabName: 'Password' }
  ];


  cloudinaryImage: any;
  imageId: string = 'qhncz2z6icty9u7zjjf0';
  // imageURL: string = 'http://res.cloudinary.com/cocacode2/image/upload/w_374,h_374,c_crop,x_84,r_max/qhncz2z6icty9u7zjjf0.jpg'
  imageURL: string = 'assets/images/avatar.png'
  imageOption: any;
  imageSize: any;

  uploader: CloudinaryUploader = new CloudinaryUploader(
    new CloudinaryOptions({ cloudName: 'cocacode1', uploadPreset: 'hoangvy' })
  );

  public data: any;
  public userHeader: User;
  public cropperSettings: CropperSettings;
  public imageCropper: ImageCropper;

  showModal: boolean = false;
  uploading: boolean = false;

  checkSize : boolean = true;
  checkFFormat : boolean = true;

  @ViewChild('cropper', undefined) cropper: ImageCropperComponent;


  constructor(public profileService: ProfileService) {
    this.userHeader = new User();
    this.userHeader.userId = parseInt(localStorage.getItem('userId'));




    this.uploader.onAfterAddingFile = (item: any) => {
      //Update uploadUrl on FileItem
      item.url = this.uploader.options.url;
      console.log("url " + item.url)
      return item;
    };


    //Override onSuccessItem function to record cloudinary response data
    this.uploader.onSuccessItem = (item: any, response: string, status: number, headers: any) => {
      //response is the cloudinary response
      //see http://cloudinary.com/documentation/upload_images#upload_response
      console.log("upload success")
      this.uploading = false;
      this.showModal = false;
      console.log(response)
      this.cloudinaryImage = JSON.parse(response);
      let res: any = JSON.parse(response);
      this.imageId = res.public_id;
      this.imageURL = this.data.image;

      // process link and save in database

      let w = 'w_' + this.imageOption.width;
      let h = 'h_' + this.imageOption.height;
      let x = 'x_' + this.imageOption.x;
      let y = 'y_' + this.imageOption.y;

      let link = 'http://res.cloudinary.com/cocacode1/image/upload/' + w + ',' + h + ',c_crop,' + x + ',' + y + '/' + this.imageId + '.' + res.format;

      //SAVE THIS LINK !!!!!!!!!!!!!!!!
      console.log(link)
      this.userHeader.avaImg = link;
      profileService.updateProfileImg( parseInt ( localStorage.getItem("userId") ) , this.userHeader.avaImg)
        .subscribe(res => { }, err => { });
      // http://res.cloudinary.com/cocacode1/image/upload/w_374,h_374,c_crop,x_84,r_max/qhncz2z6icty9u7zjjf0.jpg
      return { item, response, status, headers };
    }

    //this.imageCropper = new ImageCropper();

    this.cropperSettings = new CropperSettings();
    this.cropperSettings.width = 200;
    this.cropperSettings.height = 200;
    this.cropperSettings.keepAspect = false;

    this.cropperSettings.croppedWidth = 200;
    this.cropperSettings.croppedHeight = 200;

    this.cropperSettings.canvasWidth = 500;
    this.cropperSettings.canvasHeight = 300;

    this.cropperSettings.minWidth = 100;
    this.cropperSettings.minHeight = 100;

    this.cropperSettings.rounded = true;
    this.cropperSettings.minWithRelativeToResolution = false;

    this.cropperSettings.fileType = 'image/jpeg';

    this.cropperSettings.cropperDrawSettings.strokeColor = 'rgba(255,255,255,1)';
    this.cropperSettings.cropperDrawSettings.strokeWidth = 2;
    this.cropperSettings.noFileInput = true;


    this.imageSize = {}
    this.imageOption = {}
    this.data = {};
  }

  ngOnInit() {

    this.profileService.getProfile()
      .subscribe(

      res => {
        this.userHeader = res;
        this.imageURL = this.userHeader.avaImg;
        this.userHeader.password = null;
      }

      );

  }



  upload(): void {
    console.log("uploading. . .");
    this.uploading = true;
    this.uploader.uploadAll();
    // this.position = position;
  }

  cropped(bounds: Bounds) {
    this.imageOption.x = bounds.left;
    this.imageOption.y = bounds.top;
    this.imageOption.width = -bounds.left + bounds.right;
    this.imageOption.height = - bounds.top + bounds.bottom;
    // console.log(bounds);
    // console.log(this.imageOption);
  }

  fileChangeListener($event: any) {
    // this.imageSize.width = this.imageOption.left + this.imageOption.right;
    // this.imageSize.height = this.imageOption.bottom;
    //
    // console.log(this.imageSize)

    this.checkFFormat = false;
    this.checkSize = true;

    let image: any = new Image();
    let file: File = $event.target.files[0];

    let maxSize = 5242880;
    let fSize = file.size;

    let extnList:String[]= ["jpg","png","jpeg"];
    let extn = file.name.split(".").pop().toLowerCase();

    // extnList.forEach((x) => {
    //   if (x === extn){
    //     this.checkFFormat = true;
    //     this.checkSize = false;
    //     if(maxSize > fSize){
    //       this.checkSize = true;
    //     }
    //   }
    // })

    if (extnList.indexOf(extn) > -1 ){
      this.checkFFormat = true;
      this.checkSize = false;
      if(maxSize > fSize){
        this.checkSize = true;
      }
    }

    if(this.checkSize && this.checkFFormat){
      let myReader: FileReader = new FileReader();
      let that = this;

      myReader.onloadend = function(loadEvent: any) {
        image.src = loadEvent.target.result;
        that.cropper.setImage(image);

        // let img = document.createElement('img');
        // img.src = image.getAttribute('src');
        //
        // setTimeout(() => {
        //   // console.log(that.imageSize)
        //   console.log(img.width)
        //   console.log(img.height)
        //   that.imageSize.width = img.width;
        //   that.imageSize.height = img.height;
        // })
      };
      myReader.readAsDataURL(file);
    }

  }


  onSelect(tab: Tab): void {
    this.selectedTab = tab;
  }

  editAvatar() {
    this.showModal = true;

  }

  cancelEditAvatar() {
    this.showModal = false;
    this.checkFFormat = true;
    this.checkSize = true;

  }

}

export class Tab {
  tabName: string;
}
