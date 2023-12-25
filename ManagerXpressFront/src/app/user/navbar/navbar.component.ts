import { Component } from '@angular/core';
import { TokenStorageService } from '../../_services/token-storage.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  constructor(private tokenStorageService: TokenStorageService) { }

  onLogoutClick(): void {
    this.tokenStorageService.signOut();
  }
}
