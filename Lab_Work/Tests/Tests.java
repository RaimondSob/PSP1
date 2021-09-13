import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class Tests
{
    EmailValidator emailValidator;
    PasswordChecker passwordChecker;
    PhoneValidator phoneValidator;
    @BeforeAll
    void setUp()
    {
        emailValidator = new EmailValidator();
        passwordChecker = new PasswordChecker();
        phoneValidator = new PhoneValidator();
    }

    //PasswordChecker Tests
    @Test
    void PasswordChecker_CheckLength_ShouldReturnTrue()
    {
        assertTrue(passwordChecker.CheckPasswordLength("aaa",3));
    }

    @Test
    void PasswordChecker_CheckLength_ShouldReturnFalse()
    {
        assertFalse(passwordChecker.CheckPasswordLength("aaa",4));
    }

    @Test
    void PasswordChecker_CheckLength_ShouldThrowIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                passwordChecker.CheckPasswordLength("aaa", -4);
            }
        });
    }

    @Test
    void PasswordChecker_CheckSpecialSymbol_ShouldReturnTrue()
    {
        assertTrue(passwordChecker.CheckSpecialSymbol("sda@%asd##"));
    }

    @Test
    void PasswordChecker_CheckSpecialSymbol_ShouldReturnFalse()
    {
        assertFalse(passwordChecker.CheckSpecialSymbol("sdafdshlk"));
    }

    @Test
    void PasswordChecker_CheckUppercase_ShouldReturnTrue()
    {
        assertTrue(passwordChecker.CheckUppercase("sDa"));
    }

    @Test
    void PasswordChecker_CheckUppercase_ShouldReturnFalse()
    {
        assertFalse(passwordChecker.CheckUppercase("sdafdshlk"));
    }

    //PhoneValidator Tests
    //Initially the business requirements stated that validator should check if the phone number contains only numbers, but since it can have a "+", I decided to change the requirements to contains numbers or "+" if it is in the first position

    @Test
    void PhoneValidator_CheckNumbers_ShouldReturnTrue()
    {
        assertTrue(phoneValidator.CheckNumbers("123412123"));
    }

    @Test
    void PhoneValidator_CheckNumbersAndPlus_ShouldReturnTrue()
    {
        assertTrue(phoneValidator.CheckNumbers("+12341200456"));
    }

    @Test
    void PhoneValidator_CheckNumbers_ShouldReturnFalse()
    {
        assertTrue(phoneValidator.CheckNumbers("1sad23412"));
    }

    @Test
    void PhoneValidator_CheckNumbersAndPlus_ShouldReturnFalse()
    {
        assertTrue(phoneValidator.CheckNumbers("1+2341235324"));
    }

    @Test
    void PhoneValidator_ChangeStart_ShouldBeEqual()
    {
        assertEquals("+370345677857",phoneValidator.ChangeStart("8345677857"));
    }

    @Test
    void PhoneValidator_DoNotChangeStart_ShouldBeEqual()
    {
        assertEquals("+370345677857",phoneValidator.ChangeStart("+370345677857"));
    }

    //Email Tests
    @Test
    void EmailValidator_CheckAtSymbol_ShouldReturnTrue()
    {
        assertTrue(emailValidator.HasAtSign("asd@asd"));
    }

    @Test
    void EmailValidator_CheckAtSymbol_ShouldReturnFalse()
    {
        assertFalse(emailValidator.HasAtSign("asd@asd"));
    }

    @Test
    void EmailValidator_CheckForbiddenSymbols_ShouldReturnTrue()
    {
        assertTrue(emailValidator.CheckForbiddenSymbols("asdasd@asd.com"));
    }

    @Test
    void EmailValidator_CheckForbiddenSymbols_ShouldReturnFalse()
    {
        assertFalse(emailValidator.CheckForbiddenSymbols("~asdasd@asd.com"));
    }

    @Test
    void EmailValidator_CheckDomain_ShouldReturnTrue()
    {
        assertTrue(emailValidator.CheckTLD("asdasd@KFLAwNTRFBB8LCPBM4o4tBjhGnvDYbH2K2AcF9zehQqVdhvyZQIrATdZ4ho4czvJzPrF8fIwQtDD0QNKLe9yOM3ZhL5JS5wAjpjTC9DrtmSUa0soo4kgs88fSa5iJVcpyPkwzma8MAVUouRQpCejo1X6X1bme12q9LoFsYG9UU2J9udzjJeTsyB2vV9pnfpABWfeyGnowujFZC9JPwRfQT6lEHMNFxvbLvo6KGeG7hmKIOeS3K5Ls3hLscXTa.com"));
    }

    @Test
    void EmailValidator_CheckDomain_ShouldReturnFalse()
    {
        assertFalse(emailValidator.CheckTLD("asdasd@AyVDgFFBlUWUdscLBHrndYcyPSX1D6VsSswHYQbuQF1thWS9EIkzs6Xt6bLcdWBCcsX4TdYjcNRNF5t8cccRbtCMqlmFyl9VzozYMOoQ2o4VweTkm6tEspI5dMYSL7XEkyrKbMHVNX4NHtb6IAbQJmPhu9dV3ET8NFOMsMtEUPYEgzrBpv4jM5lO65vYtFJGVzEYnt8EV1Zy21qIx3nwnQ87BB1HSTNGKSFXOEHbafY3m6CRORa3xhjHgSnTNe.com"));
    }

    @Test
    void EmailValidator_CheckTLD_ShouldReturnTrue()
    {
        assertTrue(emailValidator.CheckTLD("asdasd@asd.com"));
    }

    @Test
    void EmailValidator_CheckTLD_ShouldReturnFalse()
    {
        assertFalse(emailValidator.CheckForbiddenSymbols("asdasd@asd.a"));
    }

    @Test
    void EmailValidator_CheckTLDNumber_ShouldReturnFalse()
    {
        assertFalse(emailValidator.CheckForbiddenSymbols("asdasd@asd.com123"));
    }

    @Test
    void EmailValidator_CheckTLDLength_ShouldReturnFalse()
    {
        assertFalse(emailValidator.CheckForbiddenSymbols("~asdasd@asd.er5Sjjjvt0mRhtJSASXqE3xR30zY0suG5vnV0zw6NiGIJu4QbxqWdcnaSakv9JZf"));
    }









}
