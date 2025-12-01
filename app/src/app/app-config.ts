import { StorageService } from './service/storage.service';
import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class AppConfig {

    constructor(
        private storageService: StorageService) {
    }

    public init() {
        this.verifyDevUrl();
    }

    private async verifyDevUrl() {
        await this.storageService.setStorage("routes", []);
    }
}