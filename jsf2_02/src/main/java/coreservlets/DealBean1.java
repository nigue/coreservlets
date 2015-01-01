package coreservlets;

import coreservlets.model.Account;
import coreservlets.model.DailyDeal;
import coreservlets.service.AccountLookupService;
import coreservlets.utils.AccountSimpleMap;
import coreservlets.utils.DealUtils;
import coreservlets.utils.FormUtils;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author anibal
 */
@ManagedBean
public class DealBean1 {

    private String accountId, countString;
    private int count;
    private DailyDeal todaysDeal = DealUtils.todaysDeal();
    private Account account;
    private static AccountLookupService lookupService = new AccountSimpleMap();

    public String getAccountId() {
        return (accountId);
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCountString() {
        return (countString);
    }

    /**
     * Converting from String to int manually. Later example will let JSF do it
     * for us.
     *
     * @param countString
     */
    public void setCountString(String countString) {
        try {
            count = Math.abs(Integer.parseInt(countString));
            this.countString = countString;
        } catch (NumberFormatException | NullPointerException e) {
            // Keep default values (count=0, countString=null)
        }
    }

    public int getCount() {
        return (count);
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

    public String getPurchaseDollars() {
        return (String.format("$%,.2f", getPurchaseAmount()));
    }

    public String buyDailyDeal() {
        String folder = "pages/dealBean1/";
        if (FormUtils.isAnyMissing(accountId, countString)) {
            return (folder + "deal-error-1");
        }
        account = lookupService.findAccount(accountId);
        if (account == null) {
            return (folder + "deal-bad-id-1");
        }
        if (account.getBalance() < getPurchaseAmount()) {
            return (folder + "deal-insufficient-balance-1");
        }
        account.setBalance(account.getBalance() - getPurchaseAmount());
        return (folder + "deal-success-1");
    }
}
