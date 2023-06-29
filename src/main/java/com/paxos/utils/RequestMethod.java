package com.paxos.utils;

import java.io.Serializable;

/**
 * An Enum to encapsulate types of request methods
 */
public enum RequestMethod implements Serializable {
  GET,
  PUT,
  DEL
}
