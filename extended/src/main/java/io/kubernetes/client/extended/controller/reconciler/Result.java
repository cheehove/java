package io.kubernetes.client.extended.controller.reconciler;

import java.time.Duration;

/** The type Result contains the result of a Reconciler invocation. */
public class Result {

  /**
   * Instantiates a new Result.
   *
   * @param requeue the requeue
   */
  public Result(boolean requeue) {
    this(requeue, null);
  }

  private boolean requeue;
  private Duration requeueAfter;

  /**
   * Instantiates a new Result.
   *
   * @param requeue the requeue
   * @param requeueAfter the requeue after
   */
  public Result(boolean requeue, Duration requeueAfter) {
    this.requeue = requeue;
    this.requeueAfter = requeueAfter;
  }

  /**
   * Is requeue boolean.
   *
   * @return the boolean
   */
  public boolean isRequeue() {
    return requeue;
  }

  /**
   * Sets requeue.
   *
   * @param requeue the requeue
   */
  public void setRequeue(boolean requeue) {
    this.requeue = requeue;
  }

  /**
   * Gets requeue after.
   *
   * @return the requeue after
   */
  public Duration getRequeueAfter() {
    return requeueAfter;
  }

  @Override
  public String toString() {
    return "Result{" + "requeue=" + requeue + ", requeueAfter=" + requeueAfter + '}';
  }

  /**
   * Sets requeue after.
   *
   * @param requeueAfter the requeue after
   */
  public void setRequeueAfter(Duration requeueAfter) {
    this.requeueAfter = requeueAfter;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Result result = (Result) o;

    if (requeue != result.requeue) return false;
    return requeueAfter != null
        ? requeueAfter.equals(result.requeueAfter)
        : result.requeueAfter == null;
  }

  @Override
  public int hashCode() {
    int result = (requeue ? 1 : 0);
    result = 31 * result + (requeueAfter != null ? requeueAfter.hashCode() : 0);
    return result;
  }
}
