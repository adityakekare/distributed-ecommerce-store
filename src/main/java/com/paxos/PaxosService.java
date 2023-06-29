package com.paxos;

import com.paxos.utils.Promise;
import com.paxos.utils.Proposal;
import com.paxos.utils.Status;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

@Service
public class PaxosService {

  private int maxId;
  private Proposal acceptedProposal;

  public PaxosService(){
    maxId = 0;
    acceptedProposal = null;
  }

  public Promise prepare(Proposal proposal) {
    System.out.println("[" + new Timestamp(new Date().getTime()).toString() + "]"
            + " Server: Received a Prepare message");
    if (Math.random() < 0.05) {
      System.out.println("Server: Server Failure");
      return null;
    }
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    FutureTask<Promise> future = new FutureTask<>(new Callable<Promise>() {
      @Override
      public Promise call() throws Exception {
        if(maxId == 0){
          maxId = proposal.getId();
          return new Promise(Status.PROMISED, proposal);
        }
        else if (proposal.getId() <= maxId) {
          return new Promise(Status.REJECTED, null);
        } else {
          maxId = proposal.getId();
          if (acceptedProposal != null) {
            return new Promise(Status.ACCEPTED, new
                    Proposal(acceptedProposal.getId(),
                    acceptedProposal.getRequestMethod()));
          } else {
            return new Promise(Status.PROMISED, proposal);
          }
        }
      }
    });
    try {
      executorService.submit(future);
      return future.get(20, TimeUnit.SECONDS);
    } catch (Exception e) {
      System.out.println("Error");
      return null;
    }
  }

  public Boolean accept(Proposal proposal) {
    System.out.println("[" + new Timestamp(new Date().getTime()).toString() + "]"
            + " Server: Received a Accept message");
    if (Math.random() < 0.05) {
      System.out.println("Server: Server Failure");
      return null;
    }

    ExecutorService executorService = Executors.newSingleThreadExecutor();
    FutureTask<Boolean> future = new FutureTask<>(new Callable<Boolean>() {
      @Override
      public Boolean call() throws Exception {
        if (proposal.getId() != maxId) {
          return Boolean.FALSE;
        }

        if (acceptedProposal == null) {
          acceptedProposal = new Proposal(proposal.getId(), proposal.getRequestMethod());
        } else {
          acceptedProposal.setId(proposal.getId());
          acceptedProposal.setRequestMethod(proposal.getRequestMethod());
        }
        return Boolean.TRUE;
      }
    });

    try {
      executorService.submit(future);
      return future.get(20, TimeUnit.SECONDS);
    } catch (Exception e) {
      System.out.println("Error");
      return null;
    }

  }
}
