package coreservlets;

import coreservlets.model.Account;
import coreservlets.model.DailyDeal;
import coreservlets.service.AccountLookupService;
import coreservlets.utils.AccountSimpleMap;
import coreservlets.utils.DealUtils;
import coreservlets.utils.FormUtils;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class DealBean5 {

    private String accountId;
    private int count = 1;
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
     * For PrimeFaces number spinner: Use int instead of Integer so field is
     * initially 0 (vs. empty).
     */
    public int getCount() {
        return (count);
    }

    public void setCount(int count) {
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

    private void addMessage(String message, boolean isError) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage fMessage = new FacesMessage(message);
        if (isError) {
            fMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
        }
        context.addMessage(null, fMessage);
    }

    public String buyDailyDeal() {
        account = lookupService.findAccount(accountId);
        String message;
        if (account == null) {
            message = String.format("%s is not a valid account ID", accountId);
            addMessage(message, true);
        } else if (account.getBalance() < getPurchaseAmount()) {
            message = String.format("Balance for %s ($%,.2f) is "
                            + "too low. $%,.2f needed.",
                            account.getFullName(), account.getBalance(),
                            getPurchaseAmount());
            addMessage(message, true);
        } else {
            account.setBalance(account.getBalance() - getPurchaseAmount());
            message = String.format("%s successfully ordered %s %s at a total "
                            + "cost of $%,.2f. New balance is $%,.2f.",
                            account.getFullName(), count,
                            FormUtils.wordForCopies(count),
                            getPurchaseAmount(), account.getBalance());
            addMessage(message, false);
        }
        return (null);
    }
}
