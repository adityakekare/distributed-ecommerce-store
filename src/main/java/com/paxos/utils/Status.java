package com.paxos.utils;

import java.io.Serializable;

/**
 * An enum to encapsulate types of Paxos Acceptor statuses
 */
public enum Status implements Serializable {
  ACCEPTED,
  REJECTED,
  PROMISED
}
