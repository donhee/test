package bitcamp.java106.pms.controller;

import bitcamp.java106.pms.server.ServerRequest;
import bitcamp.java106.pms.server.ServerResponse;

public interface Controller {
    // 규칙1:
    // 사용자가 명령어를 입력하면 App 클래스는
    // 해당 명령어를 처리할 컨트롤러를 찾아 service() 메서드를 호출한다.
    void service(ServerRequest request, ServerResponse response);
}
