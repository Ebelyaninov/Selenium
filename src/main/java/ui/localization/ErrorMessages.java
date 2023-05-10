package ui.localization;

import com.google.gson.annotations.Expose;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import ui.utils.DateFormat;

public class ErrorMessages extends PageContent<ErrorMessages.ErrorMessage> {
    @Data
    public static class ErrorMessage extends BaseContent {
        @Expose
        private String fieldCanNotBeEmpty;
        @Expose
        private String fieldIsRequired;
        @Expose
        private String invalidAmount;
        @Getter(AccessLevel.NONE)
        @Expose
        private String invalidDateFormat;
        @Expose
        private String invalidDateInPast;
        @Expose
        private String invalidEmailFormat;
        @Expose
        private String invalidAccountEmailPhoneFormat;
        @Expose
        private String accountNotFound;
        @Expose
        private String incorrectBankCode;
        @Expose
        private String receiptAddressIsNotIndicated;
        @Expose
        private String invalidPayerCode;
        @Expose
        private String invalidValue;
        @Expose
        private String textIsTooLong;
        @Expose
        private String incorrectPhoneNumberFormat;
        @Expose
        private String incorrectWalletNumber;

        public String getInvalidDateFormat() {
            return this.invalidDateFormat + DateFormat.getCurrentDate();
        }
    }
}
