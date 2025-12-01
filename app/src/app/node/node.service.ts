import { Injectable } from '@angular/core';
import { HttpService } from '../http/http.service';
import { AppConfigService } from '../app-config.service';

@Injectable({
    providedIn: 'root'
})
export class NodeService {

  private url: string = "";

	constructor(
		private httpService: HttpService,
		private appService: AppConfigService
	) {
		this.url = this.appService.baseUrl
	}

	findAll() {
		return this.httpService.get(this.url + this.appService.nodeUrl);
	}
}
