import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';

@Component({
	selector: 'app-principal',
	templateUrl: './principal.component.html',
	styleUrls: ['./principal.component.css']
})
export class PrincipalComponent {
	@Input() title: string = "";

	constructor(private router: Router) {}

	ngOnInit(): void { }

	onNode() {
		this.router.navigate(['node']);
	}

	onLink() {
		this.router.navigate(['link']);
	}

	onHistoria() {
		this.router.navigate(['historia']);
	}
}
