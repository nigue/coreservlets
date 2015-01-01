package coreservlets;

import coreservlets.model.Account;
import coreservlets.model.DailyDeal;
import coreservlets.service.AccountLookupService;
import coreservlets.utils.AccountSimpleMap;
import coreservlets.utils.DealUtils;
import coreservlets.utils.FormUtils;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class DealBean4 {

    private String accountId, message;
    private Integer count;
    private DailyDeal todaysDeal = DealUtils.todaysDeal();
    private Account account;
    private static AccountLookupService lookupService
            = new AccountSimpleMap();

    public String getAccountId() {
        return (accountId);
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * Let JSF convert the String to Integer. No parsing. Use Integer instead of
     * int so field is initially blank (vs. 0).
     */
    public Integer getCount() {
        return (count);
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public DailyDeal getTodaysDeal() {
        return (todaysDeal);
    }

    public Account getAccount() {
        return (account);
    }

    public double getPurchaseAmount() {
        return (count * todaysDeal.getPrice());
    }

    /**
     * Outputs a message telling the end user the result of the data submission.
     * This is used in the Ajax version in lieu of separate results pages.
     */
    public String getMessage() {
        return (message);
    }

    public String buyDailyDeal() {
        account = lookupService.findAccount(accountId);
        if (account == null) {
            message = String.format("%s is not a valid account ID", accountId);
        } else if (account.getBalance() < getPurchaseAmount()) {
            message = String.format("Balance for %s ($%,.2f) is "
                            + "too low. $%,.2f needed.",
                            account.getFullName(), account.getBalance(),
                            getPurchaseAmount());
        } else {
            account.setBalance(account.getBalance() - getPurchaseAmount());
            message = String.format("%s successfully ordered %s %s at a total "
                            + "cost of $%,.2f. New balance is $%,.2f.",
                            account.getFullName(), count,
                            FormUtils.wordForCopies(count),
                            getPurchaseAmount(), account.getBalance());
        }
        return (null);
    }
}
