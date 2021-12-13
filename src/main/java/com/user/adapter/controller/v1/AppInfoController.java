package com.user.adapter.controller.v1;

import static com.user.APPConstant.CONTEXT_PATH;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.server.config.AppProperties;

@RestController
@RequestMapping(CONTEXT_PATH)
public class AppInfoController {

	@Autowired
	private AppProperties appProp;

	@GetMapping("/welcome")
	public ResponseEntity<String> welcomePage() {
		return new ResponseEntity<String>(appProp.appInfo(), HttpStatus.OK);
	}

	@GetMapping("/live")
	public ResponseEntity<String> live() {
		return new ResponseEntity<String>(appProp.appInfo(), HttpStatus.OK);
	}

	@GetMapping("/ready")
	public ResponseEntity<String> ready() {
		return new ResponseEntity<String>(appProp.appInfo(), HttpStatus.OK);
	}

}
