package coreservlets.service;

import coreservlets.model.Account;

/** Main interface for classes that you retrieve book site
 *  accounts by ID. This interface provides no way to
 *  modify the set of accounts, so you don't have to
 *  worry about race conditions with shared data.
 */

public interface AccountLookupService {
  public Account findAccount(String id);
}
