package com.controller;

import com.model.Order;
import com.paxos.PaxosService;
import com.paxos.utils.Promise;
import com.paxos.utils.Proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paxos")
public class PaxosController {

  @Autowired
  PaxosService paxosService;

  @PostMapping("/prepare")
  public ResponseEntity<Promise> prepare(@RequestBody Proposal proposal) {
    Promise promise = paxosService.prepare(proposal);
    return new ResponseEntity<>(promise, HttpStatus.OK);
  }

  @PostMapping("/accept")
  public ResponseEntity<Boolean> accept(@RequestBody Proposal proposal) {
    Boolean isAccepted = paxosService.accept(proposal);
    return new ResponseEntity<>(isAccepted, HttpStatus.OK);
  }

}
