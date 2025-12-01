import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ExtractDataService } from '../service/extract-data.service';
import { NodeService } from './node.service';

@Component({
	selector: 'app-node',
	templateUrl: './node.component.html',
	styleUrls: ['./node.component.css']
})
export class NodeComponent {
	title = 'Sistema Autoria';
	orderByStateChart = 1;
	data: any[] = [];
	displayedColumns: string[] = [ "História", "Nó", "Conteúdo", "Opções" ];
	pagination = {};

	constructor(
		private router: Router,
		private extractDataService: ExtractDataService,
		private nodeService: NodeService
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
						História: item.historiaTitulo,
						Nó: item.nome,
						Conteúdo: item.conteudo,
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
		this.nodeService.findAll().subscribe(this.extractDataService.extract(success, err));
	}

	listAllLinks() {

	}

	onNavigate(event: any) {

	}

	onDelete(element: any) {

	}

	onEdit(element: any) {
		this.router.navigate(['node', 'register', element.id]);
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
		this.router.navigate(['node', 'register']);
	}
}
