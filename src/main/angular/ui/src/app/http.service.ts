import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Response, Http, Headers, RequestOptions} from "@angular/http";

 
@Injectable()
export class HttpService {
 
    constructor(private http: Http) { }
 
    on(): Observable<Response> {
        return this.http.get("/on", this.makeOptions())
    }
 
    off(): Observable<Response> {
        return this.http.get("/off", this.makeOptions())
    }
 
    private makeOptions(): RequestOptions {
        let headers = new Headers({'Content-Type': 'application/json'});
        return new RequestOptions({headers: headers});
    }
}