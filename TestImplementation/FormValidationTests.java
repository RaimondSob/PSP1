import com.raimondsobolevskij.service.FormValidator;
import org.testng.annotations.Test;

//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class FormValidationTests {


    private FormValidator formValidator = new FormValidator();
    // email validator tests
    @Test
    public void emailValidator_emptyEmail_shouldReturnFalse() {
        String email = "   ";
        boolean isValid = formValidator.validateEmail(email);
        assertFalse(isValid);
    }

    @Test
    public void emailValidator_noEtaSign_shouldReturnFalse() {
        String email = "fooAtmail.com";
        boolean isValid = formValidator.validateEmail(email);
        assertFalse(isValid);
    }

    @Test
    public void emailValidator_hasDoubleDot_shouldReturnFalse() {
        String email = "te..st@mail.com";
        boolean isValid = formValidator.validateEmail(email);
        assertFalse(isValid);
    }

    @Test
    public void emailValidator_hasTwoEtas_shouldReturnFalse() {
        String email = "test@@mail.com";
        boolean isValid = formValidator.validateEmail(email);
        assertFalse(isValid);
    }

    @Test
    public void emailValidator_hasIllegalChars_shouldReturnFalse() {
        String email = "test'?;@mail.com";
        boolean isValid = formValidator.validateEmail(email);
        assertFalse(isValid);
    }

    @Test
    public void emailValidator_hasBadCredential_shouldReturnFalse() {
        String email = "@mail.lt";
        boolean isValid = formValidator.validateEmail(email);
        assertFalse(isValid);
    }

    @Test
    public void emailValidator_hasBadDomain_shouldReturnFalse() {
        String email = "test@mail.9";

        String afterEta = email.substring(email.indexOf('@'), email.length());

        int[] indexes = new int[afterEta.length()];
        int indexesIndex = 0;
        for (int i = 0; i < afterEta.length(); i++) {
            if (afterEta.charAt(i) == '.') {
                indexes[indexesIndex] = i;
                indexesIndex++;
            }
        }


        boolean isValid = formValidator.validateEmail(email);
        assertFalse(isValid);
    }

    @Test
    public void emailValidator_hasNoDomain_shouldReturnFalse() {
        String email = "test@";
        boolean isValid = formValidator.validateEmail(email);
        assertFalse(isValid);
    }

    @Test
    public void emailValidator_hasBadTopLevelDomain_shouldReturnFalse() {
        String email = "test@.com";
        boolean isValid = formValidator.validateEmail(email);
        assertFalse(isValid);
    }

    @Test
    public void emailValidator_validMail_shouldReturnTrue() {
        String email = "test.123@mail.another.com";
        boolean isValid = formValidator.validateEmail(email);
        assertTrue(isValid);
    }

    // phone number validator tests
    @Test
    public void phoneValidator_emptyNumber_shouldReturnFalse() {
        String number = "   ";
        boolean isValid = formValidator.validatePhoneNumber(number);
        assertFalse(isValid);
    }

    @Test
    public void phoneValidator_containsLetter_shouldReturnFalse() {
        String number = "86A44651";
        boolean isValid = formValidator.validatePhoneNumber(number);
        assertFalse(isValid);
    }

    @Test
    public void phoneValidator_doesntStartWithPlusOrEight_shouldReturnFalse() {
        String number = "1234568";
        boolean isValid = formValidator.validatePhoneNumber(number);
        assertFalse(isValid);
    }

    @Test
    public void phoneValidator_startsWithEight_lengthShouldBeNine_shouldReturnFalse() {
        String number = "123456";
        boolean isValid = formValidator.validatePhoneNumber(number);
        assertFalse(isValid);
    }

    @Test
    public void phoneValidator_validNumber_shouldReturnTrue() {
        String number = "861234567";
        boolean isValid = formValidator.validatePhoneNumber(number);
        assertTrue(isValid);
    }

    @Test
    public void phoneValidator_validNumberStartsWithPlus_shouldReturnTrue() {
        String number = "+37061234567";
        boolean isValid = formValidator.validatePhoneNumber(number);
        assertTrue(isValid);
    }

    // password validation tests
    @Test
    public void passwordValidator_emptyPassword_shouldReturnFalse() {
        String passwordToTest = "  ";
        boolean isValid = formValidator.validatePassword(passwordToTest);
        assertFalse(isValid);
    }

    @Test
    public void passwordValidator_passwordTooShort_shouldReturnFalse() {
        String passwordToTest = "1234";
        boolean isValid = formValidator.validatePassword(passwordToTest);
        assertFalse(isValid);
    }

    @Test
    public void passwordValidator_noUpperCase_shouldReturnFalse() {
        String passwordToTest = "labasabas";
        boolean isValid = formValidator.validatePassword(passwordToTest);
        assertFalse(isValid);
    }

    @Test
    public void passwordValidator_noSpecialChars_shouldReturnFalse() {
        String passwordToTest = "labasLabas";
        boolean isValid = formValidator.validatePassword(passwordToTest);
        assertFalse(isValid);
    }

    @Test
    public void passwordValidator_validPassword_shouldReturnTrue() {
        String passwordToTest = "Labasabas?";
        boolean isValid = formValidator.validatePassword(passwordToTest);
        assertTrue(isValid);
    }
}
