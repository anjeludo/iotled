import {Component} from "@angular/core";
import {Response} from "@angular/http";
import {HttpService} from "./http.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  	title = 'IoTLED';
  	
  	constructor(private httpService: HttpService){}
  
   on() {
        this.httpService.on()
          .subscribe((response: Response) => {
              if (response.status === 200) {
                   console.log('ON!');
              }
           }, (error) => {
               console.log(error);
       });
    }
    
    off() {
        this.httpService.off()
          .subscribe((response: Response) => {
              if (response.status === 200) {
                   console.log('OFF!');
              }
           }, (error) => {
               console.log(error);
       });
    }
  
  
}
