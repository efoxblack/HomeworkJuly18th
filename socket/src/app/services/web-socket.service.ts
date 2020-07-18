import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {

  onMessage: Subject<any> = new Subject<any>();

  webSocket;


  constructor() {
    this.connect();
   }

  connect() {
    this.webSocket = new WebSocket('ws://localhost:4200/chat');
    this.webSocket.onmessage = (message: MessageEvent) => {
      this.onMessage.next(message);
    }
  }
  
  send(message: string): void {
    this.webSocket.send(message);
  }

}
