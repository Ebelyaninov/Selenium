package api.model.account;

import api.model.BaseResponse;
import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AccountItemsResponse extends BaseResponse {

    @Expose
    private List<BankAccount> items;
}