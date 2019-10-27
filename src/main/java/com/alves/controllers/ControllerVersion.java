package com.alves.controllers;

import java.util.Properties;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alves.services.Version;


@RestController
public class ControllerVersion {

	@RequestMapping(value = "/version", method = RequestMethod.GET)
	public ResponseEntity<Properties> versionTest() {
		return ResponseEntity.status(HttpStatus.OK).body(new Version().versionEntity());

	}
}
