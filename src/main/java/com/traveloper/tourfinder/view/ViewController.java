package com.traveloper.tourfinder.view;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    @Value("${naver.ncp.client-id}")
    private String NCP_CLIENT_ID;

    @RequestMapping("/test")
    public String test() {
        return "sample";
    }

    // 지도 띄우는 테스트
    @GetMapping("api-test/sample-map")
    public String sampleMap(
            Model model
    ) {
        model.addAttribute("clientId", NCP_CLIENT_ID);
        return "sample-map";
    }
}