import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ExtractDataService } from '../service/extract-data.service';
import { LinkService } from './link.service';

@Component({
	selector: 'app-link',
	templateUrl: './link.component.html',
	styleUrls: ['./link.component.css']
})
export class LinkComponent {
	title = 'Sistema Autoria';
	orderByStateChart = 1;
	data: any[] = [];
	displayedColumns: string[] = [ "Nó_Origem", "Nó_Destino", "Opções" ];
	pagination = {};

	constructor(
		private router: Router,
		private extractDataService: ExtractDataService,
		private linkService: LinkService
	) {	}

	ngOnInit(): void {
		this.listAllNodes();
		this.listAllLinks();
	}

	listAllNodes() {
		const success = (res: any) => {
			let list: any[] = [];

			if (res && res.length > 0) {
				for (let item of res) {
					let data: any = {
						id: item.id,
						Nó_Origem: item.nodeOrigemNome,
						Nó_Destino: item.nodeDestinoNome,
						Opções: [
							{ name: 'Apagar', icon: 'highlight_off' },
							{ name: 'Editar', icon: 'edit' },
						]
					}

					list.push(data)
				}
			}

			this.data = list;
		}

		const err = (error: any) => {
			console.log(error);
		}
		this.linkService.findAll().subscribe(this.extractDataService.extract(success, err));
	}

	listAllLinks() {

	}

	onNavigate(event: any) {

	}

	onDelete(element: any) {

	}

	onEdit(element: any) {
		this.router.navigate(['link', 'register', element.id]);
	}

	onMenuOption(event: any) {
		switch (event.option) {
			case "Apagar":
				this.onDelete(event.element);
			break;
			case "Editar":
				this.onEdit(event.element);
			break;
		}
	}

	onAdd() {
		this.router.navigate(['link', 'register']);
	}
}
