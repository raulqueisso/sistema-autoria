import { Injectable } from '@angular/core';
import { StorageService } from './service/storage.service';


@Injectable({
  	providedIn: 'root'
})
export class AppConfigService {

  	constructor(private storageService: StorageService) { }

	private requestNode: string = "/nodes";
	private requestFilme: string = "/filmes";
	private requestHistoria: string = "/historias";
	private requestLink: string = "/links";
	private requestRelatorio: string = "/relatorio";

	public get baseUrl(): string {
		return "http://localhost:8081";
	}

	get nodeUrl() {
		return this.requestNode;
	}

	get filmeUrl() {
		return this.requestFilme;
	}

	get historiaUrl() {
		return this.requestHistoria;
	}

	get linkUrl() {
		return this.requestLink;
	}

	get relatorioUrl() {
		return this.requestRelatorio;
	}
}
