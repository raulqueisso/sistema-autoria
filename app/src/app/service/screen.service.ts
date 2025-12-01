import { Injectable } from "@angular/core";

@Injectable({
	providedIn: 'root'
  })
export class ScreenService {
    public verifyScreen() {
		if(window.innerWidth < 768) {
		    return "mobile";
		} else {
		    return "desktop";
		}
	}

	public widthScreen(): number {
		return window.innerWidth;
	}
}