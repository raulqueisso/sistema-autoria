import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor() { }

  async setStorage(label: string, value: any) {
		await localStorage.setItem(label, JSON.stringify(value));
	}

	getStorage(label: string): string | undefined {
		try {
			const value = localStorage.getItem(label);

			if (value != null) {
				return JSON.parse(value)
			}
			return;
		} catch (e) {
			return;
		}
	}

	deleteStorage(label: string) {
		const value = localStorage.getItem(label);
		if (value != null) {
			localStorage.removeItem(label);
		}
	}
}
