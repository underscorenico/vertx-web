/*
 * Copyright 2014 Red Hat, Inc.
 *
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *
 *  The Eclipse Public License is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  The Apache License v2.0 is available at
 *  http://www.opensource.org/licenses/apache2.0.php
 *
 *  You may elect to redistribute this code under either of these licenses.
 */

package io.vertx.ext.web.sstore;

import io.vertx.codegen.annotations.Nullable;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.ext.web.Session;

/**
 * A session store is used to store sessions for an Vert.x-Web web app
 *
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
@VertxGen
public interface SessionStore {

  /**
   * Default length for a session id.
   * More info: https://www.owasp.org/index.php/Session_Management_Cheat_Sheet
   */
  int DEFAULT_SESSIONID_LENGTH = 16;

  /**
   * The retry timeout value in milli seconds used by the session handler when it retrieves a value from the store.<p/>
   *
   * A non positive value means there is no retry at all.
   *
   * @return the timeout value, in ms
   */
  long retryTimeout();

  /**
   * Create a new session using the default min length.
   *
   * @param timeout - the session timeout, in ms
   *
   * @return the session
   */
  Session createSession(long timeout);

  /**
   * Create a new session
   *
   * @param timeout - the session timeout, in ms
   * @param length - the required length for the session id
   *
   * @return the session
   */
  Session createSession(long timeout, int length);

  /**
   * Get the session with the specified ID
   *
   * @param id  the unique ID of the session
   * @param resultHandler  will be called with a result holding the session, or a failure
   */
  void get(String id, Handler<AsyncResult<@Nullable Session>> resultHandler);

  /**
   * Delete the session with the specified ID
   *
   * @param id  the unique ID of the session
   * @param resultHandler  will be called with a success or a failure
   */
  void delete(String id, Handler<AsyncResult<Void>> resultHandler);

  /**
   * Add a session with the specified ID
   *
   * @param session  the session
   * @param resultHandler  will be called with a success or a failure
   */
  void put(Session session, Handler<AsyncResult<Void>> resultHandler);

  /**
   * Remove all sessions from the store
   *
   * @param resultHandler  will be called with a success or a failure
   */
  void clear(Handler<AsyncResult<Void>> resultHandler);

  /**
   * Get the number of sessions in the store
   *
   * @param resultHandler  will be called with the number, or a failure
   */
  void size(Handler<AsyncResult<Integer>> resultHandler);

  /**
   * Close the store
   */
  void close();

}
